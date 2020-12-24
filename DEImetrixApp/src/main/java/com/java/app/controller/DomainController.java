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
import com.java.app.beans.DomainsDO;
import com.java.app.beans.ServerConfigDO;
import com.java.app.utils.AppConstants;
import com.java.app.utils.RestURIConstants;

@Controller
@RequestMapping(value ="/domain_controller")
public class DomainController 
{
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(DomainController.class);
	
	/**
	 * Create a new record.
	 *
	 * @param request
	 * @return true, if successful
	 */
	@RequestMapping(value = "/createRecord", method = RequestMethod.POST)
	public @ResponseBody Integer createRecord (HttpServletRequest request, HttpSession session)
	{
		DomainsDO domainObj = new DomainsDO();
		DomainsDO domainObj_name = new DomainsDO();
		boolean bStatus = false;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				String sDomain_txt = request.getParameter("domain_txt");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				String sprim_id = request.getParameter("domain_id");
				if(null != sprim_id && sprim_id != "")
				{
					Integer id = Integer.valueOf(request.getParameter("domain_id"));
					domainObj.setId(id);
					domainObj.setsUpdatedBy(session_admin.getsUserName());
				} else
				{
					domainObj_name = fetchingDomainDetailsByName(sDomain_txt.trim());
					if(domainObj_name.getId() != null)
					{
						domainObj = domainObj_name;
						domainObj.setsUpdatedBy(session_admin.getsUserName());
					} else
					{
						domainObj.setsCreatedBy(session_admin.getsUserName());
					}
				}
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				domainObj.setsDomainName(sDomain_txt.trim());
				domainObj.setcIsDeleted('N');
				bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.CREATE_UPDATE_DOMAIN, domainObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("Domain Inserted");
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
		DomainsDO domainObj = new DomainsDO();
		boolean bStatus = false;
		Integer return_key = 2;
		ServerConfigDO configDO = new ServerConfigDO();
		try
		{
			configDO = fetchingServerConfig();
			if(new Date().after(configDO.getdMaintainStartDate()) && new Date().before(configDO.getdMaintainEndDate()))
			{
				Integer id = Integer.valueOf(request.getParameter("domain_id"));
				String sKey = request.getParameter("key");
				
				AdminLoginDO session_admin = (AdminLoginDO) session.getAttribute(AppConstants.ADMIN);
				
				RestTemplate restTemplate = new RestTemplate();
				// set the values
				domainObj.setId(id);
				domainObj.setcIsDeleted('Y');
				domainObj.setsUpdatedBy(session_admin.getsUserName());
				domainObj.setsKey(sKey);
				bStatus = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.DELETE_DOMAIN, domainObj, Boolean.class);
				if(bStatus)
				{
					return_key = 1;
					logger.info("Domain Deleted");
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
	public @ResponseBody List<DomainsDO> getAllCompetenMst(HttpServletRequest request) 
	{
		List<DomainsDO> list_demo = new ArrayList<DomainsDO>();
		List<DomainsDO> list_demo_1 = new ArrayList<DomainsDO>();
		List<DomainsDO> convert_list = new ArrayList<DomainsDO>();
		try
		{
			RestTemplate restTemplate = new RestTemplate();
			list_demo_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_ALL_DOMAIN_LIST, list_demo, List.class);
			String element = new Gson().toJson(list_demo_1, new TypeToken<ArrayList<DomainsDO>>() {}.getType());
			ObjectMapper mapper = new ObjectMapper();
			convert_list = Arrays.asList(mapper.readValue(element, DomainsDO[].class));
		}catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return convert_list;
	}
	
	/**
	 * Retire record
	 * 
	 * @param request
	 * @param session
	 * @return {@link ProcessMstDO}
	 */
	@RequestMapping(value = "/retriveRecord", method = RequestMethod.POST)
	public @ResponseBody DomainsDO retriveRecord(HttpServletRequest request, HttpSession session) 
	{
		DomainsDO do1 = new DomainsDO();
		DomainsDO do1_1 = new DomainsDO();
		try
		{
			Integer id = Integer.valueOf(request.getParameter("id"));
			do1.setId(id);
			RestTemplate restTemplate = new RestTemplate();
			do1_1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DOMAIN, do1, DomainsDO.class);
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
		DomainsDO do1 = new DomainsDO();
		boolean bStatus = false;
		try
		{
			String domain_name = request.getParameter("domain_txt");
			
			do1.setsDomainName(domain_name);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DOMAIN_BY_NAME, do1, DomainsDO.class);
			if(do1.getId() != null)
			{
				bStatus = true;
			}
		} catch (Exception e) {
			logger.info("error :", e.getMessage());
		}
		return bStatus;
	}
	
	private DomainsDO fetchingDomainDetailsByName(String domain_name)
	{
		DomainsDO do1 = new DomainsDO();
		try
		{
			do1.setsDomainName(domain_name);
			RestTemplate restTemplate = new RestTemplate();
			do1 = restTemplate.postForObject(RestURIConstants.RESTAPI_URL+RestURIConstants.GET_SINGLE_DOMAIN_BY_NAME, do1, DomainsDO.class);
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
