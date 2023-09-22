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
            throw new RuntimeException("Person name must be not empty!");
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
                throw new RuntimeException("Person name must contain latin or cyrillic letters, space, -");
            }
        }
        return true;
    }
    @Override
    public boolean validate(Person person) {
        if(person == null){
            throw new RuntimeException("Person can't be null!");
        }
        if(person.getEmail() != null && !person.getEmail().isEmpty()) {
            person.getEmail().forEach(email->mailValidator.validate(email));
        }
        if(person.getAddress() != null && !person.getAddress().isEmpty()) {
            person.getAddress().forEach(address -> addressValidator.validate(address));
        }
        if(!validateFullName(person.getFullName())){
            throw new RuntimeException("Name is invalid!");
        }
        if(person.getPin() != null &&
                (person.getPin().length() != 10 || !person.getPin().matches("[0-9]{10}"))){
            if(person.getPin().length() != 10) {
                throw new RuntimeException("Pin must be exactly 10 digits long!");
            }
            else{
                throw new RuntimeException("Pin must contain digits only!");
            }
        }
        return true;
    }
}
