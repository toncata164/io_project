package org.io.people.config;

import org.io.people.entities.Address;
import org.io.people.entities.Mail;
import org.io.people.entities.Person;
import org.io.people.services.PeopleService;
import org.io.people.validators.AddressValidatorImpl;
import org.io.people.validators.MailsValidatorImpl;
import org.io.people.validators.PersonValidatorImpl;
import org.io.people.validators.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PeopleServiceConfiguration {
    @Bean
    public PeopleService getPeopleService(){
        return new PeopleService();
    }
    @Bean
    public Validator<Person> getPersonValidator(){
        return new PersonValidatorImpl();
    }
    @Bean
    public Validator<Address> getAddressValidator(){
        return new AddressValidatorImpl();
    }
    @Bean
    public Validator<Mail> getMailValidator(){
        return new MailsValidatorImpl();
    }
}
