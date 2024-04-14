package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    @NotNull(message = "La persona no puede ser nula")
    @Embedded
    private Person person;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Column(length = 100, nullable = false)
    private String address;

    @NotBlank(message = "La referencia no puede estar vacía")
    @Column(length = 192, nullable = false)
    private String reference;

    @OneToMany(
            mappedBy = "customer",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Contract> contracts  = new ArrayList<>();
}
