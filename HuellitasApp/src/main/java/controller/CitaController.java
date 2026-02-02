package controller;


import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import modelo.Cita;
import modelo.Mascota;
import servicio.CitaServicio;

public class CitaController {

    private CitaServicio servicio = new CitaServicio();
    private Scanner sc = new Scanner(System.in);

    public void menu() {

        int opcion;

        do {
            System.out.println("\n--- MENU CITAS ---");
            System.out.println("1. Crear cita");
            System.out.println("2. Listar citas");
            System.out.println("3. Buscar cita por id");
            System.out.println("4. Modificar cita");
            System.out.println("5. Eliminar cita");
            System.out.println("0. Salir");

            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1 -> crearCita();
                case 2 -> listarCitas();
                case 3 -> buscarCita();
                case 4 -> modificarCita();
                case 5 -> eliminarCita();
                case 0 -> System.out.println("Saliendo...");
                default -> System.out.println("Opción inválida");
            }

        } while (opcion != 0);
    }

    // =========================
    // MÉTODOS CRUD
    // =========================

    private void crearCita() {
        try {
            Cita c = new Cita();
            Mascota m = new Mascota();

            System.out.println("Mascota id: ");
            int mascotaId = sc.nextInt();
            sc.nextLine();
            m.setMascotaId(mascotaId);
            c.setMascota(m);

           
            System.out.println("Fecha (YYYY-MM-DD HH:MM:SS): ");
            System.out.println("\nFormato: 2026-01-28 10:30:00");
            c.setFechaHora(Timestamp.valueOf(sc.nextLine()));

            System.out.println("Motivo de la cita: ");
            c.setCitaTipo(sc.nextLine());

            boolean ok = servicio.crearCita(c);

            if (ok) System.out.println(" Cita creada correctamente");
            else System.out.println(" No se pudo crear la cita");

        } catch (Exception e) {
            System.out.println(" Error al crear cita: " + e.getMessage());
        }
    }


    private void listarCitas() {
        try {
            List<Cita> lista = servicio.listarCita();
            for (Cita c : lista) {
                System.out.println(c);
            }
        } catch (Exception e) {
            System.out.println(" Error al listar citas: " + e.getMessage());
        }
    }

    private void buscarCita() {
        try {
            System.out.println("Cita id: ");
            int id = sc.nextInt();
            sc.nextLine();

            Cita c = servicio.buscarCitaPorId(id);

            if (c == null) System.out.println("La cita no existe");
            else System.out.println(c);

        } catch (Exception e) {
            System.out.println(" Error al buscar cita: " + e.getMessage());
        }
    }

    private void modificarCita() {
        try {
            System.out.println("Cita id: ");
            int id = sc.nextInt();
            sc.nextLine();

            Cita c = servicio.buscarCitaPorId(id);
            if (c == null) {
                System.out.println("La cita no existe");
                return;
            }

            System.out.println("Nueva fecha (YYYY-MM-DD): Hora (HH:MM:SS): ");
            System.out.println("\nFormato: 2026-01-28 10:30:00");
            c.setFechaHora(Timestamp.valueOf(sc.nextLine()));

          //  System.out.println("Nueva hora (HH:MM): ");
           // c.setFechaHora(sc.nextLine());

            boolean ok = servicio.modificarCita(c);

            if (ok) System.out.println(" Cita modificada correctamente");
            else System.out.println(" No se pudo modificar");

        } catch (Exception e) {
            System.out.println(" Error al modificar cita: " + e.getMessage());
        }
    }

    private void eliminarCita() {
        try {
            System.out.print("Cita id: ");
            int id = sc.nextInt();

            boolean ok = servicio.borrarCita(id);
            if (ok) System.out.println(" Cita eliminada");
            else System.out.println(" No existe la cita");

        } catch (Exception e) {
            System.out.println(" Error al borrar cita: " + e.getMessage());
        }
    }
}
