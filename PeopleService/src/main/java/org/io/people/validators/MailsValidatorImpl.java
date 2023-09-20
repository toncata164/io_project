package org.io.people.validators;

import org.io.people.entities.Mail;

public class MailsValidatorImpl implements Validator<Mail> {
    private final String EMAIL_REGEX = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
    @Override
    public boolean validate(Mail mail) {
        if(mail == null){
            return true;
        }
        if(mail.getType() == null){
            return false;
        }
        if(!mail.getEmail().matches(EMAIL_REGEX)){
            return false;
        }
        return true;
    }
}
