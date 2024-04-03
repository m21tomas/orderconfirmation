package com.gudev.referral.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.gudev.referral.exception.CodeNotFoundException;
import com.gudev.referral.exception.MaxPersonCountException;
import com.gudev.referral.exception.UserNotFoundException;
import com.gudev.referral.model.User;
import com.gudev.referral.repository.UserRepository;
import com.gudev.referral.util.RandomStringGenerator;

@Service
public class UserService {

    @Value("${defaultRefCount}")
    private int defaultRefCount;

    private final UserRepository repository;

    private final RandomStringGenerator stringGenerator;


    public UserService(UserRepository repository,
                       RandomStringGenerator stringGenerator) {
        this.repository = repository;
        this.stringGenerator = stringGenerator;
    }


    public User getUserByUsername(String username) {
        return repository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(" user not found!"));
    }

    public List<User> getAllByReferralCode(String referralCode) {
        if (!repository.existsUserByReferralCode(referralCode)) {
            throw new UserNotFoundException(" user not found with given code!");
        }
        return repository.findAllByReferralCode(referralCode);
    }
    
    public List<User> getAllByReferredCode(String referredCode) {
        if (!repository.existsUserByReferralCode(referredCode)) {
            throw new UserNotFoundException(" user not found with given code!");
        }
        return repository.findAllByReferredByCode(referredCode);
    }

    public User createUser(User user) {

        if (user.getReferredByCode() == null || user.getReferredByCode().isEmpty()) {
            throw new CodeNotFoundException("referral code not found");
        }

        int referredUserCount = getAllByReferredCode(user.getReferredByCode()).size();
        if (referredUserCount < defaultRefCount) {
            user.setReferralCode(generateCode());
        } else {
            throw new MaxPersonCountException("max person count has been reached!");
        }

        return repository.save(user);
    }

    private String generateCode() {
        String generated = "";
        do {
            generated = stringGenerator.generate();
        } while (repository.existsUserByReferralCode(generated));

        return generated;
    }


}
