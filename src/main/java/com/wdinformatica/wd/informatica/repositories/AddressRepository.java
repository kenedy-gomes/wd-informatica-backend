package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, String> {
}
