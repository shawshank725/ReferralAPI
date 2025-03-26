package com.referral.ReferralAPI.service;

import com.referral.ReferralAPI.entity.ReferralTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReferService {

    ReferralTable save(ReferralTable theReferrableTable);

    List<ReferralTable> findAll();

}
