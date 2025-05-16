package com.curso.domains;

import com.curso.domains.dtos.TechnicianDTO;
import com.curso.domains.enums.PersonType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "technician")
public class Technician extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Technician(Long id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.USER);
        addPersonType(PersonType.TECHNICIAN);
    }

    public Technician(){
        super();
        addPersonType(PersonType.USER);
        addPersonType(PersonType.TECHNICIAN);
    }

    public Technician(TechnicianDTO obj) {
        this.id = obj.getId();
        this.firstName = obj.getFirstName();
        this.lastName = obj.getLastName();
        this.cpf = obj.getCpf();
        this.email = obj.getEmail();
        this.password = obj.getPassword();
        this.createdAt = obj.getCreatedAt();
        this.personType = obj.getPersonType().stream()
                .map( x -> x.getId()).collect(Collectors.toSet());
        addPersonType(PersonType.USER);
        addPersonType(PersonType.TECHNICIAN);
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}
