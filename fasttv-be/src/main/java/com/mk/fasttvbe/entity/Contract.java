package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@Table(name = "contract")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @NotNull(message = "La fecha de inicio no puede ser nula")
    @PastOrPresent(message = "La fecha de inicio debe ser en el pasado o en el presente")
    @Column(nullable = false)
    private LocalDateTime startDate; // Fecha y hora en que el contrato entra en vigor.

    private LocalDate activationDate; // Fecha en que se activan los servicios. Null hasta que se active el servicio.

    private LocalTime activationHour; // Hora en que se activan los servicios. Null hasta que se active el servicio.

    @Size(max = 256, message = "El dispositivo no puede tener más de 256 caracteres")
    private String device;

    @NotNull(message = "El estado no puede ser nulo")
    @Size(max = 64, message = "El estado no puede tener más de 64 caracteres")
    @Column(length = 64, nullable = false)
    private String status;

    @NotNull(message = "El cliente no puede ser nulo")
    @ManyToOne(
            cascade = CascadeType.ALL
    )
    @JoinColumn(
            name = "customer_id",
            referencedColumnName = "customerId"
    )
    private Customer customer;

    @OneToMany(
            mappedBy = "contract",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<ContractDetail> contractDetails;

    /*
    @OneToMany(mappedBy = "contract")
    private List<Invoice> invoices;
    */
}
