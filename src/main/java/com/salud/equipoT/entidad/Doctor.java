package com.salud.equipoT.entidad;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.salud.equipoT.enums.Rol;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "doctor")
public class Doctor {

  @Id
  @Column(name = "id")
  private Long matricula;

  private String nombre;
  private Double puntuacion;
  private Double costoConsulta;

  private String email;
  private String password;

  @OneToOne
  @JoinColumn(name = "idimagen", referencedColumnName = "id")
  private Imagen imagen;

  @Enumerated(EnumType.STRING)
  private Rol rol;

  @OneToOne
  private Especializacion especializacion;

  @OneToMany
  private List<Turno> turnos;
}
