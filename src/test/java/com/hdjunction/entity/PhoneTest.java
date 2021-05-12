package com.hdjunction.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PhoneTest {

    @DisplayName("유효한 휴대폰 번호 포맷 테스트")
    @ParameterizedTest
    @CsvSource(value = {"19910101", "20070718", "19630914", "19920222", "20140328", "20200430"})
    void validPhoneNoTest(final String phoneNo) throws Exception {
        // Arrange

        // Act

        // Assert
        Phone.of(phoneNo);
    }

    @DisplayName("유효하지 않은 휴대폰 번호 포맷 테스트")
    @ParameterizedTest
    @CsvSource(value = {"12910", "0", "2348923940723490237", "12790728", "19901707", "20070856"})
    void invalidPhoneNoTest(final String phoneNo) throws Exception {
        // Arrange

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> Phone.of(phoneNo));
    }
}
