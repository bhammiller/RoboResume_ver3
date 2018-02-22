package com.example.demo.Models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
public class SkillsResume {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    @Size(min=1, message = "Please enter a skill")
    private String particularskill;

    @NotNull
    @Size(min=1, message = "Please enter skill level")
    private String skilllevel;

    // Connections
    @ManyToMany(mappedBy = "skillsResumeList")
    private List<SiteApplicants> wholeSkillResumeList;

    public SkillsResume() {
    }

    public List<SiteApplicants> getWholeSkillResumeList() {
        return wholeSkillResumeList;
    }

    public void setWholeSkillResumeList(List<SiteApplicants> wholeSkillResumeList) {
        this.wholeSkillResumeList = wholeSkillResumeList;
    }

    //Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getParticularskill() {
        return particularskill;
    }

    public void setParticularskill(String particularskill) {
        this.particularskill = particularskill;
    }

    public String getSkilllevel() {
        return skilllevel;
    }

    public void setSkilllevel(String skilllevel) {
        this.skilllevel = skilllevel;
    }
}
