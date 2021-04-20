package com.empresax.autonomo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.empresax.autonomo.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {
	List<Product> findAllByUserId(Long userId);
	Optional<Product> findByUserIdAndId(Long userId, Long productId);
}
