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
 * Copyright (C) OpenMRS, LLC. All Rights Reserved.
 */
package org.openmrs.module.conceptmanagementapps.api.db;

import java.util.List;

import org.openmrs.api.db.DAOException;
import org.openmrs.module.conceptmanagementapps.api.ConceptManagementAppsService;

import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptSource;

/**
 * Database methods for {@link ConceptManagementAppsService}.
 */
public interface ConceptManagementAppsDAO {
	
	public List<Concept> getUnmappedConcepts(ConceptSource source, List<ConceptClass> classes) throws DAOException;
}
