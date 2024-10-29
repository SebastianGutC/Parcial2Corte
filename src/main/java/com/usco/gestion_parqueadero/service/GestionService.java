package com.usco.gestion_parqueadero.service;

import com.usco.gestion_parqueadero.dto.GestionDTO;
import com.usco.gestion_parqueadero.model.Gestion;

import java.util.List;

public interface GestionService {

    Gestion saveGestion(GestionDTO gestionDTO);
    List<Gestion> getAllGestiones();
    Gestion getGestionById(Long id);
    Gestion updateGestion(Gestion gestion);
    void deleteGestion(Gestion gestion);
}
