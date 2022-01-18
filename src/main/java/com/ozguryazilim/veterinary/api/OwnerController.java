package com.ozguryazilim.veterinary.api;


import com.ozguryazilim.veterinary.entity.Owner;
import com.ozguryazilim.veterinary.model.OwnerDto;
import com.ozguryazilim.veterinary.model.request.OwnerCreateRequest;
import com.ozguryazilim.veterinary.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class OwnerController {

    private final OwnerService ownerService;

    public OwnerController(OwnerService userService) {
        this.ownerService = userService;
    }


    @GetMapping
    public ResponseEntity<List<OwnerDto>> getAllOwners(){
        return new ResponseEntity<>(ownerService.getAllOwner(), HttpStatus.OK);//200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Owner> getOwnerById(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.findOwnerById(id),HttpStatus.OK);//200
    }
    @GetMapping("/search")
    public ResponseEntity<OwnerDto> getOwnerByName(@RequestParam String name){
        return new ResponseEntity<>(ownerService.findByName(name),HttpStatus.OK);//200
    }

    @PostMapping
    public ResponseEntity<Owner> createOwner(@RequestBody Owner owner){
        return new ResponseEntity<>(ownerService.createOwner(owner),HttpStatus.CREATED);//201
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@RequestBody Owner owner, @PathVariable Long id){
        return new ResponseEntity<>(ownerService.updateOwnerById(owner,id),HttpStatus.OK); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOwner(@PathVariable Long id){
        return new ResponseEntity<>(ownerService.deleteOwnerById(id),HttpStatus.NO_CONTENT);//204
    }


}
