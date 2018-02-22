package com.example.demo.Models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class SummaryResume {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=1)
    private String yousummary;

    @ManyToMany(mappedBy = "summaryResumeList")
    private List<SiteApplicants> applicantsSummaryList;

    public SummaryResume() {
    }

    public List<SiteApplicants> getApplicantsSummaryList() {
        return applicantsSummaryList;
    }

    public void setApplicantsSummaryList(List<SiteApplicants> applicantsSummaryList) {
        this.applicantsSummaryList = applicantsSummaryList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getYousummary() {
        return yousummary;
    }

    public void setYousummary(String yousummary) {
        this.yousummary = yousummary;
    }
}
