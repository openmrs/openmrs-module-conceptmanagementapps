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

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.test.BaseContextSensitiveTest;
import org.openmrs.test.BaseModuleContextSensitiveTest;
import org.openmrs.ui.framework.UiUtils;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.mock.web.MockMultipartFile;

/**
 * Tests {@link $ ConceptManagementAppsService} .
 */
public class ConceptManagementAppsServiceTest extends BaseModuleContextSensitiveTest {
	
	protected ConceptManagementAppsService conceptManagementAppsService = null;
	
	
	
	/**
	 * Run this before each unit test in this class. The "@Before" method in
	 * {@link BaseContextSensitiveTest} is run right before this method.
	 * 
	 * @throws Exception
	 */
	@Before
	public void runBeforeAllTests() throws Exception {
		conceptManagementAppsService = Context.getService(ConceptManagementAppsService.class);
	}
	
	@Test
	public void shouldSetupContext() {
		assertNotNull(Context.getService(ConceptManagementAppsService.class));
	}
	
	@Test
	public void getUnmappedConcepts_getsCorrectNumberOfRows() throws Exception {
		executeDataSet("concepts.xml");
		ConceptService cs = Context.getConceptService();
		List<ConceptClass> classesToInclude = new ArrayList<ConceptClass>();
		Integer sourceId = 6;
		classesToInclude.add(cs.getConceptClass(2));
		classesToInclude.add(cs.getConceptClass(4));
		classesToInclude.add(cs.getConceptClass(1));
		
		conceptManagementAppsService = (ConceptManagementAppsService) Context.getService(ConceptManagementAppsService.class);
		List<Concept> conceptList = conceptManagementAppsService.getUnmappedConcepts(new ConceptSource(sourceId),
		    classesToInclude);
		Assert.assertEquals(9, conceptList.size());
	}
	
	@Test
	public void uploadSpreadsheet_shouldPassWithoutErrors() throws Exception {
		conceptManagementAppsService = (ConceptManagementAppsService) Context.getService(ConceptManagementAppsService.class);
		executeDataSet("concepts.xml");
		final String fileName = "test.csv";
		String line = "\"map type\",\"source name\",\"source code\",\"concept Id\",\"concept uuid\",\"preferred name\",\"description\",\"class\",\"datatype\",\"all existing mappings\"\n";
		line += "\"same-as\",\"\",12345,225,\"432AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"MEDICAL EXAMINATION, ROUTINE\",\"Routine examination, without signs of problems.\",\"Procedure\",\"Boolean\",\"SAME-AS AMPATH \n SAME-AS SNOMED MVP\n SAME-AS PIH \n SAME-AS AMPATH \n NARROWER-THAN SNOMED NP\"\n";
		line += "\"same-as\",\"SNOMED CT\",12345,225,\"432AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"MEDICAL EXAMINATION, ROUTINE\",\"Routine examination, without signs of problems.\",\"Procedure\",\"Boolean\",\"SAME-AS AMPATH \n SAME-AS SNOMED MVP\n SAME-AS PIH \n SAME-AS AMPATH \n NARROWER-THAN SNOMED NP\"\n";
		line += "\"same-as\",\"SNOMED CT\",12345,225,\"1148AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA\",\"TOTAL MATERNAL TO CHILD TRANSMISSION PROPHYLAXIS\",\"Describes the use of drugs to prevent the maternal to child transmission of HIV during pregnancy.\",\"Procedure\",\"Coded\",\"SAME-AS AMPATH \n NARROWER-THAN SNOMED NP \n SAME-AS SNOMED MVP\"\n";
		final byte[] content = line.getBytes();
		MockMultipartFile mockMultipartFile = new MockMultipartFile("content", fileName, "text/plain", content);
		conceptManagementAppsService.uploadSpreadsheet(mockMultipartFile);
		
	}
	
	@Test
	public void downloadFileWithMissingConceptMappings_shouldPassWithoutErrors() throws Exception {
		executeDataSet("concepts.xml");
		PrintWriter pw = mock(PrintWriter.class);
		
		conceptManagementAppsService = (ConceptManagementAppsService) Context.getService(ConceptManagementAppsService.class);
		List<Concept> conceptList = new ArrayList<Concept>();
		conceptList.add(Context.getConceptService().getConcept("300"));
		conceptManagementAppsService.writeFileWithMissingConceptMappings(conceptList, pw, "same-as", "SNOMED CT");
	}
	
}
