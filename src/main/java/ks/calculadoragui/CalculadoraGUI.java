package ks.calculadoragui;

import ks.calculadoragui.controlador.ControladorCalculadora;
import ks.calculadoragui.modelo.CalculadoraModelo;
import ks.calculadoragui.vista.CalculadoraUI;

public class CalculadoraGUI {

    public static void main(String[] args) {
        CalculadoraModelo modelo = new CalculadoraModelo();
        CalculadoraUI vista = new CalculadoraUI();
        ControladorCalculadora controladorCalculadora = new ControladorCalculadora(vista, modelo);
        vista.setVisible(true);
    }
}
