package com.salud.equipoT.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "paciente")
public class Paciente extends Usuario {

    @Column(name = "dni")
    private Long dni;

    @ManyToOne()
    @JoinColumn(name = "obra_social_id", referencedColumnName = "id")
    private ObraSocial obraSocial;
    @OneToOne()
    private HistoriaClinica historiaClinica;
   

}