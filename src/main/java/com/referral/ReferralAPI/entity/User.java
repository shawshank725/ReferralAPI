package com.referral.ReferralAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="name")
    private String name = "";

    @NotBlank(message = "Email is required")
    @Column(name = "email")
    private String email;

    @Column(name="generated_code")
    private String generatedCode = "";

    @Column(name="referred_code")
    private String referredCode = "";

    @Column(name = "profile_completed")
    private String profileCompleted;

    public User(){}

    public User(String name, String email, String generatedCode, String referredCode, String profile_completed) {
        this.name = name;
        this.email = email;
        this.generatedCode = generatedCode;
        this.referredCode = referredCode;
        this.profileCompleted = profile_completed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfileCompleted() {
        return profileCompleted;
    }

    public void setProfileCompleted(String profileCompleted) {
        this.profileCompleted = profileCompleted;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @NotBlank(message = "Email is required") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "Email is required") String email) {
        this.email = email;
    }

    public String getGeneratedCode() {
        return generatedCode;
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public String getReferredCode() {
        return referredCode;
    }

    public void setReferredCode(String referredCode) {
        this.referredCode = referredCode;
    }

    public String getProfile_completed() {
        return profileCompleted;
    }

    public void setProfile_completed(String profile_completed) {
        this.profileCompleted = profile_completed;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", generatedCode='" + generatedCode + '\'' +
                ", referredCode='" + referredCode + '\'' +
                ", profile_completed='" + profileCompleted + '\'' +
                '}';
    }
}
