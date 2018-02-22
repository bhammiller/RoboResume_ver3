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
public class SiteApplicants {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    //Connects with username and password
    @ManyToMany
    private List<AppUser> appUserList;

    public List<AppUser> getAppUserList() {
        return appUserList;
    }

    public void setAppUserList(List<AppUser> appUserList) {
        this.appUserList = appUserList;
    }

    // Creates the resume for the Applicant
    @ManyToMany
    private List<EducationResume> educationResumeList;
    @ManyToMany
    private List<ExpResume> expResumeList;
    @ManyToMany
    private List<ResumeReferences> resumeReferencesList;
    @ManyToMany
    private List<SkillsResume> skillsResumeList;
    @ManyToMany
    private List<WholeResume> wholeResumeList;
    @ManyToMany
    private List<CoverLetter> coverLetterList;
    @ManyToMany
    private List<SummaryResume> summaryResumeList;


    public SiteApplicants() {
        this.educationResumeList = new ArrayList<>();
        this.expResumeList = new ArrayList<>();
        this.resumeReferencesList = new ArrayList<>();
        this.skillsResumeList = new ArrayList<>();
        this.wholeResumeList = new ArrayList<>();
        this.appUserList = new ArrayList<>();
        this.summaryResumeList= new ArrayList<>();
        this.coverLetterList=new ArrayList<>();
    }
    public void addEducation(EducationResume e){
        this.educationResumeList.add(e);
    }

    public void addExperience(ExpResume x){
        this.expResumeList.add(x);
    }

    public void addReferances(ResumeReferences r){
        this.resumeReferencesList.add(r);
    }

    public void addSkills(SkillsResume s){
        this.skillsResumeList.add(s);
    }

    public void addWhole(WholeResume w){
        this.wholeResumeList.add(w);
    }

    public void addCredentials(AppUser a){
        this.appUserList.add(a);
    }

    public void addCover(CoverLetter c){
        this.coverLetterList.add(c);
    }
     public void addSummary(SummaryResume sr){
        this.summaryResumeList.add(sr);
     }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<EducationResume> getEducationResumeList() {
        return educationResumeList;
    }

    public void setEducationResumeList(List<EducationResume> educationResumeList) {
        this.educationResumeList = educationResumeList;
    }

    public List<ExpResume> getExpResumeList() {
        return expResumeList;
    }

    public void setExpResumeList(List<ExpResume> expResumeList) {
        this.expResumeList = expResumeList;
    }

    public List<ResumeReferences> getResumeReferencesList() {
        return resumeReferencesList;
    }

    public void setResumeReferencesList(List<ResumeReferences> resumeReferencesList) {
        this.resumeReferencesList = resumeReferencesList;
    }

    public List<SkillsResume> getSkillsResumeList() {
        return skillsResumeList;
    }

    public void setSkillsResumeList(List<SkillsResume> skillsResumeList) {
        this.skillsResumeList = skillsResumeList;
    }

    public List<WholeResume> getWholeResumeList() {
        return wholeResumeList;
    }

    public void setWholeResumeList(List<WholeResume> wholeResumeList) {
        this.wholeResumeList = wholeResumeList;
    }

    public List<CoverLetter> getCoverLetterList() {
        return coverLetterList;
    }

    public void setCoverLetterList(List<CoverLetter> coverLetterList) {
        this.coverLetterList = coverLetterList;
    }

    public List<SummaryResume> getSummaryResumeList() {
        return summaryResumeList;
    }

    public void setSummaryResumeList(List<SummaryResume> summaryResumeList) {
        this.summaryResumeList = summaryResumeList;
    }
}
