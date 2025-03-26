package com.referral.ReferralAPI.repository;

import com.referral.ReferralAPI.entity.ReferralTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReferRepository extends JpaRepository<ReferralTable, Integer> {
}
