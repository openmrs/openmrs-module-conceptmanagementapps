package org.openmrs.module.conceptmanagementapps.page.controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.openmrs.module.appui.UiSessionContext;
import org.openmrs.ui.framework.page.PageModel;

public class UploadSpreadsheetPageController {
	
	public void post(@RequestParam("spreadsheet") InputStream spreadsheetInStream) {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {
			br = new BufferedReader(new InputStreamReader(spreadsheetInStream));
			while ((line = br.readLine()) != null) {
				String[] items = line.split(cvsSplitBy);
				
			}
			
		}
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			if (br != null) {
				try {
					br.close();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	

	public void post(@RequestParam("spreadsheet") MultipartFile spreadsheetFile) {
	}
	
	public void get(UiSessionContext sessionContext, PageModel model) throws Exception {
	}
	
}
