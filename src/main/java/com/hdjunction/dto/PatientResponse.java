package com.hdjunction.dto;

import com.hdjunction.entity.Patient;
import lombok.Data;

@Data
public class PatientResponse {
    private String id;
    private String name;
    private String registrationId;
    private String sex;
    private String dateOfBirth;
    private String phoneNo;
    private String recentVisit;

    public PatientResponse(final Patient patient) {
        this.id = Long.toString(patient.getId());
        this.name = patient.getName();
        this.registrationId = patient.getRegistrationId();
        this.sex = patient.getSexCode();
        this.dateOfBirth = patient.getDateOfBirth();
        this.phoneNo = patient.getPhoneNo();
        this.recentVisit = patient.getRecentVisit();
    }
}
