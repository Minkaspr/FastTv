package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.Customer;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {


    //Optional<Customer> findByFirstName(String firstName);
    Optional<Customer> findByPerson_FirstName(String firstName);

    List<Customer> findByPerson_LastNameContaining(String lastName);

    List<Customer> findByPerson_PhoneNumber2NotNull();

    // CONSULTAS CON JPQL
    //@Query("SELECT c FROM Customer c WHERE lower(c.firstName) LIKE lower(concat('%', :name,'%'))")
    @Query("SELECT c FROM Customer c WHERE lower(c.person.firstName) LIKE lower(concat('%', :name,'%'))")
    List<Customer> findByNameContainsIgnoreCase(@Param("name") String name);

    @Query("SELECT c FROM Customer c WHERE c.address = ?1 ")
    Customer getCustomerByAddress(String address);

    // @Query("SELECT c.person.firstName FROM Customer c WHERE c.address = :address")
    // String getCustomerFirstNameByAddress(@Param("address") String address);
    @Query("SELECT c.person.firstName FROM Customer c WHERE c.address = ?1")
    String getCustomerFirstNameByAddress(String address);

    // CONSULTAS NATIVAS
    /*@Query(
            value = "SELECT * FROM customer WHERE last_name = ?1",
            nativeQuery = true
    )
    Customer getCustomerByLastNameNative(String lastName);*/
    @Query(
            value = "SELECT * FROM customer WHERE last_name = :lastName",
            nativeQuery = true
    )
    Customer getCustomerByLastNameNative(@Param("lastName") String lastName);

    @Transactional
    @Modifying
    @Query(
            value = "UPDATE customer c SET c.last_name = :last_name WHERE c.customer_id = :id",
            nativeQuery = true
    )
    void updateCustomerLastNameByCustomerId(@Param("last_name") String last_name, @Param("id") Long id);
}
