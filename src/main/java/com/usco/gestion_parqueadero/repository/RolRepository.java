package com.usco.gestion_parqueadero.repository;

import com.usco.gestion_parqueadero.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RolRepository extends JpaRepository<Rol, Long> {

    @Query("SELECT r FROM Rol r WHERE r.nombre = :nombre")
    Rol findByNombre(String nombre);
}
