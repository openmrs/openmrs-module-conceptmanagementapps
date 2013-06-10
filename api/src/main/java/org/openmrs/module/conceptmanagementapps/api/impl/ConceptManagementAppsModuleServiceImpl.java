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
package org.openmrs.module.conceptmanagementapps.api.impl;

import org.openmrs.api.impl.BaseOpenmrsService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.module.conceptmanagementapps.api.ConceptManagementAppsModuleService;
import org.openmrs.module.conceptmanagementapps.api.db.ConceptManagementAppsModuleDAO;

/**
 * It is a default implementation of {@link ConceptManagementAppsModuleService}.
 */
public class ConceptManagementAppsModuleServiceImpl extends BaseOpenmrsService implements ConceptManagementAppsModuleService {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private ConceptManagementAppsModuleDAO dao;
	
	/**
     * @param dao the dao to set
     */
    public void setDao(ConceptManagementAppsModuleDAO dao) {
	    this.dao = dao;
    }
    
    /**
     * @return the dao
     */
    public ConceptManagementAppsModuleDAO getDao() {
	    return dao;
    }
}