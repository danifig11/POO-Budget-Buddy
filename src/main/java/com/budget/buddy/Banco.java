package com.budget.buddy;
public class Banco {
private String nombre; 
private String pagina;
public Banco(String nombre, String pagina) {
    this.nombre = nombre;
    this.pagina = pagina;
}
public String getNombre() {
    return nombre;
}
public void setNombre(String nombre) {
    this.nombre = nombre;
}
public String getPagina() {
    return pagina;
}
public void setPagina(String pagina) {
    this.pagina = pagina;
} 

}
