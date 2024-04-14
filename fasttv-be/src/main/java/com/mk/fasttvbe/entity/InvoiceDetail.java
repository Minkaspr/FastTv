package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice_detail")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long invoiceDetailId;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @NotNull(message = "El servicio no puede ser nulo")
    @ManyToOne
    @JoinColumn(name = "service_id")
    private Service service;

    @NotNull(message = "La cantidad no puede ser nula")
    @Min(value = 1, message = "La cantidad debe ser al menos 1")
    private Integer quantity;

    @NotNull(message = "El precio unitario no puede ser nulo")
    @Positive(message = "El precio unitario debe ser positivo")
    private Double unitPrice;

    @NotNull(message = "El subtotal no puede ser nulo")
    @Positive(message = "El subtotal debe ser positivo")
    private Double subTotal;
}
