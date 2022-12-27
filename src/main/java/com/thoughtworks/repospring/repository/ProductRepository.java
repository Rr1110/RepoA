package com.thoughtworks.repospring.repository;

import com.thoughtworks.repospring.modal.Product;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID>{
    List<Product> findProductByName(String name);
}
