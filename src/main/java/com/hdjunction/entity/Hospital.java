package com.hdjunction.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOSPITAL_ID")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String sanatoriumId;

    @NotNull
    private String directorName;

    public Hospital(final String name, final String sanatoriumId, final String directorName) {
        this.name = name;
        this.sanatoriumId = sanatoriumId;
        this.directorName = directorName;
    }

    public Long getId() {
        return id;
    }
}
