package org.io.people.validators;

import org.io.people.entities.Address;

public class AddressValidatorImpl implements Validator<Address> {
    @Override
    public boolean validate(Address address) {
        if(address == null){
            return true;
        }
        if(address.getType() == null || address.getType().isEmpty()){
            throw new RuntimeException("Address type must be not empty!");
        }
        return true;
    }
}
