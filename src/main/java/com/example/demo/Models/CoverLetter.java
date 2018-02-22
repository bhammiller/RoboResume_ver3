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
public class CoverLetter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Size(min=1)
    private String coverletter;

    @ManyToMany(mappedBy = "coverLetterList")
    private List<SiteApplicants> applicantsCoverList;

    public CoverLetter() {
    }

    public List<SiteApplicants> getApplicantsCoverList() {
        return applicantsCoverList;
    }

    public void setApplicantsCoverList(List<SiteApplicants> applicantsCoverList) {
        this.applicantsCoverList = applicantsCoverList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoverletter() {
        return coverletter;
    }

    public void setCoverletter(String coverletter) {
        this.coverletter = coverletter;
    }
}
