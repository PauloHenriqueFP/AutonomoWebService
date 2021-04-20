package com.empresax.autonomo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresax.autonomo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}
