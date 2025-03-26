package com.referral.ReferralAPI.service;

import com.referral.ReferralAPI.entity.User;

import java.util.List;

public interface UserService {

    List<User> findAll();

    User save(User thePost);

    User findUserByGeneratedCode(String generatedCode);
}
