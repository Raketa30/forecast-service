package ru.challenge.rateservice.validator;

public interface Validator<T> {
    boolean isValid(T data);
}
