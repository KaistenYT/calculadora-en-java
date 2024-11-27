package ks.calculadoragui.controlador;

import ks.calculadoragui.modelo.CalculadoraModelo;
import ks.calculadoragui.vista.CalculadoraUI;

public class ControladorCalculadora {
    private CalculadoraUI vista;
    private CalculadoraModelo modelo;

    public ControladorCalculadora(CalculadoraUI vista, CalculadoraModelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
        
        // Botones numéricos
        vista.getBtnNumero0().addActionListener(e -> agregarNumero("0"));
        vista.getBtnNumero1().addActionListener(e -> agregarNumero("1"));
        vista.getBtnNumero2().addActionListener(e -> agregarNumero("2"));
        vista.getBtnNumero3().addActionListener(e -> agregarNumero("3"));
        vista.getBtnNumero4().addActionListener(e -> agregarNumero("4"));
        vista.getBtnNumero5().addActionListener(e -> agregarNumero("5"));
        vista.getBtnNumero6().addActionListener(e -> agregarNumero("6"));
        vista.getBtnNumero7().addActionListener(e -> agregarNumero("7"));
        vista.getBtnNumero8().addActionListener(e -> agregarNumero("8"));
        vista.getBtnNumero9().addActionListener(e -> agregarNumero("9"));

        // Botones operandos
        vista.getBtnSumar().addActionListener(e -> manejarSignoPositivo());
        vista.getBtnRestar().addActionListener(e -> manejarSignoMenos());
        vista.getBtnMultiplicar().addActionListener(e -> setOperador("*"));
        vista.getBtnDividir().addActionListener(e -> setOperador("/"));

        // Botones de resultado, limpiar y borrar
        vista.getBtnResultado().addActionListener(e -> calcularResultado());
        vista.getBtnLimpiarPantalla().addActionListener(e -> limpiar());
        vista.getBtnBorrar().addActionListener(e -> borrar());
        vista.getBtnDecimal().addActionListener(e -> agregarDecimal());
    }
    
    private void agregarNumero(String numero) {
        String textoActual = vista.getTxtVisor().getText();
        vista.getTxtVisor().setText(textoActual + numero);
        actualizarNumeroEnModelo();
    }

    private void actualizarNumeroEnModelo() {
        try {
            double valorActual = Double.parseDouble(vista.getTxtVisor().getText());
            if (modelo.isOperacionPendiente()) {
                modelo.setNumero2(valorActual);
            } else {
                modelo.setNumero1(valorActual);
            }
        } catch (NumberFormatException e) {
            mostrarError("Entrada inválida actualizar numero");
        }
    }

    private void setOperador(String operador) {
        String textoActual = vista.getTxtVisor().getText();

        if (!modelo.isOperacionPendiente() && !textoActual.isEmpty()) {
            try {
                double numeroActual = Double.parseDouble(textoActual);
                modelo.setNumero1(numeroActual);
                modelo.setOperador(operador);
                vista.getTxtVisor().setText("");
                modelo.setOperacionPendiente(true);
            } catch (NumberFormatException e) {
                mostrarError("Entrada inválida en el set Operando");
            }
        } else {
            mostrarError("Entrada inválida");
        }
    }

    private void calcularResultado() {
        try {
            if (!vista.getTxtVisor().getText().isEmpty()) {
                modelo.setNumero2(Double.parseDouble(vista.getTxtVisor().getText()));
            }
            double resultado = modelo.calcular();
            vista.getTxtVisor().setText(String.valueOf(resultado));
            modelo.limpiar();
        } catch (ArithmeticException e) {
            mostrarError("División por cero");
        } catch (NumberFormatException e) {
            mostrarError("Entrada inválida");
        }
    }

    private void mostrarError(String mensaje) {
        vista.getTxtVisor().setText("Error: " + mensaje);
    }

    private void limpiar() {
        modelo.limpiar();
        vista.getTxtVisor().setText("");
    }

    private void borrar() {
        String cadena = vista.getTxtVisor().getText();
        if (cadena.length() > 0) {
            cadena = cadena.substring(0, cadena.length() - 1);
            vista.getTxtVisor().setText(cadena);
        }
    }

    private void agregarDecimal() {
        String textoActual = vista.getTxtVisor().getText();
        if (!textoActual.contains(".")) { 
            if (textoActual.isEmpty()) {
                vista.getTxtVisor().setText("0.");
            } else {
                vista.getTxtVisor().setText(textoActual + ".");
            }
        }
    }
    
    private void manejarSignoMenos() {
        String textoActual = vista.getTxtVisor().getText();

     
        if (textoActual.isEmpty()) {
            vista.getTxtVisor().setText("-");
        } else {
            
            setOperador("-");
        }
    }
    
    private void manejarSignoPositivo(){
         String textoActual = vista.getTxtVisor().getText();

     
        if (textoActual.isEmpty()) {
            vista.getTxtVisor().setText("+");
        } else {
            
            setOperador("+");
        }
    }
}
