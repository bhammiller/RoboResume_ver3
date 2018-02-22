package com.example.demo.Models;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
