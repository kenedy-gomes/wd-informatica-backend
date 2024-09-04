package com.wdinformatica.wd.informatica.services;

import com.wdinformatica.wd.informatica.domain.address.Address;
import com.wdinformatica.wd.informatica.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAllAddress(){
        return addressRepository.findAll();
    }

    public Address updateAddress(String id, Address address) {
        Address existingAddress = addressRepository.findById(id).orElseThrow(()-> new RuntimeException("Address not found"));
        existingAddress.setCep(address.getCep());
        existingAddress.setEndereco(address.getEndereco());
        existingAddress.setBairro(address.getBairro());
        existingAddress.setCidade(address.getCidade());
        existingAddress.setEstado(address.getEstado());
        existingAddress.setComplemento(address.getComplemento());

        return addressRepository.save(existingAddress);
    }
}
