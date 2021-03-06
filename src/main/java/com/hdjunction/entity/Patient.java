package com.hdjunction.entity;

import com.hdjunction.common.CodeGroup;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Patient extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PATIENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOSPITAL_ID")
    private Hospital hospital;

    @NotNull
    @Column(columnDefinition = "varchar(45)")
    private String name;

    @NotNull
    @Column(unique = true, columnDefinition = "uuid")
    private UUID registrationId;

    @NotNull
    @Column(columnDefinition = "varchar(10)")
    private CodeGroup.Code sexCode;

    @Embedded
    private DateOfBirth dateOfBirth;

    @Embedded
    private Phone phoneNo;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<Visit> visitHistory = new ArrayList<>();

    private Patient(final Hospital hospital, final String name, final CodeGroup.Code sexCode,
                    final DateOfBirth dateOfBirth, final Phone phoneNo) {
        this.hospital = hospital;
        this.name = name;
        this.registrationId = generateRegistrationId();
        this.sexCode = sexCode;
        this.dateOfBirth = dateOfBirth;
        this.phoneNo = phoneNo;
    }

    public static Patient of(final Hospital hospital, final String name, final CodeGroup.Code sexCode,
                             final DateOfBirth dateOfBirth, final Phone phoneNo) {
        return new Patient(hospital, name, sexCode, dateOfBirth, phoneNo);
    }

    public void update(final String name, final CodeGroup.Code sexCode, final DateOfBirth dateOfBirth, final Phone phoneNo) {
        this.name = name;
        this.sexCode = sexCode;
        this.dateOfBirth = dateOfBirth;
        this.phoneNo = phoneNo;
    }

    public void visitHospital(final Visit visit) {
        this.visitHistory.add(visit);
        visit.visitHospital(this);
    }

    public String getRecentVisit() {
        if (this.visitHistory.isEmpty()) {
            return "";
        }

        return this.visitHistory.stream()
            .max(Comparator.comparing(BaseTimeEntity::getCreatedDate))
            .get()
            .getCreatedDate()
            .toString();
    }

    public Long getId() {
        return id;
    }

    private UUID generateRegistrationId() {
        return UUID.randomUUID();
    }

    public String getName() {
        return name;
    }

    public String getRegistrationId() {
        return registrationId.toString();
    }

    public String getSexCode() {
        return sexCode.getDetailCode();
    }

    public String getDateOfBirth() {
        return dateOfBirth.getDateOfBirth();
    }

    public String getPhoneNo() {
        return phoneNo.getPhoneNo();
    }
}
