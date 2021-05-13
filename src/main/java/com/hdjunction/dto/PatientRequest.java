package com.hdjunction.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PatientRequest {
    private Long id;
    @NotNull(message = "병원 정보를 입력하세요.")
    private Long hospitalId;
    @NotBlank(message = "유효한 이름을 입력하세요.")
    private String name;
    @NotBlank(message = "유효한 성별을 입력하세요.")
    private String sex;
    @NotBlank(message = "유효한 생년월일을 입력하세요.")
    private String dateOfBirth;
    @NotBlank(message = "유효한 휴대폰 번호를 입력하세요.")
    private String phoneNo;
}
