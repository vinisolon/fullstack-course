package com.vinisolon.fullstackcourse.configuration;

import com.vinisolon.fullstackcourse.services.DBService;
import com.vinisolon.fullstackcourse.services.email.EmailService;
import com.vinisolon.fullstackcourse.services.email.SmtpEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevProfileConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String dbGenerationStrategy;

    @Bean
    public boolean instantiateDatabase() throws ParseException {
        if (!dbGenerationStrategy.equals("create"))
            return false;

        dbService.getTestProfileDatabaseInstance();
        return true;
    }

    @Bean
    public EmailService emailService() {
        return new SmtpEmailService();
    }

}
