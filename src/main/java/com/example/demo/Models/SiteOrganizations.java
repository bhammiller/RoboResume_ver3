package com.example.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteOrganizations {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String organizationName;

    private String organizationDescription;

    //Connections to other Classes
    @ManyToMany
    private List<SiteJobs> siteJobsOrgList;

    public SiteOrganizations(List<SiteJobs> siteJobsOrgList) {
        this.siteJobsOrgList = siteJobsOrgList;
    }

    public List<SiteJobs> getSiteJobsOrgList() {
        return siteJobsOrgList;
    }

    public void setSiteJobsOrgList(List<SiteJobs> siteJobsOrgList) {
        this.siteJobsOrgList = siteJobsOrgList;
    }

    // Variable Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrganizationName() {
        return organizationName;
    }

    public void setOrganizationName(String organizationName) {
        this.organizationName = organizationName;
    }

    public String getOrganizationDescription() {
        return organizationDescription;
    }

    public void setOrganizationDescription(String organizationDescription) {
        this.organizationDescription = organizationDescription;
    }
}
