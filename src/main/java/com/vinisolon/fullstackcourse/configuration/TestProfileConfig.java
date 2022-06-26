package com.vinisolon.fullstackcourse.configuration;

import com.vinisolon.fullstackcourse.services.DBService;
import com.vinisolon.fullstackcourse.services.email.EmailService;
import com.vinisolon.fullstackcourse.services.email.MockEmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TestProfileConfig {

    @Bean
    public boolean instantiateDatabase(DBService dbService) throws ParseException {
        dbService.getTestProfileDatabaseInstance();
        return true;
    }

    @Bean
    public EmailService instantiateEmailService() {
        return new MockEmailService();
    }

}
