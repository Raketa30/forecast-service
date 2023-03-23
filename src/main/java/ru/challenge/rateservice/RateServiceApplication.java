package ru.challenge.rateservice;

import ru.challenge.rateservice.factory.ApplicationFactory;

public class RateServiceApplication {
    public static void main(String[] args) {
        ApplicationFactory.create().start();
    }
}