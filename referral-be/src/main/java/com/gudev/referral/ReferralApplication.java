package com.gudev.referral;

import com.gudev.referral.model.User;
import com.gudev.referral.repository.UserRepository;
import com.gudev.referral.util.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReferralApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(ReferralApplication.class, args);
    }


    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Autowired
    UserRepository userRepository;

    @Autowired
    RandomStringGenerator randomStringGenerator;

    @Override
    // THIS is the first created user
    public void run(String... args) throws Exception {
    	User existingUser = userRepository.findByUsername("grkn").orElse(null);
    	if(existingUser == null) {
	        User usr = new User();
	             usr.setName("gurkan");
	             usr.setName("grkn");
	             usr.setSurname("surname");
	             usr.setReferralCode(randomStringGenerator.generate());
	             
	        userRepository.save(usr);
    	} else if (existingUser.getReferralCode().length()<5) {
    		existingUser.setReferralCode(randomStringGenerator.generate());
    		userRepository.save(existingUser);
    	}
    }
}
