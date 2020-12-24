package com.java.service.controller.master;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.java.service.Util.ServiceRestURIConstants;
import com.java.service.bean.master.DimensionsDO;
import com.java.service.services.masters.DimensionService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DimensionController {
	
	@Autowired
	private DimensionService dimensionService;
	
	private static final Logger logger = LoggerFactory.getLogger(DimensionController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_DIMENSIONS, method = RequestMethod.POST)
	public @ResponseBody DimensionsDO getdimensions(@RequestBody DimensionsDO single_dimensions) 
	{
		DimensionsDO DimensionsDO = new DimensionsDO();
		try
		{
			logger.info("Start fetching single dimensions ID="+single_dimensions.getId());
			DimensionsDO = dimensionService.fetchForUpdate(single_dimensions.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single dimensions : " + excp.getMessage());
		}
		return DimensionsDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_DIMENSIONS_BY_NAME, method = RequestMethod.POST)
	public @ResponseBody DimensionsDO getdimensionsByName(@RequestBody DimensionsDO single_dimensions) 
	{
		DimensionsDO DimensionsDO = new DimensionsDO();
		try
		{
			DimensionsDO = dimensionService.fetchForUpdateByName(single_dimensions);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single dimensions : " + excp.getMessage());
		}
		return DimensionsDO;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_DIMENSIONS_LIST, method = RequestMethod.POST)
	public @ResponseBody List<DimensionsDO> getAllEmployees() 
	{
		logger.info("Start feching all dimensions.");
		List<DimensionsDO> obj_list = new ArrayList<DimensionsDO>();
		try
		{
			obj_list = dimensionService.fetchList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching all dimensions list : " + excp.getMessage());
		}
		return obj_list;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_DIMENSIONS, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody DimensionsDO create_do) 
	{
		boolean status = false;
		DimensionsDO dimensionObj_name = new DimensionsDO();
		DimensionsDO dimensionsDO = new DimensionsDO();
		try
		{
			logger.info("Start create dimensions.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					dimensionsDO = dimensionService.fetchForUpdate(create_do.getId());
					dimensionsDO.setDomainsDO(create_do.getDomainsDO());
					dimensionsDO.setsDimensionsName(create_do.getsDimensionsName());
					dimensionsDO.setdUpdatedDate(new Date());
					dimensionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
					dimensionsDO.setcIsDeleted('N');
					status = dimensionService.updateRecord(dimensionsDO);
				} else
				{
					dimensionObj_name= dimensionService.fetchForUpdateByName(create_do);
					if(dimensionObj_name.getId() != null)
					{
						dimensionsDO = dimensionObj_name;
						dimensionsDO.setdUpdatedDate(new Date());
						dimensionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
						status = dimensionService.updateRecord(dimensionsDO);
					} else
					{
						create_do.setdCreatedDate(new Date());
						status = dimensionService.createRecord(create_do);						
					}
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update dimensions : " + excp.getMessage());
		}
		return status;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.DELETE_DIMENSIONS, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody DimensionsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start delete dimensions.");
			if(create_do.getsKey().equals("soft"))
			{
				DimensionsDO dimensionsDO = dimensionService.fetchForUpdate(create_do.getId());
				dimensionsDO.setcIsDeleted('N');
				dimensionsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				dimensionsDO.setdUpdatedDate(new Date());
				status = dimensionService.updateRecord(dimensionsDO);
			} else
			{
				status = dimensionService.deleteRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete dimensions : " + excp.getMessage());
		}
		return status;
	}
}
