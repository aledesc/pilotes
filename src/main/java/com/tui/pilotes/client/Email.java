package com.tui.pilotes.client;


import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

import java.util.function.Predicate;
import java.util.regex.Pattern;

@Getter
public class Email {

    private final String email;

    private static Boolean isAValidEmailDir(String email) {

        String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        Predicate<String> emailPredicate = Pattern.compile(EMAIL_REGEX).asPredicate();

        return StringUtils.isNotEmpty(email) && emailPredicate.test(email);
    }



    public Email(String email) throws InvalidEmailException {

        Predicate<String> isNotAValidEmailDir = str -> !isAValidEmailDir(str);

        if( isNotAValidEmailDir.test(email) ) {
            throw new InvalidEmailException();
        }

        this.email = email;
    }
}
