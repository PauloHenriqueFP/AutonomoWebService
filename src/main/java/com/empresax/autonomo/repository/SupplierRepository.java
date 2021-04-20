package com.empresax.autonomo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.empresax.autonomo.model.Supplier;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
	List<Supplier> findAllByUserId(Long userId);
	Optional<Supplier> findByUserIdAndId(Long userId, Long supplierId);
}
