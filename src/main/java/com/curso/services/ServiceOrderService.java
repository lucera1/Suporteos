package com.curso.services;

import com.curso.domains.ServiceOrder;
import com.curso.domains.Technician;
import com.curso.domains.User;
import com.curso.domains.dtos.ServiceOrderDTO;
import com.curso.domains.enums.OrderPriority;
import com.curso.domains.enums.OrderStatus;
import com.curso.repositories.ServiceOrderRepository;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ServiceOrderService {

    @Autowired
    private ServiceOrderRepository serviceRepo;
    @Autowired
    private TechnicianService techService;
    @Autowired
    private UserService userService;

    public ServiceOrder findbyId(UUID id){
        Optional<ServiceOrder> obj = serviceRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Chamado não encontrado! Id:"+id));
    }

    public List<ServiceOrderDTO> findAll(){
        return serviceRepo.findAll().stream()
                .map(obj -> new ServiceOrderDTO(obj)).collect(Collectors.toList());
    }

    private ServiceOrder newsServiceOrder(ServiceOrderDTO obj){
        Technician tech = techService.findbyId(obj.getTechnician());
        User user = userService.findbyId(obj.getUser());

        ServiceOrder os = new ServiceOrder();
        if(obj.getId() != null){
            os.setId(obj.getId());
        }

        if(obj.getOrderStatus().equals(2)){
            os.setEndDate(LocalDate.now());
        }

        os.setTechnician(tech);
        os.setUser(user);
        os.setOrderPriority(OrderPriority.toEnum(obj.getOrderPriority()));
        os.setOrderStatus(OrderStatus.toEnum(obj.getOrderStatus()));
        os.setTitleOS(obj.getTitleOS());
        os.setDescription(obj.getDescription());
        return os;
    }

    public ServiceOrder create (ServiceOrderDTO objDto){
        return serviceRepo.save(newsServiceOrder(objDto));
    }

    public ServiceOrder update (UUID id, ServiceOrderDTO objDto){
        objDto.setId(id);
        ServiceOrder oldObj;
        oldObj = newsServiceOrder(objDto);
        return serviceRepo.save(oldObj);
    }
}
