package org.openmrs.module.conceptmanagementapps.page.controller;

import java.io.InputStream;
import java.util.List;

import org.openmrs.ConceptClass;
import org.openmrs.ConceptSource;
import org.openmrs.api.context.Context;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class DownloadSpreadsheetPageController {
	
	public void post(@RequestParam("sourceId") String sourceId, UiSessionContext sessionContext, PageModel model) {
		List<ConceptSource> sourceList = Context.getConceptService().getAllConceptSources();
		
		model.addAttribute("sourceList", sourceList);
		

	}
	
	public void get(UiSessionContext sessionContext, PageModel model) throws Exception {
		List<ConceptSource> sourceList = Context.getConceptService().getAllConceptSources();
		List<ConceptClass> classList = Context.getConceptService().getAllConceptClasses();
		model.addAttribute("sourceList", sourceList);
		model.addAttribute("classList", classList);
		
	}
}