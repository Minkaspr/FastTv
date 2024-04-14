package com.mk.fasttvbe.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class Person {
    @Column(length = 128, nullable = false)
    private String firstName;

    @Column(length = 128, nullable = false)
    private String lastName;

    @Column(length = 8, nullable = false, unique = true)
    private String dni;

    @Column(length = 9, nullable = false)
    private String phoneNumber1;

    @Column(length = 9)
    private String phoneNumber2;
}
