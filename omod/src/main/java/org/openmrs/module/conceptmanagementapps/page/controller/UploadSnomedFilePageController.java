package org.openmrs.module.conceptmanagementapps.page.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.api.context.Context;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.module.conceptmanagementapps.api.ConceptManagementAppsService;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.PageModel;
import org.openmrs.ui.framework.page.PageRequest;
import org.springframework.web.bind.annotation.RequestParam;

public class UploadSnomedFilePageController {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	public void post(@RequestParam("snomedFile") String snomedFile, UiUtils ui, PageRequest pageRequest, PageModel model) {
		
		ConceptManagementAppsService conceptManagementAppsService = (ConceptManagementAppsService) Context
		        .getService(ConceptManagementAppsService.class);
		
		conceptManagementAppsService.readInSnomedFile(snomedFile);
		log.info("done adding reference terms");
	}
	
	public void get(UiSessionContext sessionContext, PageModel model) throws Exception {
	}
	
}
