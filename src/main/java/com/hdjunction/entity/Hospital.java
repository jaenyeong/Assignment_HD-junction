package com.hdjunction.entity;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Hospital extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "HOSPITAL_ID")
    private Long id;

    private String name;

    private String sanatoriumId;

    private String directorName;

    public Hospital(final String name, final String sanatoriumId, final String directorName) {
        this.name = name;
        this.sanatoriumId = sanatoriumId;
        this.directorName = directorName;
    }
}
