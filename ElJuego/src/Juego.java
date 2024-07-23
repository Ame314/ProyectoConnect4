package juego;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Juego extends JFrame {

    private static final int FILAS = 6;
    private static final int COLUMNAS = 7;

    private final JButton[][] botones;
    private final char[][] tablero = new char[FILAS][COLUMNAS];
    private final Jugador jugadorBlanco = new Jugador("Blanco", 'B', Color.WHITE);
    private final Jugador jugadorRojo = new Jugador("Rojo", 'R', Color.RED);
    private Jugador jugadorActual = jugadorBlanco;
    private boolean hayGanador = false;
    private int puntajeBlanco = 0;
    private int puntajeRojo = 0;

    public Juego() {
        botones = new JButton[FILAS][COLUMNAS];
        setLayout(new GridLayout(FILAS + 1, COLUMNAS)); // +1 para incluir el botón de reinicio en la última fila

        // Crear los botones del tablero
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                botones[fila][columna] = new JButton();
                botones[fila][columna].setPreferredSize(new Dimension(50, 50));
                botones[fila][columna].setFont(new Font("Arial", Font.BOLD, 24));
                botones[fila][columna].addActionListener(new BotonListener(columna));
                add(botones[fila][columna]);
            }
        }

        // Botón de reinicio
        JButton reiniciarButton = new JButton("Reiniciar");
        reiniciarButton.addActionListener(e -> inicializarJuego());
        for (int columna = 0; columna < COLUMNAS; columna++) {
            add(reiniciarButton);
        }

        // Configurar el cierre de la ventana
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                mostrarGanadorFinal();
                System.exit(0);
            }
        });

        pack();
        setVisible(true);
        inicializarJuego();
    }

    public void inicializarJuego() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                tablero[fila][columna] = ' ';
                botones[fila][columna].setText("");
                botones[fila][columna].setEnabled(true);
                botones[fila][columna].setBackground(Color.LIGHT_GRAY);
            }
        }
        hayGanador = false;
        jugadorActual = jugadorBlanco;
    }

    public void colocarFicha(int columna) {
        for (int fila = FILAS - 1; fila >= 0; fila--) {
            if (tablero[fila][columna] == ' ') {
                tablero[fila][columna] = jugadorActual.getFicha();
                botones[fila][columna].setText(String.valueOf(jugadorActual.getFicha()));
                botones[fila][columna].setEnabled(false);
                botones[fila][columna].setBackground(jugadorActual.getColor());
                if (comprobarGanador(fila, columna)) {
                    hayGanador = true;
                    JOptionPane.showMessageDialog(null, "El jugador " + jugadorActual.getNombre() + " ha ganado!");
                    actualizarPuntajes(jugadorActual);
                    inicializarJuego();
                } else if (esEmpate()) {
                    JOptionPane.showMessageDialog(null, "¡Es un empate!");
                    inicializarJuego();
                } else {
                    cambiarTurno();
                }
                break;
            }
        }
    }

    public boolean comprobarGanador(int fila, int columna) {
        char ficha = tablero[fila][columna];
        return (contarFichas(fila, columna, 1, 0) >= 4 || // Horizontal
                contarFichas(fila, columna, 0, 1) >= 4 || // Vertical
                contarFichas(fila, columna, 1, 1) >= 4 || // Diagonal descendente
                contarFichas(fila, columna, 1, -1) >= 4); // Diagonal ascendente
    }

    private int contarFichas(int fila, int columna, int deltaFila, int deltaColumna) {
        char ficha = tablero[fila][columna];
        int contador = 1;
        int i = fila + deltaFila;
        int j = columna + deltaColumna;
        while (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS && tablero[i][j] == ficha) {
            contador++;
            i += deltaFila;
            j += deltaColumna;
        }
        i = fila - deltaFila;
        j = columna - deltaColumna;
        while (i >= 0 && i < FILAS && j >= 0 && j < COLUMNAS && tablero[i][j] == ficha) {
            contador++;
            i -= deltaFila;
            j -= deltaColumna;
        }
        return contador;
    }

    public boolean esEmpate() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (tablero[fila][columna] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public void cambiarTurno() {
        jugadorActual = (jugadorActual == jugadorBlanco) ? jugadorRojo : jugadorBlanco;
    }

    public void actualizarPuntajes(Jugador jugador) {
        if (jugador == jugadorBlanco) {
            puntajeBlanco++;
        } else {
            puntajeRojo++;
        }
    }

    public void presentarTablero() {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                System.out.print(tablero[fila][columna] + " ");
            }
            System.out.println();
        }
    }

    private void mostrarGanadorFinal() {
        String mensaje;
        if (puntajeBlanco > puntajeRojo) {
            mensaje = "El jugador Blanco ha ganado con " + puntajeBlanco + " puntos!";
        } else if (puntajeRojo > puntajeBlanco) {
            mensaje = "El jugador Rojo ha ganado con " + puntajeRojo + " puntos!";
        } else {
            mensaje = "¡Es un empate con " + puntajeBlanco + " puntos cada uno!";
        }
        JOptionPane.showMessageDialog(null, mensaje);
    }

    private class BotonListener implements ActionListener {
        private final int columna;

        public BotonListener(int columna) {
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!hayGanador) {
                colocarFicha(columna);
                presentarTablero();
            }
        }
    }

    public static void main(String[] args) {
        new Juego();
    }

    private class Jugador {
        private final String nombre;
        private final char ficha;
        private final Color color;

        public Jugador(String nombre, char ficha, Color color) {
            this.nombre = nombre;
            this.ficha = ficha;
            this.color = color;
        }

        public String getNombre() {
            return nombre;
        }

        public char getFicha() {
            return ficha;
        }

        public Color getColor() {
            return color;
        }
    }
}
