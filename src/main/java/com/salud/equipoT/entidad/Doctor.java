package com.salud.equipoT.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "doctor")
public class Doctor extends Usuario{
    
@Column(name = "matricula")
private Long matricula;

private Double puntuacion;
private Double costoConsulta;

@OneToOne
private Especializacion especializacion;

@OneToMany
private List<Disponibilidad> turnos;
}
