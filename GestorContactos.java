package gestorcontactos;

import java.io.*;
import java.util.*;

public class GestorContactos {
    private List<Contacto> contactos;
    private final String archivo = "contactos.txt";

    public GestorContactos() {
        contactos = new ArrayList<>();
        cargarContactos();
    }

    public void agregarContacto(Contacto c) {
        contactos.add(c);
        guardarContactos();
    }

    public void mostrarContactos() {
        if (contactos.isEmpty()) {
            System.out.println("No hay contactos.");
        } else {
            for (Contacto c : contactos) {
                System.out.println(c);
            }
        }
    }

    public Contacto buscarContacto(String nombre) {
        for (Contacto c : contactos) {
            if (c.getNombre().equalsIgnoreCase(nombre)) {
                return c;
            }
        }
        return null;
    }

    public boolean eliminarContacto(String nombre) {
        Iterator<Contacto> it = contactos.iterator();
        while (it.hasNext()) {
            if (it.next().getNombre().equalsIgnoreCase(nombre)) {
                it.remove();
                guardarContactos();
                return true;
            }
        }
        return false;
    }

    private void guardarContactos() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(archivo))) {
            for (Contacto c : contactos) {
                pw.println(c);
            }
        } catch (IOException e) {
            System.out.println("Error al guardar los contactos.");
        }
    }

    private void cargarContactos() {
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                if (datos.length == 3) {
                    contactos.add(new Contacto(datos[0], datos[1], datos[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("No se pudo cargar el archivo. Se creará uno nuevo.");
        }
    }
}