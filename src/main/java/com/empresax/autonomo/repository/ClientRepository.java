package com.empresax.autonomo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresax.autonomo.model.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
	List<Client> findAllByUserId(Long userId);
	Optional<Client> findByUserIdAndId(Long userId, Long clientId);
}
