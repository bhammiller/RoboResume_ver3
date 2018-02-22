package com.example.demo.Models;

import org.hibernate.validator.constraints.Email;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ResumeReferences {
    @Id

    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "please enter a reference")
    private String referals;

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

