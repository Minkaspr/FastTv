package com.mk.fasttvbe.repository;

import com.mk.fasttvbe.entity.Service;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ServiceRepositoryTest {

    @Autowired
    ServiceRepository serviceRepository;

    @Test
    void saveSingleService() {
        Service service = new Service();
        service.setName("Internet 100 Mbps");
        service.setDescription("Plan de internet con velocidad de 100 Mbps");
        service.setCost(50.0);
        service.setType("Internet");
        service = serviceRepository.save(service);

        assertNotNull(service.getServiceId()); // Verifica que el servicio se haya guardado correctamente
    }

    @Test
    void saveMultipleServices() {
        List<Service> services = new ArrayList<>();

        Service service1 = new Service();
        service1.setName("Internet 200 Mbps");
        service1.setDescription("Plan de internet con velocidad de 200 Mbps");
        service1.setCost(70.0);
        service1.setType("Internet");
        services.add(service1);

        Service service2 = new Service();
        service2.setName("Cable Basic");
        service2.setDescription("Plan básico de cable con 100 canales");
        service2.setCost(30.0);
        service2.setType("Cable");
        services.add(service2);

        Service service3 = new Service();
        service3.setName("Cable Premium");
        service3.setDescription("Plan premium de cable con 200 canales y contenido HD");
        service3.setCost(50.0);
        service3.setType("Cable");
        services.add(service3);

        services = serviceRepository.saveAll(services);

        for (Service service : services) {
            assertNotNull(service.getServiceId()); // Verifica que cada servicio se haya guardado correctamente
        }
    }
}