package com.igor.mercadinho.app.repository;

import com.igor.mercadinho.app.model.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCompraRepository extends JpaRepository<ItemCompra, Long> {
    ItemCompra findById(long id);

    List<ItemCompra> findByCompraId(Long compraId);
}
