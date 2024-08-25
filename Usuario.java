public class Usuario {
    private String nombre; 
    private double gasto; 
    private double ahorro; 
    private String contraseña; 
    private Banco banco; 
    private Articulo articulos[];
    public Usuario(String nombre, double gasto, String contraseña, Banco banco, Articulo[] articulos) {
        this.nombre = nombre;
        this.gasto = gasto;
        this.contraseña = contraseña;
        this.banco = banco;
        this.articulos = articulos;
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
    public Articulo[] getArticulos() {
        return articulos;
    }
    public void setArticulos(Articulo[] articulos) {
        this.articulos = articulos;
    }

}
