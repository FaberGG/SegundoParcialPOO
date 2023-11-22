/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller;
/**
 *
 * @author 104623010352 John Faber Navia Narvaez
 */
public class Motocicleta extends Vehiculo {
    @Setter @Getter private int cilindrada;

    // Constructores, getters y setters

    // Otros métodos específicos para motocicletas

    public Motocicleta(int cilindrada, String marca, String modelo, String placa, String horaIngreso) {
        super(marca, modelo, placa, horaIngreso);
        this.cilindrada = cilindrada;
    }
}
