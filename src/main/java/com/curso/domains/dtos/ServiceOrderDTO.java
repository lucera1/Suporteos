package com.curso.domains.dtos;

import com.curso.domains.ServiceOrder;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public class ServiceOrderDTO {

    private UUID id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate starDate;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate endDate;

    @NotNull(message = "O campo Título não pode ser nulo!")
    private String titleOS;

    @NotNull(message = "O campo Descrição não pode ser nulo!")
    private String description;

    @NotNull(message = "O campo Prioridade não pode ser nulo!")
    private Integer orderPriority;

    @NotNull(message = "O campo Status não pode ser nulo!")
    private Integer orderStatus;

    @NotNull(message = "O campo Técnico não pode ser nulo!")
    private Long technician;

    @NotNull(message = "O campo Usuário não pode ser nulo!")
    private Long user;

    private String nameTechnician;
    private String nameUser;

    public ServiceOrderDTO() {
    }

    public ServiceOrderDTO(ServiceOrder serviceOrder) {
        this.id = serviceOrder.getId();
        this.starDate = serviceOrder.getStarDate();
        this.endDate = serviceOrder.getEndDate();
        this.titleOS = serviceOrder.getTitleOS();
        this.description = serviceOrder.getDescription();
        this.orderPriority = serviceOrder.getOrderPriority().getId();
        this.orderStatus = serviceOrder.getOrderStatus().getId();
        this.technician = serviceOrder.getTechnician().getId();
        this.user = serviceOrder.getUser().getId();
        this.nameTechnician = serviceOrder.getTechnician().getFirstName() + " " + serviceOrder.getTechnician().getLastName();
        this.nameUser = serviceOrder.getUser().getFirstName() + " " + serviceOrder.getUser().getLastName();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LocalDate getStarDate() {
        return starDate;
    }

    public void setStarDate(LocalDate starDate) {
        this.starDate = starDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public @NotNull(message = "O campo Título não pode ser nulo!") String getTitleOS() {
        return titleOS;
    }

    public void setTitleOS(@NotNull(message = "O campo Título não pode ser nulo!") String titleOS) {
        this.titleOS = titleOS;
    }

    public @NotNull(message = "O campo Descrição não pode ser nulo!") String getDescription() {
        return description;
    }

    public void setDescription(@NotNull(message = "O campo Descrição não pode ser nulo!") String description) {
        this.description = description;
    }

    public @NotNull(message = "O campo Prioridade não pode ser nulo!") Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(@NotNull(message = "O campo Prioridade não pode ser nulo!") Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public @NotNull(message = "O campo Status não pode ser nulo!") Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(@NotNull(message = "O campo Status não pode ser nulo!") Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public @NotNull(message = "O campo Técnico não pode ser nulo!") Long getTechnician() {
        return technician;
    }

    public void setTechnician(@NotNull(message = "O campo Técnico não pode ser nulo!") Long technician) {
        this.technician = technician;
    }

    public @NotNull(message = "O campo Usuário não pode ser nulo!") Long getUser() {
        return user;
    }

    public void setUser(@NotNull(message = "O campo Usuário não pode ser nulo!") Long user) {
        this.user = user;
    }

    public String getNameTechnician() {
        return nameTechnician;
    }

    public void setNameTechnician(String nameTechnician) {
        this.nameTechnician = nameTechnician;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }
}
