package com.java.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.java.app.beans.AdminLoginDO;
import com.java.app.beans.DimensionsDO;
import com.java.app.beans.DomainsDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;

@Controller
@RequestMapping(value ="/dimension_controller")
public class DimensionController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DimensionController.class);
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST)
	public @ResponseBody Integer createRecord (HttpServletRequest request, HttpSession session)
	{
		DimensionsDO dimensionObj = new DimensionsDO();
		DimensionsDO dimensionObj_name = new DimensionsDO();
		boolean bStatus = false;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				String sDomain_txt = request.getParameter("domain_val");
				String sDimension_txt = request.getParameter("dimension_txt");
				
				String sprim_id = request.getParameter("dimension_id");
				if(null != sprim_id && sprim_id != "")
				{
					Integer id = Integer.valueOf(request.getParameter("dimension_id"));
					dimensionObj.setId(id);
					dimensionObj.setsUpdatedBy(session_admin.getsUserName());
				} else
				{
					dimensionObj_name= fetchingDimensionDetailsByName(sDimension_txt.trim(), Integer.valueOf(sDomain_txt));
					if(dimensionObj_name.getId() != null)
					{
						dimensionObj = dimensionObj_name;
						dimensionObj.setsUpdatedBy(session_admin.getsUserName());
					} else
					{
						dimensionObj.setsCreatedBy(session_admin.getsUserName());					
					}
				}
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				dimensionObj.setDomainsDO(new DomainsDO(Integer.valueOf(sDomain_txt)));
				dimensionObj.setsDimensionsName(sDimension_txt.trim());
				dimensionObj.setcIsDeleted('N');
				bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_DIMENSIONS, dimensionObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("dimension Inserted");
				} else
				{
					return_key = 2;
				}
			} else
			{
				return_key = 3;
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}
	
	/**
	 * Update a existing record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/deleteRecord", method = RequestMethod.POST)
	public @ResponseBody Integer deleteRecord(HttpServletRequest request, HttpSession session) 
	{
		DimensionsDO dimensionObj = new DimensionsDO();
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				Integer id = Integer.valueOf(request.getParameter("dimension_id"));
				String sKey = request.getParameter("key");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				dimensionObj.setId(id);
				dimensionObj.setcIsDeleted('Y');
				dimensionObj.setsUpdatedBy(session_admin.getsUserName());
				dimensionObj.setsKey(sKey);
				boolean bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_DIMENSIONS, dimensionObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("dimension Deleted");
				} else
				{
					return_key = 2;
				}
			} else
			{
				return_key = 3;
			}
		} catch (Exception excp)
		{
			logger.error(excp.getMessage(), excp);
		}
		return return_key;
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/fetching_all_list", method = RequestMethod.POST)
	public @ResponseBody List<DimensionsDO> getAllCompetenMst(HttpServletRequest request) 
	{
		List<DimensionsDO> list_demo = new ArrayList<DimensionsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DIMENSIONS_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo, new TypeToken<ArrayList<DimensionsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			list_demo = Arrays.asList(mapper.readValue(element, DimensionsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return list_demo;
	}
	
	/**
	 * Retire record
	 * 
	 * @param request
	 * @param session
	 * @return {@link ProcessMstDO}
	 */
	@RequestMapping(value = "/retriveRecord", method = RequestMethod.POST)
	public @ResponseBody DimensionsDO retriveRecord(HttpServletRequest request, HttpSession session) 
	{
		DimensionsDO do1 = new DimensionsDO();
		DimensionsDO do1_1 = new DimensionsDO();
		try
		{
			Integer id = Integer.valueOf(request.getParameter("id"));
			do1.setId(id);
			RestTemplate restTemplate = new RestTemplate();
			do1_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DIMENSIONS, do1, DimensionsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1_1;
	}
	
	/**
	 * Retire record by name
	 * 
	 * @param request
	 * @param session
	 * @return {@link ProcessMstDO}
	 */
	@RequestMapping(value = "/check_duplicate", method = RequestMethod.POST)
	public @ResponseBody boolean check_duplicate(HttpServletRequest request, HttpSession session) 
	{
		DimensionsDO do1 = new DimensionsDO();
		boolean bStatus = false;
		try
		{
			String sDomain_txt = request.getParameter("domain_val");
			String sDimension_txt = request.getParameter("dimension_txt");
			
			do1.setsDimensionsName(sDimension_txt);
			do1.setDomainsDO(new DomainsDO(Integer.valueOf(sDomain_txt)));
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DIMENSIONS_BY_NAME, do1, DimensionsDO.class);
			if(do1.getId() != null)
			{
				bStatus = true;
			}
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return bStatus;
	}
	
	private DimensionsDO fetchingDimensionDetailsByName(String dimension_name, Integer domain_id)
	{
		DimensionsDO do1 = new DimensionsDO();
		try
		{
			do1.setsDimensionsName(dimension_name);
			do1.setDomainsDO(new DomainsDO(domain_id));
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DIMENSIONS_BY_NAME, do1, DimensionsDO.class);
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return do1;
	}
	
	/**
	 * Fetching server config
	 * 
	 * @return
	 */
	private ServerConfigDO fetchingServerConfig()
	{
		ServerConfigDO serverConfigDO = new ServerConfigDO();
		try
		{
			serverConfigDO.setId(1);
			RestTemplate restTemplate = new RestTemplate();
			serverConfigDO = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_SERVER_CONFIG, serverConfigDO, ServerConfigDO.class);
		}catch (Exception excp) {
			logger.error(excp.getMessage(), excp);
		}
		return serverConfigDO;
	}
}
