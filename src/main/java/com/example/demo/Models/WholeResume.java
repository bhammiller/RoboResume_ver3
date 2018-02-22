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
public class WholeResume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;


    @Size(min=1, message = "Please enter your name")
    private String name;


    @Email(message = "Please your email address")
    private String email;

    @Size(min=1)
    private String yousummary;

    @Size(min=1)
    private String coverletter;

    @URL
    private String youimage;

    //Connections to Username and Password




    // Connections to Other Resume Parts
    @ManyToMany(mappedBy = "wholeResumeList")
    private List<SiteApplicants> siteApplicantsWholeList;

    public List<SiteApplicants> getSiteApplicantsWholeList() {
        return siteApplicantsWholeList;
    }

    public void setSiteApplicantsWholeList(List<SiteApplicants> siteApplicantsWholeList) {
        this.siteApplicantsWholeList = siteApplicantsWholeList;
    }

    public WholeResume() {
    }

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getYousummary() {
        return yousummary;
    }

    public void setYousummary(String yousummary) {
        this.yousummary = yousummary;
    }

    public String getCoverletter() {
        return coverletter;
    }

    public void setCoverletter(String coverletter) {
        this.coverletter = coverletter;
    }

    public String getYouimage() {
        return youimage;
    }

    public void setYouimage(String youimage) {
        this.youimage = youimage;
    }
}
