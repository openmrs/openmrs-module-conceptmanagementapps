/**
 * The contents of this file are subject to the OpenMRS Public License
 * Version 1.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * http://license.openmrs.org
 *
 * Software distributed under the License is distributed on an "AS IS"
 * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * License for the specific language governing rights and limitations
 * under the License.
 *
 * Copyright (C) OpenMRS, LLC.  All Rights Reserved.
 */
package org.openmrs.module.conceptmanagementapps.api;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.junit.Before;
import org.openmrs.module.conceptmanagementapps.api.ConceptManagementAppsService;

/**
 * Tests {@link $ ConceptManagementAppsService} .
 */
public class ConceptManagementAppsServiceTest extends
		BaseModuleContextSensitiveTest {
	protected ConceptManagementAppsService conceptManagementAppsService = null;

	/**
	 * Run this before each unit test in this class. The "@Before" method in
	 * {@link BaseContextSensitiveTest} is run right before this method.
	 * 
	 * @throws Exception
	 */
	@Before
	public void runBeforeAllTests() throws Exception {
		conceptManagementAppsService = Context
				.getService(ConceptManagementAppsService.class);

	}

	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(ConceptManagementAppsService.class));
	}

	@Test
	public void getUnmappedConcepts_getsCorrectNumberOfRows() throws Exception {
		initializeInMemoryDatabase();
		executeDataSet("concepts.xml");
		authenticate();
		String sourceId = "6";
		String classes = "2,4,1";
		String [] classArray = classes.split(",");
		List<ConceptClass> conceptClasses=new ArrayList<ConceptClass>();
		for(int i=0;i<classArray.length;i++){
			String convToInteger = classArray[i];
			Integer classInteger = Integer.valueOf(convToInteger.trim());
			conceptClasses.add(Context.getConceptService().getConceptClass((classInteger)));
		}
		conceptManagementAppsService = (ConceptManagementAppsService) Context
				.getService(ConceptManagementAppsService.class);
		List<Concept> conceptList = conceptManagementAppsService
				.getUnmappedConcepts(new ConceptSource(Integer.valueOf(sourceId.trim())), conceptClasses);
		Assert.assertEquals(5, conceptList.size());
	}
}
