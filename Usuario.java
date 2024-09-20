import java.util.ArrayList;

public class Usuario {
    private String nombre; 
    private double gasto; 
    private double ahorro; 
    private String contraseña; 
    private Banco banco; 
    private ArrayList <Articulo> articulos = new ArrayList<>();
    

    public Usuario(String nombre, double gasto, String contraseña, Banco banco) {
        this.nombre = nombre;
        this.gasto = gasto;
        this.contraseña = contraseña;
        this.banco = banco;
    }
    
    public Usuario(String nombre, String contraseña) {
        this.nombre = nombre;
        this.contraseña = contraseña;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public double getGasto() {
        return gasto;
    }
    public void setGasto(double gasto) {
        this.gasto = gasto;
    }
    public double getAhorro() {
        return ahorro;
    }
    public void setAhorro(double ahorro) {
        this.ahorro = ahorro;
    }
    public String getContraseña() {
        return contraseña;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public Banco getBanco() {
        return banco;
    }
    public void setBanco(Banco banco) {
        this.banco = banco;
    }
    public ArrayList<Articulo> getArticulos() {
        return articulos;
    }
    public void setArticulos(Articulo articulo) {
        this.articulos.add(articulo);
    }

}
