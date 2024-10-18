package com.budget.buddy;

public class CuestionarioFinanzas {

    // Lista de preguntas en un arreglo bidimensional para poder acceder a cada pregunta y sus posibles opciones
    //además, este array contiene a la opción correcta como un número (es tomado como string)
    private String[][] preguntas = {
        {"¿Qué es una tasa de interés?", 
         "1. El dinero que se paga por un préstamo", 
         "2. El porcentaje que gana el banco por cada transacción", 
         "3. La ganancia sobre una inversión", 
         "1"}, // Respuesta correcta es 1
        {"¿Qué es un fondo de emergencia?", 
         "1. Un préstamo a corto plazo", 
         "2. Dinero ahorrado para imprevistos", 
         "3. Una cuenta de inversión de alto riesgo", 
         "2"}, // Respuesta correcta es 2
        {"¿Cuál es una buena práctica para controlar las finanzas personales?", 
         "1. Gastar más de lo que se gana", 
         "2. Ahorrar al menos el 20% de los ingresos", 
         "3. Invertir en productos que no entiendes", 
         "2"}, // Respuesta correcta es 2
        {"¿Qué significa diversificar una inversión?", 
         "1. Invertir en diferentes tipos de activos", 
         "2. Invertir todo en un solo activo de bajo riesgo", 
         "3. Invertir únicamente en bonos del gobierno", 
         "1"}, // Respuesta correcta es 1
        {"¿Qué es la inflación?", 
         "1. El aumento en los precios de bienes y servicios", 
         "2. La disminución del valor del dinero en el tiempo", 
         "3. Ambas son correctas", 
         "3"}  // Respuesta correcta es 3
    };

    // Getter 
    public String[][] getPreguntas() {
        return preguntas;
    }

    /**Este método es utilizado para verificar cuál es la respuesta correcta 
     * 
     * @param respuesta Aqui se toma la opción ingresada por el usuario 
     * @param respuestaCorrecta aqui se ingresa la opción correcta 
     * @return devuelve un boolean para indicar si es correcto o incorrecto 
     * 
     */
    public boolean verificarRespuesta(int respuesta, int respuestaCorrecta) {
        return respuesta == respuestaCorrecta;
    }
}
