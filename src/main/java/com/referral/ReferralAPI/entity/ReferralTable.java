package com.referral.ReferralAPI.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "referral_table")
public class ReferralTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="referrerCode")
    private String referrerCode = "";

    @Column(name = "referred_code")
    private String referredCode;

    @Column(name="successful")
    private String successful = "False";

    public ReferralTable(){}

    public ReferralTable(String referrer_code, String referred_code, String successful) {
        this.referrerCode = referrer_code;
        this.referredCode = referred_code;
        this.successful = successful;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReferrerCode() {
        return referrerCode;
    }

    public void setReferrerCode(String referrerCode) {
        this.referrerCode = referrerCode;
    }

    public String getReferredCode() {
        return referredCode;
    }

    public void setReferredCode(String referredCode) {
        this.referredCode = referredCode;
    }

    public String getSuccessful() {
        return successful;
    }

    public void setSuccessful(String successful) {
        this.successful = successful;
    }

    @Override
    public String toString() {
        return "ReferralTable{" +
                "id=" + id +
                ", referrerCode='" + referrerCode + '\'' +
                ", referred_code='" + referredCode + '\'' +
                ", successful='" + successful + '\'' +
                '}';
    }
}
