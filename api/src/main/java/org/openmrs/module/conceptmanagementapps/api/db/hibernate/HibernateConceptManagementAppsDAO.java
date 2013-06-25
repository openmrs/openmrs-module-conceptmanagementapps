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
package org.openmrs.module.conceptmanagementapps.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.openmrs.Concept;
import org.openmrs.api.db.DAOException;
import org.openmrs.module.conceptmanagementapps.api.db.ConceptManagementAppsDAO;

/**
 * It is a default implementation of {@link ConceptManagementAppsDAO}.
 */
public class HibernateConceptManagementAppsDAO implements ConceptManagementAppsDAO {
	
	protected final Log log = LogFactory.getLog(this.getClass());
	
	private SessionFactory sessionFactory;
	
	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Concept> getUnmappedConcepts(String sourceId, String classes) throws DAOException {
		
		String hql = "";
		hql += "select distinct concept";
		hql += " from Concept as concept";
		hql += " left join concept.conceptMappings as conceptMappings where conceptMappings is NULL";
		hql += " and";
		hql += " concept.retired = false";
		hql += " and";
		hql += "(" + classes + ")";
		String hql2 = "";
		hql2 += "select distinct concept";
		hql2 += " from Concept as concept";
		hql2 += " inner join concept.conceptMappings as conceptMappings";
		hql2 += " inner join conceptMappings.conceptReferenceTerm as conceptReferenceTerm";
		hql2 += " where";
		hql2 += " conceptReferenceTerm.conceptSource.conceptSourceId != " + sourceId;
		hql += " and";
		hql += " concept.retired = false";
		hql2 += " and";
		hql2 += "(" + classes + ")";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		Query query2 = sessionFactory.getCurrentSession().createQuery(hql2);
		List<Concept> list1 = query.list();
		List<Concept> list2 = query2.list();
		list1.addAll(list2);
		return list1;
	}
}
