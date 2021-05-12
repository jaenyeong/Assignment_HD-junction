package com.hdjunction.entity;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertThrows;

class DateOfBirthTest {

    @DisplayName("생년월일 성공 포맷 테스트")
    @ParameterizedTest
    @CsvSource(value = {"19910101", "20070718", "19630914", "19920222", "20140328", "20200430"})
    void validDateOfBirthTest(final String date) throws Exception {
        // Arrange

        // Act

        // Assert
        DateOfBirth.of(date);
    }

    @DisplayName("생년월일 실패 포맷 테스트")
    @ParameterizedTest
    @CsvSource(value = {"12910", "0", "2348923940723490237", "12790728", "19901707", "20070856"})
    void invalidDateOfBirthTest(final String date) throws Exception {
        // Arrange

        // Act

        // Assert
        assertThrows(IllegalArgumentException.class, () -> DateOfBirth.of(date));
    }
}
