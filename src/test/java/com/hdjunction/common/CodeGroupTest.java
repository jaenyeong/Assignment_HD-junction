package com.hdjunction.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class CodeGroupTest {

    @DisplayName("상세코드 테스트")
    @ParameterizedTest
    @MethodSource("getArgumentsForFindCodeTest")
    void detailCodeTest(final String detailCode, final String expectedDetailCode, final String expectedDescription) throws Exception {
        // Arrange

        // Act
        final CodeGroup.Code findCode = CodeGroup.Code.findCodeBy(detailCode);

        // Assert
        assertThat(findCode.name()).isEqualTo(expectedDetailCode);
        assertThat(findCode.getDescription()).isEqualTo(expectedDescription);
    }

    private static Stream<Arguments> getArgumentsForFindCodeTest() {
        return Stream.of(
            Arguments.of("M", "MALE", "남"),
            Arguments.of("F", "FEMALE", "여"),
            Arguments.of("1", "VISITING", "방문중"),
            Arguments.of("2", "END", "종료"),
            Arguments.of("3", "CANCEL", "취소"),
            Arguments.of("01", "INTERNAL_MEDICINE", "내과"),
            Arguments.of("02", "OPHTHALMOLOGY", "안과"),
            Arguments.of("D", "PRESCRIBE_MEDICINE", "약처방"),
            Arguments.of("T", "CHECK", "검사"),
            Arguments.of("NONE", "NONE", "일치하는 코드 없음")
        );
    }

    @DisplayName("코드그룹 테스트")
    @ParameterizedTest
    @MethodSource("getArgumentsForFindCodeGroupTest")
    void codeGroupTest(final String detailCode, final String expectedCodeGroupName) throws Exception {
        // Arrange

        // Act
        final CodeGroup findCode = CodeGroup.Code.findCodeGroupBy(detailCode);

        // Assert
        assertThat(findCode.name()).isEqualTo(expectedCodeGroupName);
    }

    private static Stream<Arguments> getArgumentsForFindCodeGroupTest() {
        return Stream.of(
            Arguments.of("M", "SEX"),
            Arguments.of("F", "SEX"),
            Arguments.of("1", "VISIT"),
            Arguments.of("2", "VISIT"),
            Arguments.of("3", "VISIT"),
            Arguments.of("01", "MEDICAL_DEPARTMENT"),
            Arguments.of("02", "MEDICAL_DEPARTMENT"),
            Arguments.of("D", "MEDICAL_TYPE"),
            Arguments.of("T", "MEDICAL_TYPE"),
            Arguments.of("NONE", "NONE")
        );
    }
}
