package org.io.people.validators;

import org.io.people.entities.Mail;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MailsValidatorImpl implements Validator<Mail> {
    private final String EMAIL_REGEX = "^(.+)@(.+)$";
    @Override
    public boolean validate(Mail mail) {
        if(mail == null){
            return true;
        }
        if(mail.getType() == null){
            throw new RuntimeException("E-mail type must be not null!");
        }
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(mail.getEmail());
        if(!matcher.matches()){
            throw new RuntimeException("E-mail "+mail.getEmail()+" is not valid!");
        }
        return true;
    }
}
