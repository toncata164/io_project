package org.io.people.validators;

import org.io.people.entities.Address;
import org.io.people.entities.Mail;
import org.io.people.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;

public class PersonValidatorImpl implements Validator<Person>{
    @Autowired
    Validator<Mail> mailValidator;
    @Autowired
    Validator<Address> addressValidator;

    private boolean validateFullName(String fullName){
        if(fullName == null || fullName.isEmpty()){
            return false;
        }
        char[] symbols = fullName.toCharArray();
        int unicodeUpperA = 0x0041;
        int unicodeUpperZ = 0x005A;
        int unicodeLowerA = 0x0061;
        int unicodeLowerZ = 0x007A;
        int unicodeUpperCyrillicA = 0x0410;
        int unicodeLowerCyrillicYA = 0x044F;
        for(char symbol : symbols){
            if(!((symbol >= unicodeUpperA && symbol <= unicodeUpperZ) ||
                    (symbol >= unicodeLowerA && symbol <= unicodeLowerZ) ||
                    (symbol >= unicodeUpperCyrillicA && symbol <= unicodeLowerCyrillicYA) ||
                    symbol == ' ' || symbol == '-')){
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean validate(Person person) {
        if(person == null){
            return false;
        }
        if(person.getEmail() != null && !person.getEmail().isEmpty()) {
            Boolean areMailsValid = person.getEmail().stream().reduce(Boolean.TRUE, (b, m) -> b && mailValidator.validate(m), (b, bb) -> b && bb);
            if (!areMailsValid) {
                return false;
            }
        }
        if(person.getAddress() != null && !person.getAddress().isEmpty()) {
            Boolean areAddressesValid = person.getAddress().stream().reduce(Boolean.TRUE, (b, a) -> b && addressValidator.validate(a), (b, bb) -> b && bb);
            if (!areAddressesValid) {
                return false;
            }
        }
        if(!validateFullName(person.getFullName())){
            return false;
        }
        if(person.getPin() != null &&
                (person.getPin().length() != 10 || !person.getPin().matches("[0-9]{10}"))){
            return false;
        }
        return true;
    }
}
