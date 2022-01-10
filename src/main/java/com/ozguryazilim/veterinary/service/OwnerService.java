package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.UserRole;
import com.ozguryazilim.veterinary.exception.OwnerNotFoundException;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OwnerService implements UserService{

    private final OwnerRepository ownerRepository;
    private final ModelMapper modelMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

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

    public OwnerDto getOwnerById(Long id){
        return modelMapper.map(findOwnerById(id),OwnerDto.class);
    }

    public OwnerDto createOwner(OwnerCreateRequest owner){
        return modelMapper.map(ownerRepository.save(modelMapper.map(owner,Owner.class)),OwnerDto.class);
    }

    public Owner findByUsername(String name){
        return ownerRepository.findByUsername(name);
    }

    public OwnerDto updateOwnerById(Owner owner, Long id){
        Owner existingOwner = findOwnerById(id);
        existingOwner.setNameSurname(owner.getNameSurname());
        existingOwner.setContact(owner.getContact());
        existingOwner.setEmail(owner.getEmail());
        existingOwner.setPassword(owner.getPassword());
        existingOwner.setPhoneNumber(owner.getPhoneNumber());
        return modelMapper.map(ownerRepository.save(existingOwner),OwnerDto.class);
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

    @Override
    public Owner save(Owner request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        //Owner owner = new Owner(request.getNameSurname(),request.getContact(),request.getUsername(),request.getEmail(),request.getPhoneNumber(),passwordEncoder.encode(request.getPassword()));
        return ownerRepository.save(request);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByUsername(username);
        //Collection<UserRole> roles = Collections.singleton(owner.getUserRole());
        if (owner == null) {
            throw new UsernameNotFoundException("Invalid Username or password 01");
        }
        return new loginService(owner);
    }

    public Owner activateAdmin(Long id){
        Owner owner = findOwnerById(id);
        owner.setUserRole(UserRole.ADMIN_ROLE);
        return ownerRepository.save(owner);
    }

    @Override
    public Owner updateOwner(Owner owner) {
        Owner owner1 = ownerRepository.findById(owner.getId()).orElseThrow(()->new OwnerNotFoundException("Owner Not Found With This Id: "+owner.getId()));
        owner1.setNameSurname(owner.getNameSurname());
        return ownerRepository.save(owner1);
    }

    public Owner deActivateAdmin(Long id){
        Owner owner = findOwnerById(id);
        owner.setUserRole(UserRole.USER_ROLE);
        return ownerRepository.save(owner);
    }

    /**
    private Collection<? extends GrantedAuthority> mapRoles(Collection<UserRole> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
    }
     **/
}
