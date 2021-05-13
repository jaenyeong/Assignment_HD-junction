package com.hdjunction.entity;

import com.hdjunction.common.CodeGroup;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Visit extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PATIENT_ID")
    private Patient patient;

    @NotNull
    private CodeGroup.Code visitCode;

    public Visit(final Hospital hospital, final Patient patient, final CodeGroup.Code visitCode) {
        this.hospital = hospital;
        this.patient = patient;
        this.visitCode = visitCode;
    }

    public void visitHospital(final Patient patient) {
        this.patient = patient;
    }
}
