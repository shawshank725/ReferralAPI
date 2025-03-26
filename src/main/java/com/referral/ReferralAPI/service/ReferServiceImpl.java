package com.referral.ReferralAPI.service;

import com.referral.ReferralAPI.entity.ReferralTable;
import com.referral.ReferralAPI.repository.ReferRepository;
import com.referral.ReferralAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReferServiceImpl implements ReferService{

    private ReferRepository referRepository;

    @Autowired
    public ReferServiceImpl(ReferRepository theReferRepository){
        referRepository = theReferRepository;
    }

    @Override
    @Transactional
    public ReferralTable save(ReferralTable theReferrableTable) {
        return referRepository.save(theReferrableTable);
    }

    @Override
    public List<ReferralTable> findAll() {
        return referRepository.findAll();
    }
}
