package com.referral.ReferralAPI.repository;

import com.referral.ReferralAPI.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByGeneratedCode(String generatedCode);
}
