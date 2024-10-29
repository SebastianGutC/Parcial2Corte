package com.usco.gestion_parqueadero.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GestionDTO {
    private String placa;
    private LocalDateTime fechaIngreso;
    private LocalDateTime fechaSalida;
    private String ubicacion;
    private String modelo;
}
