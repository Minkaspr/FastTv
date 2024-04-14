package com.mk.fasttvbe.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "contract_detail")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContractDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contracDetailId;

    @NotNull(message = "El contrato no puede ser nulo")
    @ManyToOne
    @JoinColumn(
            name = "contract_id",
            referencedColumnName = "contractId"
    )
    private Contract contract;

    @NotNull(message = "El servicio no puede ser nulo")
    @ManyToOne
    @JoinColumn(
            name = "service_id",
            referencedColumnName = "serviceId"
    )
    private Service service;

    @NotNull(message = "El estado activo no puede ser nulo")
    private Boolean isActive;
}
