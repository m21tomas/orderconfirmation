package com.gudev.referral.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gudev.referral.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    List<User> findAllByReferredByCode(String code);
    List<User> findAllByReferralCode(String code);
    boolean existsUserByReferralCode(String code);


}
