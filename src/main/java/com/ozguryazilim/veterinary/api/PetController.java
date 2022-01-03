package com.ozguryazilim.veterinary.api;


import com.ozguryazilim.veterinary.model.PetDto;
import com.ozguryazilim.veterinary.model.request.PetCreateRequest;
import com.ozguryazilim.veterinary.service.OwnerService;
import com.ozguryazilim.veterinary.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("animal")
public class PetController {

    private final PetService petService;
    private final OwnerService ownerService;

    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<PetDto>> getAllPets(){
        return new ResponseEntity<>(petService.getAllPets(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PetDto> getPetById(@PathVariable Long id){
        return new ResponseEntity<>(petService.getPetById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PetDto> createPet(@RequestBody PetCreateRequest request){
        return new ResponseEntity<>(petService.createPet(request),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PetDto> updatePetById(@PathVariable Long id, @RequestBody PetCreateRequest createRequest){
        return new ResponseEntity<>(petService.updatePetById(createRequest,id),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePetById(@PathVariable Long id){
        return new ResponseEntity<>(petService.deletePetById(id),HttpStatus.NO_CONTENT);
    }






}
