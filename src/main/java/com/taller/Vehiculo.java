/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author 104623010352 John Faber Navia Narvaez
 */
public class Vehiculo {

    @Setter @Getter private String marca, modelo, placa, horaIngreso, horaSalida;

    public Vehiculo(String marca, String modelo, String placa, String horaIngreso) {
        this.marca = marca;
        this.modelo = modelo;
        this.placa = placa;
        this.horaIngreso = horaIngreso;
    }

    public int calcularGanancia() {

        if (this.horaSalida == null) {
            return 2000;
        }
        
        String HoraIngreso = this.horaIngreso;
        String HoraSalida = this.horaSalida;
        //extrae del string las horas
        String[] partesHoraI = HoraIngreso.split(":");
        int horasIn = Integer.parseInt(partesHoraI[0]);
        //extrae del string las horas
        String[] partesHoraS = HoraSalida.split(":");
        int horasS = Integer.parseInt(partesHoraS[0]);

        return 2000 + ((horasS - horasIn) * 200);

    }

}
