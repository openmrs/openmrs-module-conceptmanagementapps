package org.openmrs.module.conceptmanagementapps;

public class ConceptSourceList {
	
	private String sourceName;
	
	private String sourceId;
	
	/**
	 * @param ConceptSourceList
	 */
	public ConceptSourceList() {
	}
	
	/**
	 * @return the sourceName
	 */
	public String getSourceName() {
		return this.sourceName;
	}
	
	/**
	 * @param sourceName the sourceName to set
	 */
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	
	/**
	 * @return the sourceId
	 */
	public String getSourceId() {
		return this.sourceId;
	}
	
	/**
	 * @param sourceId the sourceId to set
	 */
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	
}
