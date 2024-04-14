package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "service")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serviceId;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(max = 128, message = "El nombre no puede tener más de 128 caracteres")
    @Column(length = 128, nullable = false)
    private String name;

    @Size(max = 256, message = "La descripción no puede tener más de 256 caracteres")
    @Column(length = 256)
    private String description;

    @NotNull(message = "El costo no puede ser nulo")
    @Positive(message = "El costo debe ser positivo")
    @Column(nullable = false)
    private Double cost;

    @NotBlank(message = "El tipo no puede estar vacío")
    @Size(max = 64, message = "El tipo no puede tener más de 64 caracteres")
    @Column(length = 64, nullable = false)
    private String type;

    @OneToMany(mappedBy = "service")
    private List<ContractDetail> contractDetails;
}
