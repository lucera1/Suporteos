package com.curso.services;

import com.curso.domains.Technician;
import com.curso.domains.dtos.TechnicianDTO;
import com.curso.repositories.TechnicianRepository;
import com.curso.services.exceptions.DataIntegrityViolationException;
import com.curso.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TechnicianService {

    @Autowired
    private TechnicianRepository techRepo;

    public List<TechnicianDTO> findAll(){
        return techRepo.findAll().stream()
                .map( obj -> new TechnicianDTO(obj)).collect(Collectors.toList());
    }

    public Technician findbyId(Long id){
        Optional<Technician> obj = techRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id:"+id));
    }

    public Technician findbyCpf(String cpf){
        Optional<Technician> obj = techRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! CPF:"+cpf));
    }

    public Technician findbyEmail(String email){
        Optional<Technician> obj = techRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Email:"+email));
    }

    public Technician create(TechnicianDTO objDto){
        objDto.setId(null);
        ValidaPorCPFeEmail(objDto);
        Technician newObj = new Technician(objDto);
        return techRepo.save(newObj);
    }

    public Technician update(Long id, TechnicianDTO objDto){
        objDto.setId(id);
        Technician oldObj = findbyId(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Technician(objDto);
        return techRepo.save(oldObj);
    }

    public void delete(Long id){
        Technician obj = findbyId(id);
        if(obj.getServiceOrders().size()>0){
            throw new DataIntegrityViolationException("Técnico não pode ser deletado pois possui ordens de serviço!");
        }
        techRepo.deleteById(id);
    }

    public void ValidaPorCPFeEmail (TechnicianDTO objDto){
        Optional<Technician> obj = techRepo.findByCpf(objDto.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        Optional<Technician> obj2 = techRepo.findByEmail(objDto.getEmail());
        if(obj2.isPresent() && obj2.get().getId() != objDto.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

}
