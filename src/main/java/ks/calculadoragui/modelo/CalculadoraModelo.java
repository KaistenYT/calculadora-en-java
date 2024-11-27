package ks.calculadoragui.modelo;

import javax.swing.JOptionPane;

public class CalculadoraModelo {
    private double numero1; 
    private double numero2;
    private String operador; // Cambiado a String para almacenar operadores como "+"
    private boolean operacionPendiente; 

    public CalculadoraModelo() {
        this.operacionPendiente = false;
    }

    public void setNumero1(double numero1) {
        this.numero1 = numero1;
    }

    public void setNumero2(double numero2) {
        this.numero2 = numero2;
    }

    public void setOperador(String operador) {
        this.operador = operador;
    }

    public double calcular() {
        double resultado;
        switch (operador) {
            case "+" -> resultado = numero1 + numero2;
            case "-" -> resultado = numero1 - numero2;
            case "*" -> resultado = numero1 * numero2;
            case "/" -> {
                if (numero2 == 0) {
                    JOptionPane.showMessageDialog(null, "Error: no se puede dividir entre 0");
                    return 0; // Retorna 0 en caso de división por cero.
                } else {
                    resultado = numero1 / numero2;
                }
            }
            default -> throw new IllegalArgumentException("Operador no soportado: " + operador);
        }
        operacionPendiente = false;
        return resultado;
    }

    public void limpiar() {
        numero1 = 0;
        numero2 = 0;
        operador = ""; // Limpia el operador asignando una cadena vacía
        operacionPendiente = false;
    }

    public boolean isOperacionPendiente() {
        return operacionPendiente;
    }

    public void setOperacionPendiente(boolean operacionPendiente) {
        this.operacionPendiente = operacionPendiente;
    }
}
