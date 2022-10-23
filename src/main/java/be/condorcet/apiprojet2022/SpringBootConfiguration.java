package be.condorcet.apiprojet2022;

import be.condorcet.apiprojet2022.services.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringBootConfiguration {
    @Value("${server.mode}")
    private String mode;
    @Bean
    InterfEmployeService employeServiceImpl() {
        System.out.println("création du service employé en mode : "+mode);
        switch (mode){
            case "PROD" : return new EmployeServiceImpl();
            case "STUB" : return new EmployeServiceStub();
            case "MOCK" : return new EmployeSeviceMock();
            default: return new EmployeServiceStub();
        }
    }
    /*@Bean
    InterfComfactService comfactServiceImpl() {
        System.out.println("création du service client en mode : "+mode);
        switch (mode){
            case "PROD" : return new ComfactServiceImpl();
            case "STUB" : return new ComfactServiceStub();
            case "MOCK" : return new ComfactServiceMock();
            default: return new ComfactServiceStub();
        }
    }*/
}
