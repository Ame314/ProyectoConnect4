Connect 4 Game
Este es un juego de Connect 4 implementado en Java usando JFrame y JButton para la interfaz gráfica. 
El juego permite a dos jugadores turnarse para colocar fichas en un tablero de 6 filas y 7 columnas, 
con el objetivo de ser el primero en formar una línea de cuatro fichas consecutivas, ya sea en horizontal, vertical o diagonal.

Características:
Interfaz Gráfica: Utiliza JFrame y JButton para la creación del tablero y la interacción del usuario.
Dos Jugadores: Soporta dos jugadores, cada uno con un color y ficha específicos.
Cambio de Turno: Alterna entre los jugadores después de cada movimiento.
Detección de Ganador: Comprueba si hay un ganador después de cada movimiento.
Reinicio del Juego: Permite reiniciar el juego en cualquier momento con un botón de reinicio.
Puntajes Acumulados: Mantiene los puntajes de ambos jugadores durante la sesión de juego.
Detección de Empate: Comprueba si el tablero está lleno sin un ganador y declara un empate.
Mensaje Final: Muestra un mensaje con el ganador final cuando se cierra la ventana del juego.

Cómo Jugar:
Inicio del Juego: Al iniciar la aplicación, se muestra un tablero vacío.
Colocar Fichas: Los jugadores hacen clic en los botones para colocar sus fichas en la columna deseada. 
La ficha caerá hasta la posición más baja disponible en esa columna.
Cambio de Turno: Después de colocar una ficha, el turno pasa al otro jugador.
Comprobación de Ganador: El juego comprueba si hay una línea de cuatro fichas consecutivas y declara al ganador si se encuentra una.
Reiniciar el Juego: Se puede reiniciar el juego en cualquier momento haciendo clic en el botón de reinicio.
Cerrar el Juego: Al cerrar la ventana del juego, se mostrará un mensaje indicando el ganador final basado en los puntajes acumulados.

Requisitos:
Java: Asegúrate de tener instalado Java para compilar y ejecutar el juego.

Ejecución:
Para ejecutar el juego, compila y ejecuta el archivo 
Juego.java:
javac Juego.java
java juego.Juego
