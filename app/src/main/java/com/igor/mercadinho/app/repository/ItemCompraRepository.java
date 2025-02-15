package com.igor.mercadinho.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igor.mercadinho.app.model.ItemCompra;
@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    ItemCompra findById(long id);

    List<ItemCompra> findByCompraId(Long compraId);
}
