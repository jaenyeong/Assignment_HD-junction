package com.hdjunction.entity;

import com.hdjunction.common.CodeGroup;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private Long id;

    private Long hospitalId;

    private String name;

    private String registrationId;

    private CodeGroup.Code sexCode;

    @Embedded
    private DateOfBirth dateOfBirth;

    private String phoneNo;

    public Patient(final Long hospitalId, final String name, final String registrationId, final CodeGroup.Code sexCode,
                   final String dateOfBirth, final String phoneNo) {
        this.hospitalId = hospitalId;
        this.name = name;
        this.registrationId = registrationId;
        this.sexCode = sexCode;
        this.dateOfBirth = DateOfBirth.of(dateOfBirth);
        this.phoneNo = phoneNo;
    }
}
