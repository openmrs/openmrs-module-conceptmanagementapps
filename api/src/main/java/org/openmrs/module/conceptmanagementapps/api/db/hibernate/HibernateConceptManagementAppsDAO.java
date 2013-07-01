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
package org.openmrs.module.conceptmanagementapps.api.db.hibernate;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.DistinctRootEntityResultTransformer;
import org.openmrs.Concept;
import org.openmrs.ConceptClass;
import org.openmrs.ConceptMap;
import org.openmrs.ConceptSource;
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
	public List<Concept> getUnmappedConcepts(ConceptSource conceptSource, List<ConceptClass> conceptClasses)
	    throws DAOException {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Concept.class);
		Criteria conceptMapCrit = sessionFactory.getCurrentSession().createCriteria(Concept.class, "concept");
		conceptMapCrit.createAlias("conceptMappings", "conceptMappings");
		conceptMapCrit.createAlias("conceptMappings.conceptReferenceTerm", "term");
		conceptMapCrit.add(Restrictions.eq("term.conceptSource", conceptSource));
		criteria.createAlias("conceptMappings", "conceptMappings");
		//we don't want any mappings that have a concept which has a mapping to our source
		criteria.add(Restrictions.not(Restrictions.in("conceptMappings.concept", (List<Concept>) conceptMapCrit.list())));
		// ignore retired concepts
		criteria.add(Restrictions.eq("retired", false));
		// only want concepts with the following conceptClass(s)
		for (ConceptClass conceptClass : conceptClasses) {
			criteria.add(Restrictions.eq("conceptClass", conceptClass));
		}
		// we only want distinct concepts
		criteria.setResultTransformer(DistinctRootEntityResultTransformer.INSTANCE);
		//we also want all concepts with out a mapping at all because that also means it is not mapped to our source
		String hql = "";
		hql += "select distinct concept";
		hql += " from Concept as concept";
		hql += " left join concept.conceptMappings as conceptMappings where conceptMappings is NULL";
		hql += " and";
		hql += " concept.retired = false";
		hql += " and";
		int i = 1;
		for (ConceptClass conceptClass : conceptClasses) {
			
			if (i < conceptClasses.size()) {
				hql += " concept.conceptClass.conceptClassId=" + conceptClass.getConceptClassId();
				hql += " or";
				
			} else {
				hql += " concept.conceptClass.conceptClassId=" + conceptClass.getConceptClassId();
			}
			i++;
		}
		
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<Concept> list2 = query.list();
		List<Concept> list1 = criteria.list();
		list1.addAll(list2);
		return (List<Concept>) list1;
		
	}
	
}
