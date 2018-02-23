package com.example.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class SiteJobs {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String jobTitle;

    private String jobDescription;

    private String jobSalaryRange;

    //Connections to other Classes
    @ManyToMany
    private List<JobSkills> jobSkillsList;

    public SiteJobs(List<JobSkills> jobSkillsList) {
        this.jobSkillsList = jobSkillsList;
    }

    public void addJobSkills(JobSkills j){
        this.jobSkillsList.add(j);
    }

    public List<JobSkills> getJobSkillsList() {
        return jobSkillsList;
    }

    public void setJobSkillsList(List<JobSkills> jobSkillsList) {
        this.jobSkillsList = jobSkillsList;
    }

    @ManyToMany(mappedBy = "siteJobsOrgList")
    private List<SiteOrganizations> siteOrganizationsList;

    public SiteJobs() {
    }

    public List<SiteOrganizations> getSiteOrganizationsList() {
        return siteOrganizationsList;
    }

    public void setSiteOrganizationsList(List<SiteOrganizations> siteOrganizationsList) {
        this.siteOrganizationsList = siteOrganizationsList;
    }

    // Variable Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobSalaryRange() {
        return jobSalaryRange;
    }

    public void setJobSalaryRange(String jobSalaryRange) {
        this.jobSalaryRange = jobSalaryRange;
    }
}
