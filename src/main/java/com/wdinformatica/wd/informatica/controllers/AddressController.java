package com.wdinformatica.wd.informatica.controllers;

import com.wdinformatica.wd.informatica.domain.address.Address;
import com.wdinformatica.wd.informatica.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/address")
public class AddressController {
    @Autowired
    AddressService addressService;

    @GetMapping("/all")
    public List<Address> getAll () {
        return addressService.getAllAddress();
    }

    @PutMapping("/update/{id}")
    public Address updateAddress(@PathVariable String id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

}
