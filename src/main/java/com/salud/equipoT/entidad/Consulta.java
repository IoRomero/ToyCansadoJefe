package com.salud.equipoT.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "consulta")
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    private String diagnostico;
    private String tratamiento;
    private String motivoConsulta;

    @ManyToOne
    @JoinColumn(name = "doctor", referencedColumnName = "nombre")
    private Doctor doctor;
    @ManyToOne
    private Paciente paciente;
    @OneToOne
    private Turno turno;
}
