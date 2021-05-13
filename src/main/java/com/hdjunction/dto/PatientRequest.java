package com.hdjunction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientRequest {
    private String id;
    @NotNull(message = "병원 정보를 입력하세요.")
    private String hospitalId;
    @NotBlank(message = "유효한 이름을 입력하세요.")
    private String name;
    @NotBlank(message = "유효한 성별을 입력하세요.")
    private String sex;
    @NotBlank(message = "유효한 생년월일을 입력하세요.")
    private String dateOfBirth;
    @NotBlank(message = "유효한 휴대폰 번호를 입력하세요.")
    private String phoneNo;
}
