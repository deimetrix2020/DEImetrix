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
import com.java.service.bean.master.DomainsDO;
import com.java.service.services.masters.DomainService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DomainController {
	
	@Autowired
	private DomainService domainService;
	
	private static final Logger logger = LoggerFactory.getLogger(DomainController.class);
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_DOMAIN, method = RequestMethod.POST)
	public @ResponseBody DomainsDO getDOMAIN(@RequestBody DomainsDO single_DOMAIN) 
	{
		DomainsDO DomainsDO = new DomainsDO();
		try
		{
			logger.info("Start fetching single DOMAIN ID="+single_DOMAIN.getId());
			DomainsDO = domainService.fetchForUpdate(single_DOMAIN.getId());
		} catch (Exception excp) 
		{
			logger.error("Error fetching single domain : " + excp.getMessage());
		}
		return DomainsDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_SINGLE_DOMAIN_BY_NAME, method = RequestMethod.POST)
	public @ResponseBody DomainsDO getdomainByName(@RequestBody DomainsDO single_dimensions) 
	{
		DomainsDO domainsDO = new DomainsDO();
		try
		{
			domainsDO = domainService.fetchForUpdateByName(single_dimensions);
		} catch (Exception excp) 
		{
			logger.error("Error fetching single domain : " + excp.getMessage());
		}
		return domainsDO;
	}
	
	@RequestMapping(value = ServiceRestURIConstants.GET_ALL_DOMAIN_LIST, method = RequestMethod.POST)
	public @ResponseBody List<DomainsDO> getAllEmployees() 
	{
		logger.info("Start feching all domain.");
		List<DomainsDO> obj_list = new ArrayList<DomainsDO>();
		try
		{
			obj_list = domainService.fetchList();
		} catch (Exception excp) 
		{
			logger.error("Error fetching all domain list : " + excp.getMessage());
		}
		return obj_list;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.CREATE_UPDATE_DOMAIN, method = RequestMethod.POST)
	public @ResponseBody boolean createUpdateUser(@RequestBody DomainsDO create_do) 
	{
		boolean status = false;
		DomainsDO domainsDO = new DomainsDO();
		DomainsDO domainsDO_obj = new DomainsDO();
		try
		{
			logger.info("Start create domain.");
			if(create_do != null)
			{
				if(create_do.getId() != null)
				{
					domainsDO = domainService.fetchForUpdate(create_do.getId());
					domainsDO.setsDomainName(create_do.getsDomainName());
					domainsDO.setcIsDeleted('N');
					domainsDO.setdUpdatedDate(new Date());
					domainsDO.setsUpdatedBy(create_do.getsUpdatedBy());
					status = domainService.updateRecord(domainsDO);
				} else
				{
					domainsDO_obj = domainService.fetchForUpdateByName(domainsDO);
					if(domainsDO_obj.getId() != null)
					{
						domainsDO = domainsDO_obj;
						domainsDO.setdUpdatedDate(new Date());
						domainsDO.setsUpdatedBy(create_do.getsUpdatedBy());
						status = domainService.updateRecord(domainsDO);
					} else
					{
						create_do.setdCreatedDate(new Date());
						status = domainService.createRecord(create_do);						
					}
				}
			}
		} catch (Exception excp) 
		{
			logger.error("Error create/update domain : " + excp.getMessage());
		}
		return status;
	}
		
	@RequestMapping(value = ServiceRestURIConstants.DELETE_DOMAIN, method = RequestMethod.POST)
	public @ResponseBody boolean deleteEmployee(@RequestBody DomainsDO create_do) 
	{
		boolean status = false;
		try
		{
			logger.info("Start delete domain.");
			if(create_do.getsKey().equals("soft"))
			{
				DomainsDO domainsDO = domainService.fetchForUpdate(create_do.getId());
				domainsDO.setcIsDeleted('Y');
				domainsDO.setdUpdatedDate(new Date());
				domainsDO.setsUpdatedBy(create_do.getsUpdatedBy());
				status = domainService.updateRecord(domainsDO);
			} else
			{
				status = domainService.deleteRecord(create_do);
			}
		} catch (Exception excp) 
		{
			logger.error("Error delete domain : " + excp.getMessage());
		}
		return status;
	}
}
