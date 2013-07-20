package org.openmrs.module.conceptmanagementapps.fragment.controller;

import java.util.ArrayList;
import java.util.List;

import org.openmrs.ConceptReferenceTerm;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.module.conceptmanagementapps.api.ConceptManagementAppsService;
import org.openmrs.ui.framework.page.PageModel;
import org.springframework.web.bind.annotation.RequestParam;

public class BrowseReferenceTermsTableFragmentController {
	
	public List<String[]> getInitialDataTablePage(@RequestParam("startIndex") Integer startIndex,
	                                              @RequestParam("sourceId") Integer sourceId,
	                                              @RequestParam("numOfRefTermsToRetrieve") Integer numOfRefTermsToRetrieve)
	    throws Exception {
		
		ConceptManagementAppsService conceptManagementAppsService = (ConceptManagementAppsService) Context
		        .getService(ConceptManagementAppsService.class);
		
		List<ConceptReferenceTerm> referenceTermList;
		
		if (sourceId == 0) {
			referenceTermList = conceptManagementAppsService.getReferenceTermsForAllSources(Integer.valueOf(startIndex),
			    Integer.valueOf(numOfRefTermsToRetrieve));
		} else {
			referenceTermList = conceptManagementAppsService.getReferenceTermsForSpecifiedSource(Context.getConceptService()
			        .getConceptSource(sourceId), startIndex, numOfRefTermsToRetrieve);
		}
		
		List<String[]> referenceTermDataList = new ArrayList<String[]>();
		
		for (ConceptReferenceTerm crt : referenceTermList) {
			
			String[] referenceTermArray = { crt.getConceptSource().getName(), crt.getCode(), crt.getName(),
			        crt.getDescription() };
			referenceTermDataList.add(referenceTermArray);
			
		}
		
		return referenceTermDataList;
	}
	
	public List<String[]> retrieveNewPages(@RequestParam("startIndex") Integer startIndex,
	                                       @RequestParam("sourceId") Integer sourceId,
	                                       @RequestParam("numOfRefTermsToRetrieve") Integer numOfRefTermsToRetrieve)
	    throws Exception {
		
		ConceptManagementAppsService conceptManagementAppsService = (ConceptManagementAppsService) Context
		        .getService(ConceptManagementAppsService.class);
		
		List<ConceptReferenceTerm> referenceTermList;
		
		if (sourceId == 0) {
			referenceTermList = conceptManagementAppsService.getReferenceTermsForAllSources(Integer.valueOf(startIndex),
			    Integer.valueOf(numOfRefTermsToRetrieve));
		} else {
			referenceTermList = conceptManagementAppsService.getReferenceTermsForSpecifiedSource(Context.getConceptService()
			        .getConceptSource(sourceId), startIndex, numOfRefTermsToRetrieve);
		}
		
		List<String[]> referenceTermDataList = new ArrayList<String[]>();
		
		for (ConceptReferenceTerm crt : referenceTermList) {
			String[] referenceTermArray = { crt.getConceptSource().getName(), crt.getCode(), crt.getName(),
			        crt.getDescription() };
			referenceTermDataList.add(referenceTermArray);
			
		}
		
		return referenceTermDataList;
	}
	
	public List<String[]> searchForReferenceTerms(@RequestParam("refTermQuery") String refTermQuery,
	                                              @RequestParam("sourceId") Integer sourceId,
	                                              @RequestParam("startIndex") Integer startIndex,
	                                              @RequestParam("numOfRefTermsToRetrieve") Integer numOfRefTermsToRetrieve)
	    throws Exception {
		
		ConceptService conceptService = (ConceptService) Context.getService(ConceptService.class);
		
		List<ConceptReferenceTerm> referenceTermList;
		
		if (sourceId == 0) {
			referenceTermList = conceptService.getConceptReferenceTerms(refTermQuery, null, startIndex,
			    numOfRefTermsToRetrieve, false);
		} else {
			referenceTermList = conceptService.getConceptReferenceTerms(refTermQuery, Context.getConceptService()
			        .getConceptSource(sourceId), startIndex, numOfRefTermsToRetrieve, false);
		}
		
		List<String[]> referenceTermDataList = new ArrayList<String[]>();
		
		for (ConceptReferenceTerm crt : referenceTermList) {
			String[] referenceTermArray = { crt.getConceptSource().getName(), crt.getCode(), crt.getName(),
			        crt.getDescription() };
			referenceTermDataList.add(referenceTermArray);
			
		}
		
		return referenceTermDataList;
	}
	
	public void controller(@RequestParam("sourceId") String sourceId, UiSessionContext sessionContext, PageModel model)
	    throws Exception {
	}
}
