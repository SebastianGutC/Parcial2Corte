package com.usco.gestion_parqueadero.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "gestion")
public class Gestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 6)
    @Pattern(regexp = "^[A-Z]{3}\\d{3}$", message = "La placa debe tener el formato AAA000")
    private String placa;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaSalida;

    private String ubicacion;

    @ManyToOne
    @JoinColumn(name = "modelo")
    private TipoVehiculo modelo;

    public Gestion(String placa, LocalDateTime fechaIngreso, LocalDateTime fechaSalida, String ubicacion, TipoVehiculo modelo) {
        this.placa = placa;
        this.fechaIngreso = fechaIngreso;
        this.fechaSalida = fechaSalida;
        this.ubicacion = ubicacion;
        this.modelo = modelo;
    }
}
