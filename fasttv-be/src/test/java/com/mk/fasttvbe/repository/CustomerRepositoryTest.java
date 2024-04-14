package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.Contract;
import com.mk.fasttvbe.entity.ContractDetail;
import com.mk.fasttvbe.entity.Customer;
import com.mk.fasttvbe.entity.Person;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    private ContractRepository contractRepository;

    @Autowired
    private ServiceRepository serviceRepository;

    @Test
    public void saveCustomer(){
        /*
        Customer customer = Customer.builder()
                .firstName("Juan")
                .lastName("Perez")
                .dni("12345679")
                .phoneNumber1("987654321")
                .address("Calle Falsa 456")
                .reference("Cerca al parque")
                .isActive(true)
                .build();

        Customer anotherCustomer = Customer.builder()
                .firstName("Maria")
                .lastName("Gonzalez")
                .dni("87654321")
                .phoneNumber1("912345678")
                .phoneNumber2("987654321")
                .address("Avenida Siempre Viva 321")
                .reference("Al lado de la tienda")
                .isActive(false)
                .build();
                */
        Person person = Person.builder()
                .firstName("Carlos")
                .lastName("Rodriguez")
                .dni("98760000")
                .phoneNumber1("432156789")
                .build();

        Customer customer = Customer.builder()
                .person(person)
                .address("Avenida Principal 123")
                .reference("Frente a la plaza")
                .build();

        customerRepository.save(customer);
    }

    @Test
    public void saveMultipleCustomers(){
        List<Customer> customers = new ArrayList<>();
        /*Customer customer1 = Customer.builder()
                .firstName("Pedro")
                .lastName("Lopez")
                .dni("98765432")
                .phoneNumber1("123456789")
                .address("Calle Verdadera 123")
                .reference("Lejos del parque")
                .isActive(true)
                .build();

        Customer customer2 = Customer.builder()
                .firstName("Ana")
                .lastName("Ramirez")
                .dni("23456789")
                .phoneNumber1("987123456")
                .phoneNumber2("321654987")
                .address("Avenida Nunca Muere 789")
                .reference("Al lado del supermercado")
                .isActive(false)
                .build();*/
        Person person1 = Person.builder()
                .firstName("Roberto")
                .lastName("Martinez")
                .dni("35451274")
                .phoneNumber1("112233445")
                .phoneNumber2("998877665")
                .build();

        Customer customer1 = Customer.builder()
                .person(person1)
                .address("Avenida Principal 456")
                .reference("Al lado del cine")
                .build();

        Person person2 = Person.builder()
                .firstName("Laura")
                .lastName("Gomez")
                .dni("21212121")
                .phoneNumber1("554433221")
                .phoneNumber2("667788990")
                .build();

        Customer customer2 = Customer.builder()
                .person(person2)
                .address("Calle Secundaria 789")
                .reference("Frente al hospital")
                .build();

        customers.add(customer1);
        customers.add(customer2);

        customerRepository.saveAll(customers);
    }

    @Test
    public void findCustomerById(){
        Long customerId = 1L; // reemplaza esto con el ID del cliente que quieres buscar
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if(optionalCustomer.isPresent()){
            Customer customer = optionalCustomer.get();
            System.out.println("Customer: " + customer);
        } else {
            System.out.println("No customer found with id: " + customerId);
        }
    }

    @Test
    public void findCustomerByFirstName(){
        //Customer customer = customerRepository.findByFirstName("Maria").get();
        Customer customer = customerRepository.findByPerson_FirstName("Maria").get();
        System.out.println("Customer: " + customer);
    }

    @Test
    public void findCustomersByNameContains(){
        List<Customer> customers = customerRepository.findByNameContainsIgnoreCase("an");
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer);
        }
    }

    @Test
    public void findCustomersByLastNameContains(){
        List<Customer> customers = customerRepository.findByPerson_LastNameContaining("go");
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer);
        }
    }

    @Test
    public void findAllCustomer(){
        List<Customer> customerList = customerRepository.findAll();
        System.out.println("Customer list: " + customerList);
    }

    @Test
    public void findCustomersByPhoneNumber2NotNull(){
        List<Customer> customers = customerRepository.findByPerson_PhoneNumber2NotNull();
        for (Customer customer : customers) {
            System.out.println("Customer: " + customer);
        }
    }

    @Test
    public void getCustomerByEmailTest(){
        String address = "Calle Secundaria 789";
        Customer customer = customerRepository.getCustomerByAddress(address);
        System.out.println("Customer: " + customer);
    }

    @Test
    public void getCustomerFirstNameByAddressTest(){
        String address = "Calle Secundaria 789";
        String firstName = customerRepository.getCustomerFirstNameByAddress(address);
        System.out.println("First Name: " + firstName);
    }

    @Test
    public void getCustomerFirstNameByLastNameNativeTest(){
        String lastName = "Rodriguez";
        Customer customer = customerRepository.getCustomerByLastNameNative(lastName);
        System.out.println("Customer: " + customer);
    }

    @Test
    public void updateCustomerLastNameByCustomerIdTest(){
        String newLastName = "Suarez";
        Long customerId = 1L;
        customerRepository.updateCustomerLastNameByCustomerId(newLastName, customerId);
    }

    @Test
    public void testSaveCustomerWithOneContract() {
        Person person = new Person("Juan", "Perez", "23242324", "123456789", "987654321");
        Customer customer = new Customer();
        customer.setPerson(person);
        customer.setAddress("Calle Falsa 123");
        customer.setReference("REF123");

        Contract contract = new Contract();
        contract.setStartDate(LocalDateTime.now());
        contract.setDevice("Device 1");
        contract.setStatus("Active");

        contract.setCustomer(customer);
        customer.getContracts().add(contract);

        customerRepository.save(customer);

        assertNotNull(customer.getCustomerId());
        assertNotNull(contract.getContractId());
    }

    @Test
    public void testSaveCustomerWithTwoContracts() {
        Person person = new Person("Maria", "Gomez", "87878787", "987654321", "123456789");
        Customer customer = new Customer();
        customer.setPerson(person);
        customer.setAddress("Avenida Siempre Viva 456");
        customer.setReference("REF456");

        Contract contract1 = new Contract();
        contract1.setStartDate(LocalDateTime.now());
        contract1.setDevice("Device 2");
        contract1.setStatus("Active");

        Contract contract2 = new Contract();
        contract2.setStartDate(LocalDateTime.now());
        contract2.setDevice("Device 3");
        contract2.setStatus("Inactive");

        contract1.setCustomer(customer);
        contract2.setCustomer(customer);

        customer.getContracts().add(contract1);
        customer.getContracts().add(contract2);

        customerRepository.save(customer);

        assertNotNull(customer.getCustomerId());
        assertNotNull(contract1.getContractId());
        assertNotNull(contract2.getContractId());
    }

    @Test
    void testSaveCustomerWithContractWithDetail() {
        // Crear un nuevo cliente
        Customer customer = new Customer();
        customer.setPerson(new Person("Davo", "Doe", "78877887", "467378952", "987654321"));
        customer.setAddress("123 Frente a la av. la paz");
        customer.setReference("Ref123");

        // Crear un nuevo contrato
        Contract contract = new Contract();
        contract.setStartDate(LocalDateTime.now());
        contract.setStatus("Active");
        contract.setCustomer(customer);
        contract.setContractDetails(new ArrayList<>()); 

        // Crear un detalle de contrato
        ContractDetail contractDetail = new ContractDetail();
        contractDetail.setContract(contract);
        contractDetail.setService(serviceRepository.findById(1L).orElse(null));
        contractDetail.setIsActive(true);

        // Agregar el contrato y el detalle de contrato al cliente
        contract.getContractDetails().add(contractDetail);
        customer.getContracts().add(contract);

        // Guardar el cliente
        customer = customerRepository.save(customer);

        // Verificaciones
        assertNotNull(customer.getCustomerId());
        assertFalse(customer.getContracts().isEmpty());
        assertFalse(customer.getContracts().get(0).getContractDetails().isEmpty());
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