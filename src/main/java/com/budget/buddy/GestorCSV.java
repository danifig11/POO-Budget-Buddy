package com.budget.buddy;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class GestorCSV {

    private static final String DELIMITADOR = ",";  // Separador para el CSV

    // Guardar usuarios en el archivo CSV
    public static void guardarUsuarios(ArrayList<Usuario> usuarios, String rutaArchivo) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaArchivo))) {
            for (Usuario usuario : usuarios) {
                String linea = usuario.getNombre() + DELIMITADOR + usuario.getGasto(); 
                        // + usuario.getAhorro() + DELIMITADOR + usuario.getContraseña() 
                        // + DELIMITADOR + usuario.getBanco().getNombre() 
                        // + DELIMITADOR + usuario.getBanco().getPagina();
                writer.write(linea);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error al guardar el archivo CSV: " + e.getMessage());
        }
    }

    // Cargar usuarios desde el archivo CSV
    public static ArrayList<Usuario> cargarUsuarios(String rutaArchivo) {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] campos = linea.split(DELIMITADOR);
                String nombre = campos[0];
                double gasto = Double.parseDouble(campos[1]);
                double ahorro = Double.parseDouble(campos[2]);
                String contraseña = campos[3];
                Banco banco = new Banco(campos[4], campos[5]);
                Usuario usuario = new Usuario(nombre, gasto, contraseña);
                usuario.setAhorro(ahorro);
                usuarios.add(usuario);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo CSV: " + e.getMessage());
        }
        return usuarios;
    }
}