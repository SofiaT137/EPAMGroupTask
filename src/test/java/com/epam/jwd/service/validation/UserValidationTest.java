package com.epam.jwd.service.validation;

import com.epam.jwd.service.exception.IllegalEmailException;
import com.epam.jwd.service.exception.IllegalNameSizeException;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserValidationTest {

    @Test
    void shouldValidateNameWhenTheNameIsValid() throws IllegalNameSizeException {
        String name = "Vasily";
        assertTrue(UserValidation.isValidName(name));
    }

    @Test
    void shouldThrowIllegalNameSizeExceptionWhenTheNameIsShort() {
        String name = "";
        assertThrows(IllegalNameSizeException.class,
                () -> UserValidation.isValidName(name));
    }

    @Test
    void shouldValidateEmailWhenTheEmailIsValid() throws IllegalEmailException {
        String email = "jack@mail.ru";
        assertTrue(UserValidation.isEmail(email));
    }

    @Test
    void shouldThrowIllegalEmailExceptionWhenTheEmailIsInvalid() {
        List<String> notEmails = Arrays.asList(
                "",
                "aaaaaa",
                "aaa@aaa",
                "@",
                "@."
        );
        for (String invalidEmail : notEmails) {
            assertThrows(IllegalEmailException.class,
                    () -> UserValidation.isEmail(invalidEmail));
        }
    }
}
