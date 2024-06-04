package com.wdinformatica.wd.informatica.repositories;

import com.wdinformatica.wd.informatica.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {
        Optional<User> findByEmail(String email);
}
