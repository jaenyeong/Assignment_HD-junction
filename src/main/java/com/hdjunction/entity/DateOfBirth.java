package com.hdjunction.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DateOfBirth {
    private static final String REGEX_DATE_OF_BIRTH = "^(19[0-9][0-9]|20\\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$";
    private static final String INVALID_ERR_MESSAGE = "INVALID DATE FORMAT ERROR -> [ex format]: 20010101";

    @Column(columnDefinition = "varchar(10)")
    private String DateOfBirth;

    private DateOfBirth(final String date) {
        this.DateOfBirth = date;
    }

    public static DateOfBirth of(final String date) {
        if (invalidDateOfBirth(date)) {
            throw new IllegalArgumentException(INVALID_ERR_MESSAGE);
        }

        return new DateOfBirth(date);
    }

    private static boolean invalidDateOfBirth(final String date) {
        return !Pattern.matches(REGEX_DATE_OF_BIRTH, date);
    }

    public String getDateOfBirth() {
        return DateOfBirth;
    }
}
