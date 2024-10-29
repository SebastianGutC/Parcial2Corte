package com.usco.gestion_parqueadero.service;

import com.usco.gestion_parqueadero.dto.GestionDTO;
import com.usco.gestion_parqueadero.model.Gestion;
import com.usco.gestion_parqueadero.model.TipoVehiculo;
import com.usco.gestion_parqueadero.repository.GestionRepository;
import com.usco.gestion_parqueadero.repository.TipoVehiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionServiceImpl implements GestionService{

    @Autowired
    private GestionRepository gestionRepository;

    @Autowired
    private TipoVehiculoRepository tipoVehiculoRepository;

    @Override
    public Gestion saveGestion(GestionDTO gestionDTO) {

        TipoVehiculo modelo = tipoVehiculoRepository.findByModelo(gestionDTO.getModelo());
        if (modelo == null) {
            throw new IllegalArgumentException(" Modelo de vehiculo no encontrado");
        }

        Gestion gestion = new Gestion(gestionDTO.getPlaca(),
                gestionDTO.getFechaIngreso(),
                gestionDTO.getFechaSalida(),
                gestionDTO.getUbicacion(),
                modelo);
        try {
            Gestion savedGestion = gestionRepository.save(gestion);
            System.out.println("Nueva gestión de Ingreso guardada: " + savedGestion);
            return savedGestion;
        } catch (Exception e) {
            System.out.println("Error al guardar el Nuevo Ingreso: " + e.getMessage());
            return null;
        }
    }

    @Override
    public List<Gestion> getAllGestiones() {
        return gestionRepository.findAll();
    }

    @Override
    public Gestion getGestionById(Long id) {
        Optional<Gestion> optionalGestion = gestionRepository.findById(id);
        return optionalGestion.orElse(null);
    }

    @Override
    public Gestion updateGestion(Gestion gestion) {
        return gestionRepository.save(gestion);
    }

    @Override
    public void deleteGestion(Gestion gestion) {
        gestionRepository.delete(gestion);
    }
}
