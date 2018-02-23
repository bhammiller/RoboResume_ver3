package com.example.demo.Models;

import javax.persistence.*;
import java.util.List;

@Entity
public class JobSkills {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String jobSkillName;

    //Connections to other Classes
    @ManyToMany(mappedBy = "jobSkillsList")
    private List<SiteJobs> siteJobsList;

    public JobSkills() {
    }

    public List<SiteJobs> getSiteJobsList() {
        return siteJobsList;
    }

    public void setSiteJobsList(List<SiteJobs> siteJobsList) {
        this.siteJobsList = siteJobsList;
    }

    // Variable Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getJobSkillName() {
        return jobSkillName;
    }

    public void setJobSkillName(String jobSkillName) {
        this.jobSkillName = jobSkillName;
    }
}
