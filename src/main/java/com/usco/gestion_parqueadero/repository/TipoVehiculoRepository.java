package com.usco.gestion_parqueadero.repository;

import com.usco.gestion_parqueadero.model.TipoVehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoVehiculoRepository extends JpaRepository<TipoVehiculo, Long> {

    @Query("SELECT t FROM TipoVehiculo t WHERE t.modelo = :modelo")
    TipoVehiculo findByModelo(String modelo);
}
