package com.phkj.entity.environmental;

public class StAppletEnviron {

    private String title;

    private String reportType;

    private String position;

    private String content;

    private String eventSourceId;

    private String peopleName;

    private String orgCode;

    private String topOrgCode;

    private String files;



    public StAppletEnviron() {
    }

    public StAppletEnviron(String title, String reportType, String position, String content, String eventSourceId, String peopleName, String orgCode, String topOrgCode, String files) {
        this.title = title;
        this.reportType = reportType;
        this.position = position;
        this.content = content;
        this.eventSourceId = eventSourceId;
        this.peopleName = peopleName;
        this.orgCode = orgCode;
        this.topOrgCode = topOrgCode;
        this.files = files;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEventSourceId() {
        return eventSourceId;
    }

    public void setEventSourceId(String eventSourceId) {
        this.eventSourceId = eventSourceId;
    }

    public String getPeopleName() {
        return peopleName;
    }

    public void setPeopleName(String peopleName) {
        this.peopleName = peopleName;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getTopOrgCode() {
        return topOrgCode;
    }

    public void setTopOrgCode(String topOrgCode) {
        this.topOrgCode = topOrgCode;
    }

    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }
}
