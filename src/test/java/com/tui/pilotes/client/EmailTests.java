package com.tui.pilotes.client;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmailTests {

    @Test
    void checkThrowingInvalidEmailException() {


        Exception exception = assertThrows(InvalidEmailException.class, () -> new Email(""));
        exception = assertThrows(InvalidEmailException.class, () -> new Email("."));
        exception = assertThrows(InvalidEmailException.class, () -> new Email(" mole.com"));
        exception = assertThrows(InvalidEmailException.class, () -> new Email("@mole.com "));
        exception = assertThrows(InvalidEmailException.class, () -> new Email("a.coleman@mole"));
        exception = assertThrows(InvalidEmailException.class, () -> new Email("a.coleman@mole.a"));

        String VALID_EMAIL_STRUCTURE = "uno@dos.com";
        try {

            Email email = new Email("uno@dos.com");
            assertEquals(VALID_EMAIL_STRUCTURE, email.getEmail());

        } catch (InvalidEmailException ignored) {

            // creepy feeling down your spine !

        }

    }
}
