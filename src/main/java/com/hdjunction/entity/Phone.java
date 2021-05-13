package com.hdjunction.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.regex.Pattern;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Phone {
    private static final String REGEX_PHONE_NO = "^01(?:0|1|[6-9])(\\\\d{3}|\\\\d{4})(\\\\d{4})$";
    private static final String INVALID_ERR_MESSAGE = "INVALID DATE FORMAT ERROR -> [ex format]: 20010101";

    @Column(columnDefinition = "varchar(20)")
    private String phoneNo;

    private Phone(final String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public static Phone of(final String phoneNo) {
        if (isNotValidPhoneNoFormat(phoneNo)) {
            throw new IllegalArgumentException(INVALID_ERR_MESSAGE);
        }

        return new Phone(phoneNo);
    }

    private static boolean isNotValidPhoneNoFormat(final String phoneNo) {
        return !Pattern.matches(REGEX_PHONE_NO, phoneNo);
    }

    public String getPhoneNo() {
        return phoneNo;
    }
}
