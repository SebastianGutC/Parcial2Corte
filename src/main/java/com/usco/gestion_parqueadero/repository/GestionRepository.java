package com.usco.gestion_parqueadero.repository;

import com.usco.gestion_parqueadero.model.Gestion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionRepository extends JpaRepository<Gestion, Long> {
    Gestion findByPlaca(String placa);
}
