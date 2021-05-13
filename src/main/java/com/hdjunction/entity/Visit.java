package com.hdjunction.entity;

import com.hdjunction.common.CodeGroup;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
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
}
