/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.taller;

import static spark.Spark.*;
import com.google.gson.Gson;
import java.util.Date;

import java.util.LinkedList;

/**
 *
 * @author 104623010352 John Faber Navia Narvaez
 */
public class Main {

    public static void main(String[] args) {

        //Crea el objeto fecha, permite acceder a la hora
        Date fecha = new Date();

        /*
        * Esto nos sirve para generar un formato json para retornar la data del array
        * sin tener que acomodarla bonita de manera manual
         */
        Gson gson = new Gson();

        LinkedList<Vehiculo> automoviles = new LinkedList<>();
        LinkedList<Motocicleta> motos = new LinkedList<>();

        // Automovil creado por defecto
        Automovil auto = new Automovil(4, "Mazda", "3", "ZYX987", (fecha.getHours() + ":" + fecha.getMinutes()));
        automoviles.add(auto);

        //moto creada por defecto
        Motocicleta moto = new Motocicleta(600, "Honda", "CBR600", "XYZ789", (fecha.getHours() + ":" + fecha.getMinutes()));
        motos.add(moto);

        // Definir endpoints
        // Por defecto 
        get("/motocicleta", (req, res) -> {
            res.type("application/json");
            return gson.toJson(moto);
        });

        // Listado de automovile
        get("/automoviles", (req, res) -> {
            res.type("application/json");
            return gson.toJson(automoviles);
        });

        // Listado de motos
        get("/motos", (req, res) -> {
            res.type("application/json");
            return gson.toJson(motos);
        });

        // Guardar automovil
        // endpoint GET para agregar un automóvil
        get("/agregarAutomovil/:marca/:modelo/:placa/:numeroPuertas", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // objeto fecha
            Date nuevaFecha = new Date();
            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int numeroPuertas = Integer.parseInt(req.params(":numeroPuertas"));

            // Crear un nuevo automóvil y agregarlo al parqueadero
            Automovil nuevoAuto = new Automovil(numeroPuertas, marca, modelo, placa, (nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
            automoviles.add(nuevoAuto);

            return gson.toJson(nuevoAuto);
        });

        // Guardar moto: endpoint get
        get("/agregarMoto/:marca/:modelo/:placa/:cilindrada", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // objeto fecha
            Date nuevaFecha = new Date();
            // Obtener parámetros de la URL
            String marca = req.params(":marca");
            String modelo = req.params(":modelo");
            String placa = req.params(":placa");

            // No olvidar convertir en integer los string numericos que llegan por url
            int cilindrada = Integer.parseInt(req.params(":cilindrada"));

            // Crear una nueva moto y agregarla al parqueadero
            Motocicleta nuevaMoto = new Motocicleta(cilindrada, marca, modelo, placa, (nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
            motos.add(nuevaMoto);

            return gson.toJson(nuevaMoto);
        });

        //endpoint registrar hora de salida
        get("/registrarSalidaAutomovil/:placa", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // objeto fecha
            Date nuevaFecha = new Date();

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // Buscar en automóviles
            for (Vehiculo automovil : automoviles) {
                if (automovil.getPlaca().equals(placa)) {
                    automovil.setHoraSalida((nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
                    return gson.toJson(automovil);
                }

            }
            return gson.toJson("Automovil no encontrado en el parqueadero");
        });

        //endpoint registrar hora de salida
        get("/registrarSalidaMoto/:placa", (req, res) -> {

            // Añadimos esto para retornar en formato Json
            res.type("application/json");

            // objeto fecha
            Date nuevaFecha = new Date();

            // Obtener parámetros de la URL
            String placa = req.params(":placa");

            // Buscar la motocicleta en el parqueadero
            for (Motocicleta motocicleta : motos) {
                if (motocicleta.getPlaca().equals(placa)) {
                    motocicleta.setHoraSalida((nuevaFecha.getHours() + ":" + nuevaFecha.getMinutes()));
                    return gson.toJson(motocicleta);
                }
            }
            return gson.toJson("Motocicleta no encontrada en el parqueadero");
        });

        // Endpoint para verificar automóviles actuales en el parqueadero
        get("/AutomovilesActuales", (req, res) -> {
            res.type("application/json");

            LinkedList<Vehiculo> automovilesActuales = new LinkedList<>();

            // Filtrar automóviles que aún están en el parqueadero (no tienen hora de salida registrada)
            for (Vehiculo automovil : automoviles) {
                if (automovil.getHoraSalida() == null) {
                    automovilesActuales.add(automovil);
                }
            }

            return gson.toJson(automovilesActuales);
        });

        // Endpoint para verificar motos actuales en el parqueadero
        get("/motosActuales", (req, res) -> {
            res.type("application/json");

            LinkedList<Motocicleta> motosActuales = new LinkedList<>();

            // Filtrar motos que aún están en el parqueadero (no tienen hora de salida registrada)
            for (Motocicleta motocicleta : motos) {
                if (motocicleta.getHoraSalida() == null) {
                    motosActuales.add(motocicleta);
                }
            }

            return gson.toJson(motosActuales);
        });

        // Endpoint para obtener informe de ganancias de motocicletas
        get("/motosReporte", (req, res) -> {
            res.type("application/json");

            LinkedList<String> informeMotos = new LinkedList<>();
            for (Motocicleta m : motos) {
                informeMotos.add("Placa: " + m.getPlaca()
                        + ", Ganancia: " + m.calcularGanancia()
                        + ", Hora de Entrada: " + m.getHoraIngreso()
                        + ", Hora de Salida: " + m.getHoraSalida());
            }

            return new Gson().toJson(informeMotos);
        });

        // Endpoint para obtener informe de ganancias de automóviles
        get("/AutomovilesReporte", (req, res) -> {
            res.type("application/json");

            LinkedList<String> informeAutomoviles = new LinkedList<>();
            for (Vehiculo a : automoviles) {
                informeAutomoviles.add("Placa: " + a.getPlaca()
                        + ", Ganancia: " + a.calcularGanancia()
                        + ", Hora de Entrada: " + a.getHoraIngreso()
                        + ", Hora de Salida: " + a.getHoraSalida());
            }
            return new Gson().toJson(informeAutomoviles);
        });
        
        // Endpoint para obtener el total de ganancias de motocicletas del día
        get("/totalGananciasMotos", (req, res) -> {
            res.type("application/json");

            // Calcular el total de ganancias de motocicletas del día
            int totalGananciasMotos = 0;
            for (Motocicleta m : motos) {
                totalGananciasMotos += m.calcularGanancia();
            }

            return new Gson().toJson(totalGananciasMotos);
        });
        
        // Endpoint para obtener el total de ganancias de automoviles del día
        get("/totalGananciasAutomoviles", (req, res) -> {
            res.type("application/json");

            // Calcular el total de ganancias de automoviles del día
            int totalGananciasAutomoviles = 0;
            for (Vehiculo a : automoviles) {
                totalGananciasAutomoviles += a.calcularGanancia();
            }

            return new Gson().toJson(totalGananciasAutomoviles);
        });
           // Endpoint para obtener el total de ganancias del dia (autos + motos)
        get("/totalGananciasDia", (req, res) -> {
            res.type("application/json");

            // Calcular el total de ganancias del dia (autos + motos)
            int totalGananciasDia = 0;

            // Sumar las ganancias de motocicletas
            for (Motocicleta m : motos) {
                totalGananciasDia += m.calcularGanancia();
            }

            // Sumar las ganancias de automoviles
            for (Vehiculo a : automoviles) {
                totalGananciasDia += a.calcularGanancia();
            }

            return new Gson().toJson(totalGananciasDia);
        });
    }
}
