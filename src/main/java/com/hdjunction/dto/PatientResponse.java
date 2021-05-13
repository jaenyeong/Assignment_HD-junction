package com.hdjunction.dto;

import lombok.Data;

@Data
public class PatientResponse {
    private String name;
    private String sex;
    private String dateOfBirth;
    private String phoneNo;
    private String recentVisit;
}
