package com.curso.domains.enums;

import com.curso.domains.Person;
import com.curso.domains.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "technician")
public class Technician extends Person {

    @JsonIgnore
    @OneToMany(mappedBy = "technician")
    private List<ServiceOrder> serviceOrders = new ArrayList<>();

    public Technician(Long id, String firstName, String lastName, String cpf, String email, String password) {
        super(id, firstName, lastName, cpf, email, password);
        addPersonType(PersonType.TECHNICIAN);
    }

    public Technician(){
        super();
        addPersonType(PersonType.TECHNICIAN);
    }

    public List<ServiceOrder> getServiceOrders() {
        return serviceOrders;
    }

    public void setServiceOrders(List<ServiceOrder> serviceOrders) {
        this.serviceOrders = serviceOrders;
    }
}
