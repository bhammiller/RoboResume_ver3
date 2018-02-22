package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class ExpResume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=1, message="Please enter your job title")
    private String jobtitle;

    @NotNull
    @Size(min=1, message = "please enter your employer")
    private String employer;

    @NotNull
    @Size(min=1, message = "please enter starting year")
    private String yearsstart;

    @NotNull
    @Size(min=1, message = "please enter end date")
    private String yearsend;

    @NotNull
    @Size(min=1, message = "please enter your duties")
    private String duties;

    // Connections
    @ManyToMany(mappedBy = "expResumeList")
    private List<SiteApplicants> siteApplicantsExpList;

    public ExpResume() {
    }

    public List<SiteApplicants> getWholeExpResumeList() {
        return siteApplicantsExpList;
    }

    public void setWholeExpResumeList(List<SiteApplicants> wholeExpResumeList) {
        this.siteApplicantsExpList = wholeExpResumeList;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobtitle() {
        return jobtitle;
    }

    public void setJobtitle(String jobtitle) {
        this.jobtitle = jobtitle;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getYearsstart() {
        return yearsstart;
    }

    public void setYearsstart(String yearsstart) {
        this.yearsstart = yearsstart;
    }

    public String getYearsend() {
        return yearsend;
    }

    public void setYearsend(String yearsend) {
        this.yearsend = yearsend;
    }

    public String getDuties() {
        return duties;
    }

    public void setDuties(String duties) {
        this.duties = duties;
    }
}
