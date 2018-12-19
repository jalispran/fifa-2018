package com.fifa.official;

public class Part {

	private String datasource;
	private Object resourceId;
	private String renderedContent;
	private Data data;
	public String getDatasource() {
		return datasource;
	}
	public void setDatasource(String datasource) {
		this.datasource = datasource;
	}
	public Object getResourceId() {
		return resourceId;
	}
	public void setResourceId(Object resourceId) {
		this.resourceId = resourceId;
	}
	public String getRenderedContent() {
		return renderedContent;
	}
	public void setRenderedContent(String renderedContent) {
		this.renderedContent = renderedContent;
	}
	public Data getData() {
		return data;
	}
	public void setData(Data data) {
		this.data = data;
	}
	
}
