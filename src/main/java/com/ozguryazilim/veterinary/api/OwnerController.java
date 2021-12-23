package com.ozguryazilim.veterinary.api;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public ResponseEntity<List<Owner>> getAllOwners(){
        return new ResponseEntity<>(ownerService.getAllOwner(), HttpStatus.OK);//200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.getOwnerById(id),HttpStatus.OK);//200
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        return new ResponseEntity<>(ownerService.createOwner(owner),HttpStatus.CREATED);//201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner,@PathVariable Long id){
        return new ResponseEntity<>(ownerService.updateOwnerById(owner,id),HttpStatus.OK); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.deleteOwnerById(id),HttpStatus.NO_CONTENT);//204
    }


}
