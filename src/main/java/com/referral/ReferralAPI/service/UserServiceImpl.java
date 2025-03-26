package com.referral.ReferralAPI.service;

import com.referral.ReferralAPI.entity.User;
import com.referral.ReferralAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository theUserRepository){
        userRepository = theUserRepository;
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    @Transactional
    public User save(User theUser) {
        return userRepository.save(theUser);
    }

    @Override
    public User findUserByGeneratedCode(String generatedCode){
        return userRepository.findByGeneratedCode(generatedCode);
    }
}
