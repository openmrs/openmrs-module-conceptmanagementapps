package org.openmrs.module.conceptmanagementapps.page.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.Concept;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptReferenceTerm;
import org.openmrs.ConceptSource;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.ui.framework.page.PageModel;

public class UploadSpreadsheetPageController {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	public void post(@RequestParam("spreadsheet") MultipartFile spreadsheetFile) {
		
		BufferedReader br = null;
		String line = "";
		try {
			String filename = spreadsheetFile.getOriginalFilename();
			String[] nameWithoutExtension = filename.split(".csv");
			String sourceId = nameWithoutExtension[0].substring(nameWithoutExtension[0].length() - 1);
			br = new BufferedReader(new InputStreamReader(spreadsheetFile.getInputStream()));
			int linenumber = 1;
			boolean header = true;
			ConceptService cs = Context.getConceptService();
			Collection<ConceptMap> conceptMappings;
			ConceptSource source;
			boolean foundMapping = false;
			while ((line = br.readLine()) != null) {
				String mappings = "";
				String mapTypeName = "";
				String sourceCode = "";
				String sourceName = "";
				ConceptMap conceptMap = new ConceptMap();
				ConceptReferenceTerm refTerm = new ConceptReferenceTerm();
				
				if (!header) {
					if (!foundMapping && !line.endsWith("\"")) {
						line = line + "\"";
						foundMapping = true;
					} else if (foundMapping && !line.endsWith("\"")) {
						line = "\"" + line + "\"";
						foundMapping = true;
					} else if (foundMapping && line.endsWith("\"")) {
						line = "\"" + line;
						foundMapping = false;
					}
					
					String[] tokens = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
					for (String t : tokens) {
						switch (linenumber) {
							case 1:
								mapTypeName = t;
								conceptMap.setConceptMapType(cs.getConceptMapTypeByName(mapTypeName));
								linenumber++;
								break;
							case 2:
								sourceName = t;
								linenumber++;
								break;
							case 3:
								sourceCode = t;
								source = cs.getConceptSource(Integer.valueOf(sourceId.trim()));
								if (cs.getConceptReferenceTermByCode(t, source) == null) {
									refTerm.setConceptSource(source);
									refTerm.setCode(t);
									cs.saveConceptReferenceTerm(refTerm);
								} else {
									refTerm = cs.getConceptReferenceTermByCode(t, source);
								}
								conceptMap.setConceptReferenceTerm(refTerm);
								linenumber++;
								break;
							case 4:
								if (cs.getConcept(t) != null) {
									Concept concept = cs.getConcept(t);
									if (concept.getConceptMappings() == null) {
										List<ConceptMap> conceptMappingsList = new ArrayList<ConceptMap>();
										conceptMappingsList.add(conceptMap);
										concept.setConceptMappings(conceptMappingsList);
									} else {
										conceptMappings = concept.getConceptMappings();
										conceptMappings.add(conceptMap);
										concept.setConceptMappings(conceptMappings);
										
									}
									cs.saveConcept(concept);
								}
								linenumber++;
								break;
							case 5:
								linenumber++;
								break;
							case 6:
								linenumber++;
								break;
							case 7:
								linenumber++;
								break;
							case 8:
								linenumber++;
								break;
							case 9:
								linenumber++;
								break;
							case 10:
								if (!header) {
									if (t.startsWith("\"") && t.length() > 2) {
										mappings += t;
										if (!foundMapping) {
											linenumber = 1;
											mappings = "";
										}
									} else {
										linenumber = 1;
										mappings = "";
									}
								}
								
								break;
							default:
								linenumber++;
								log.error("invalid line in file" + linenumber);
								break;
						
						}
					}
				}
				header = false;
				
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			IOUtils.closeQuietly(br);
		}
		
	}
	
	public void get(UiSessionContext sessionContext, PageModel model) throws Exception {
		
	}
	
}
