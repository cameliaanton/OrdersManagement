package BusinessLogic.validators;

import Model.Client;
import Presentation.ViewClients;

import javax.swing.*;
import java.awt.*;

/**
 * Validator for validating the age of a client.
 */
public class ClientAgeValidator implements Validator<Client> {
    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 90;

    /**
     * Validates the age of a client.
     * @param age the age to validate
     * @return 1 if the age is valid, 0 otherwise
     */
    public int validate(int age) {
        int c = 0;
        if (age < MIN_AGE || age > MAX_AGE) {
            c = 0;
            // throw new IllegalArgumentException("The Student Age limit is not respected!");
        } else {
            c = 1;
        }
        return c;
    }

    /**
     * Validates the age of a client object.
     * @param t the client object to validate
     * @throws IllegalArgumentException if the age is not within the valid range
     */
    public void validate(Client t) {
        if (t.getAge() < MIN_AGE || t.getAge() > MAX_AGE) {
            throw new IllegalArgumentException("The Student Age limit is not respected!");
        }
    }
}
