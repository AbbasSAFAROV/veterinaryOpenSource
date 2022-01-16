package com.ozguryazilim.veterinary.service;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.entity.Role;
import com.ozguryazilim.veterinary.entity.UserRole;
import com.ozguryazilim.veterinary.exception.OwnerNotFoundException;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.PetDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import com.ozguryazilim.veterinary.repository.OwnerRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class OwnerService implements UserDetailsService {

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

    public Owner save(Owner request) {
        request.setPassword(passwordEncoder.encode(request.getPassword()));
        //Owner owner = new Owner(request.getNameSurname(),request.getContact(),request.getUsername(),request.getEmail(),request.getPhoneNumber(),passwordEncoder.encode(request.getPassword()));
        return ownerRepository.save(request);
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Owner owner = ownerRepository.findByEmail(email);
        //Collection<UserRole> roles = Collections.singleton(owner.getUserRole());
        if (owner == null) {
            throw new UsernameNotFoundException("Invalid Username or password 01");
        }
        //return new loginService(owner);
        return new User(owner.getEmail(),owner.getPassword(),mapRoles(owner.getRoles()));
    }

    public Owner activateAdmin(Long id){
        Owner owner = findOwnerById(id);
        owner.setRoles(Arrays.asList(new Role("ADMIN")));
        return ownerRepository.save(owner);
    }

    public Owner updateOwner(Owner owner) {
        Owner owner1 = ownerRepository.findById(owner.getId()).orElseThrow(()->new OwnerNotFoundException("Owner Not Found With This Id: "+owner.getId()));
        owner1.setNameSurname(owner.getNameSurname());
        return ownerRepository.save(owner1);
    }

    public Owner getCurrentUser() {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        log.info(auth.getName());
        log.info(auth.getPrincipal().toString());
        log.info(auth.getAuthorities().toString());

        if(!(auth instanceof AnonymousAuthenticationToken)){
            Object name = (Object) auth.getPrincipal();
            Owner owner = ownerRepository.findByEmail(auth.getName());
            return owner;
        }
        return null;
    }

    public Owner deActivateAdmin(Long id){
        Owner owner = findOwnerById(id);
        owner.setRoles(Arrays.asList(new Role("USER")));
        return ownerRepository.save(owner);
    }


    private Collection<? extends GrantedAuthority> mapRoles(Collection<Role> roles){
        return roles.stream().map(role->new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }

}
