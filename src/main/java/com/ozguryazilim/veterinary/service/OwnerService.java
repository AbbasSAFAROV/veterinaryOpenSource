package com.ozguryazilim.veterinary.service;

import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.exception.OwnerNotFoundException;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder passwordEncoder;

    public OwnerService(OwnerRepository ownerRepository, ModelMapper modelMapper, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.ownerRepository = ownerRepository;
        this.modelMapper = modelMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public List<OwnerDto> getAllOwner(){
        return ownerRepository
                .findAll()
                .stream()
                .map(x->modelMapper.map(x,OwnerDto.class))
                .collect(Collectors.toList());
    }

    public Owner createOwner(Owner request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        return ownerRepository.save(request);
    }

    public Owner updateOwnerById(Owner owner, Long id){
        Owner existingOwner = findOwnerById(id);
        existingOwner.setNameSurname(owner.getNameSurname());
        existingOwner.setContact(owner.getContact());
        existingOwner.setEmail(owner.getEmail());
        existingOwner.setPassword(owner.getPassword());
        existingOwner.setPhoneNumber(owner.getPhoneNumber());
        return ownerRepository.save(existingOwner);
    }

    public String deleteOwnerById(Long id){
        Owner owner = findOwnerById(id);
        ownerRepository.delete(owner);
        return "owner deleted id:"+id;
    }

    public OwnerDto findByName(String name){
        return modelMapper.map(ownerRepository.findByNameSurnameContaining(name),OwnerDto.class);
    }

    public Owner getOwnerByEmail(String email){
        return ownerRepository.findByEmail(email);
    }

    public Owner findOwnerById(Long id){
        return ownerRepository.findById(id).orElseThrow(()->new OwnerNotFoundException("Owner Not Found With This Id: "+id));
    }

}
