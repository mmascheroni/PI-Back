package com.backend.PIBack.repository;

import com.backend.PIBack.entity.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {

    @Query("SELECT f FROM Favorito f WHERE f.usuario.id = :usuarioId")
    List<Favorito> findByUsuarioId(@Param("usuarioId") Long usuarioId);

    @Query("SELECT f FROM Favorito f WHERE f.usuario.email = :usuarioEmail")
    List<Favorito> findByUsuarioEmail(@Param("usuarioEmail") String usuarioEmail);

}
