/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.taller;

/**
 *
 * @author 104623010352 John Faber Navia Narvaez
 */
public class Vehiculo {

    private String marca;
    private String modelo;
    private String placa;
    private String horaIngreso;
    private String horaSalida;

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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getHoraIngreso() {
        return horaIngreso;
    }

    public String getHoraSalida() {
        return horaSalida;
    }

    public void setHoraIngreso(String horaIngreso) {
        this.horaIngreso = horaIngreso;
    }

    public void setHoraSalida(String horaSalida) {
        this.horaSalida = horaSalida;
    }

}
