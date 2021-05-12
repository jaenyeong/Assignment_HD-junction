package com.hdjunction.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@AllArgsConstructor
@Getter
public enum CodeGroup {
    SEX("성별코드", "성별을 표시", Arrays.asList(Code.MALE, Code.FEMALE)),
    VISIT("방문상태코드", "환자방문의 상태(방문중, 종료, 취소)", Arrays.asList(Code.VISITING, Code.END, Code.CANCEL)),
    MEDICAL_DEPARTMENT("진료과목코드", "진료과목(내과, 안과 등)", Arrays.asList(Code.INTERNAL_MEDICINE, Code.OPHTHALMOLOGY)),
    MEDICAL_TYPE("진료유형코드", "진료유형(약처방, 검사등)", Arrays.asList(Code.PRESCRIBE_MEDICINE, Code.CHECK)),
    NONE("NONE", "일치하는 코드그룹 없음", Collections.emptyList());

    private final String groupName;
    private final String description;
    private final List<Code> codes;

    public static CodeGroup findCodeGroupBy(final Code code) {
        return Arrays.stream(values())
            .filter(codeGroup -> codeGroup.hasCode(code))
            .findAny()
            .orElse(NONE);
    }

    public boolean hasCode(final Code detailCode) {
        return codes.stream()
            .anyMatch(code -> code == detailCode);
    }

    @AllArgsConstructor
    @Getter
    public enum Code {
        MALE("M", "남"),
        FEMALE("F", "여"),

        VISITING("1", "방문중"),
        END("2", "종료"),
        CANCEL("3", "취소"),

        INTERNAL_MEDICINE("01", "내과"),
        OPHTHALMOLOGY("02", "안과"),

        PRESCRIBE_MEDICINE("D", "약처방"),
        CHECK("T", "검사"),

        NONE("NONE", "일치하는 코드 없음");

        private final String detailCode;
        private final String description;

        public static Code findCodeBy(final String codeName) {
            return Arrays.stream(values())
                .filter(thisCode -> thisCode.detailCode.equals(codeName))
                .findAny()
                .orElse(NONE);
        }

        public static CodeGroup findCodeGroupBy(final String codeName) {
            return CodeGroup.findCodeGroupBy(findCodeBy(codeName));
        }
    }
}
