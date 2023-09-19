package org.io.people.config;

import org.io.people.services.PeopleService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleServiceConfiguration {
    @Bean
    public PeopleService getPeopleService(){
        return new PeopleService();
    }
}
