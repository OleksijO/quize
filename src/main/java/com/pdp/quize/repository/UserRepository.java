package com.pdp.quize.repository;

import com.pdp.quize.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    Boolean existsByEmail(String email);

}
