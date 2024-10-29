package com.usco.gestion_parqueadero.service;

import com.usco.gestion_parqueadero.model.TipoVehiculo;
import com.usco.gestion_parqueadero.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoVehiculoServiceImpl implements TipoVehiculoService{

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Override
    public List<TipoVehiculo> findAll() {
        return tipoVehiculoRepository.findAll();
    }
}
