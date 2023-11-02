package com.backend.PIBack.repository;

import com.backend.PIBack.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Long> {
    @Query("SELECT p FROM Producto p ORDER BY FUNCTION('RAND') LIMIT :limite")
    List<Producto> listarProductosAleatorios(@Param("limite") int limite);
}
