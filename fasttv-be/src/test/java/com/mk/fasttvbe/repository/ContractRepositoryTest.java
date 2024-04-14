package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.Contract;
import com.mk.fasttvbe.entity.ContractDetail;
import com.mk.fasttvbe.entity.Customer;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ContractRepositoryTest {

    @Autowired
    ContractRepository contractRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Test
    @Transactional
    public void testSaveContractForExistingCustomer() {
        Long existingCustomerId = 1L;

        Customer customer = customerRepository.findById(existingCustomerId).orElseThrow();
        System.out.println("Cliente recuperado: " + customer);

        Contract contract = new Contract();
        contract.setStartDate(LocalDateTime.now());
        contract.setDevice("Device 4 Up");
        contract.setStatus("Active");

        contract.setCustomer(customer);
        customer.getContracts().add(contract);

        contractRepository.save(contract);

        assertNotNull(contract.getContractId());
    }

    @Test
    @Transactional
    void testSaveContractWithDetails() {
        Long existingCustomerId = 1L;
        // Buscar el cliente existente
        Customer customer = customerRepository.findById(existingCustomerId).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Crear un nuevo contrato
        Contract contract = new Contract();
        contract.setStartDate(LocalDateTime.now());
        contract.setStatus("Active");
        contract.setCustomer(customer);
        contract.setContractDetails(new ArrayList<>());

        // Crear los detalles de contrato
        ContractDetail contractDetail1 = new ContractDetail();
        contractDetail1.setContract(contract);
        contractDetail1.setService(serviceRepository.findById(1L).orElse(null));
        contractDetail1.setIsActive(true);

        ContractDetail contractDetail2 = new ContractDetail();
        contractDetail2.setContract(contract);
        contractDetail2.setService(serviceRepository.findById(2L).orElse(null));
        contractDetail2.setIsActive(true);

        // Agregar los detalles de contrato al contrato
        contract.getContractDetails().add(contractDetail1);
        contract.getContractDetails().add(contractDetail2);

        // Guardar el contrato
        contract = contractRepository.save(contract);

        // Verificaciones
        assertNotNull(contract.getContractId());
        assertFalse(contract.getContractDetails().isEmpty());
    }
}