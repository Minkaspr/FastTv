package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "invoice")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceId;

    @NotNull(message = "El contrato no puede ser nulo")
    @NotNull
    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    private LocalDate dateOfIssue;

    @Future(message = "La fecha de vencimiento debe ser en el futuro")
    private LocalDate dueDate;

    @NotNull(message = "El total no puede ser nulo")
    @Positive(message = "El total debe ser positivo")
    private Double total;

    @NotBlank(message = "El estado no puede estar vacío")
    private String status;

    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;
}
