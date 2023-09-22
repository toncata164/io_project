package org.io.people.validators;

public interface Validator<T> {
    boolean validate(T t);
}
