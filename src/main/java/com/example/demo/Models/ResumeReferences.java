package com.example.demo.Models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class ResumeReferences {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "please enter a reference")
    private String referals;

    // Connections
    @ManyToMany(mappedBy = "resumeReferencesList")
    private List<SiteApplicants> wholeRefResumeList;

    public ResumeReferences() {
    }

    public List<SiteApplicants> getWholeRefResumeList() {
        return wholeRefResumeList;
    }

    public void setWholeRefResumeList(List<SiteApplicants> wholeRefResumeList) {
        this.wholeRefResumeList = wholeRefResumeList;
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getReferals() {
        return referals;
    }

    public void setReferals(String referals) {
        this.referals = referals;
    }
}

