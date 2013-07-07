package org.openmrs.module.conceptmanagementapps.page.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import org.openmrs.api.APIException;
import org.openmrs.api.ConceptService;
import org.openmrs.api.context.Context;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.ui.framework.UiUtils;
import org.openmrs.ui.framework.page.FileDownload;
import org.openmrs.ui.framework.page.PageModel;

public class UploadSpreadsheetPageController {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	public FileDownload post(@RequestParam("spreadsheet") MultipartFile spreadsheetFile, UiUtils ui) {
		
		BufferedReader br = null;
		String line = "";
		ArrayList<String> linesWithErrors = new ArrayList<String>();
		String delimiter = ",";
		linesWithErrors.add("error reason" + delimiter + "map type" + delimiter + "source name" + delimiter + "source code"
		        + delimiter + "concept Id" + delimiter + "concept uuid" + delimiter + "preferred name" + delimiter
		        + "description" + delimiter + "class" + delimiter + "datatype" + delimiter + "all existing mappings" + "\n");
		
		String filename = spreadsheetFile.getOriginalFilename();
		String[] nameWithoutExtension = filename.split(".csv");
		String sourceId = nameWithoutExtension[0].substring(nameWithoutExtension[0].length() - 1);
		
		try {
			
			br = new BufferedReader(new InputStreamReader(spreadsheetFile.getInputStream()));
			int columnNumber = 1;
			boolean header = true;
			ConceptService cs = Context.getConceptService();
			Collection<ConceptMap> conceptMappings;
			ConceptSource source;
			boolean foundMapping = false;
			
			while ((line = br.readLine()) != null) {
				String errorString = "";
				String mappings = "";
				String mapTypeName = "";
				String sourceCode = "";
				String sourceName = "";
				ConceptMap conceptMap = new ConceptMap();
				
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
						switch (columnNumber) {
							case 1:
								mapTypeName = t.replace("\"", "").trim();
								if (t.replace("\"", "").trim().length() <= 1
								        || cs.getConceptMapTypeByName(mapTypeName) == null) {
									errorString = " " + errorString + ui.message("conceptmanagementapps.file.maptype.error")
									        + " ";
								} else {
									try {
										conceptMap.setConceptMapType(cs.getConceptMapTypeByName(mapTypeName));
									}
									catch (APIException e) {
										errorString = "\"" + e + "\"" + "," + line + "\n";
										e.printStackTrace();
									}
								}
								columnNumber++;
								break;
							case 2:
								sourceName = t.replace("\"", "").trim();
								if (t.replace("\"", "").trim().length() <= 1) {
									errorString = " " + errorString
									        + ui.message("conceptmanagementapps.file.sourcename.error") + " ";
									
								}
								columnNumber++;
								break;
							case 3:
								sourceCode = t.replace("\"", "").trim();
								source = cs.getConceptSource(Integer.valueOf(sourceId.trim()));
								if (t.replace("\"", "").trim().length() <= 1
								        || cs.getConceptReferenceTermByCode(t, source) == null) {
									errorString = " " + errorString
									        + ui.message("conceptmanagementapps.file.sourcecode.error") + " ";
								} else {
									try {
										ConceptReferenceTerm refTerm = cs.getConceptReferenceTermByCode(t, source);
										conceptMap.setConceptReferenceTerm(refTerm);
									}
									catch (APIException e) {
										errorString = "\"" + e + "\"" + "," + line + "\n";
										e.printStackTrace();
									}
									
								}
								columnNumber++;
								break;
							case 4:
								if (errorString.length() < 1) {
									
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
										try {
											cs.saveConcept(concept);
										}
										catch (APIException e) {
											errorString = "\"" + e + "\"" + "," + line + "\n";
											e.printStackTrace();
										}
										
									}
								}
								columnNumber++;
								break;
							case 5:
								columnNumber++;
								break;
							case 6:
								columnNumber++;
								break;
							case 7:
								columnNumber++;
								break;
							case 8:
								columnNumber++;
								break;
							case 9:
								columnNumber++;
								break;
							case 10:
								if (!header) {
									if (t.startsWith("\"") && t.length() > 2) {
										mappings += t;
										if (!foundMapping) {
											columnNumber = 1;
											mappings = "";
										}
									} else {
										columnNumber = 1;
										mappings = "";
									}
								}
								
								break;
							default:
								columnNumber++;
								log.error(ui.message("conceptmanagementapps.file.line.error") + columnNumber);
								break;
						
						}
					}
				}
				header = false;
				if (errorString.length() > 1) {
					linesWithErrors.add(errorString + "," + line + "\n");
				}
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
		return writeToFile(linesWithErrors, sourceId);
		
	}
	
	public void get(UiSessionContext sessionContext, PageModel model) throws Exception {
		
	}
	
	private FileDownload writeToFile(ArrayList<String> lines, String sourceId) {
		String line = "";
		for (String aline : lines) {
			line += aline;
		}
		String s = new SimpleDateFormat("dMy_Hm").format(new Date());
		
		String contentType = "text/csv;charset=UTF-8";
		String errorFilename = "conceptsMissingMappingsErrors" + s + "_" + sourceId + ".csv";
		return new FileDownload(errorFilename, contentType, line.getBytes());
	}
	
}
