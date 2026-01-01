package testing.cucumber.tennis_kata;

/**
 * 🎾 TennisScore: Lógica de dominio para la Kata de Tenis.
 * 
 * Esta clase encapsula las reglas de puntuación de un partido de tenis:
 * 1. Los puntos progresan de Love (0), Fifteen (15), Thirty (30) a Forty (40).
 * 2. Si ambos jugadores tienen 3 puntos (Forty) o más y están empatados, es
 * "Deuce".
 * 3. Si un jugador tiene un punto de ventaja sobre el otro tras el Deuce, es
 * "Advantage".
 * 4. Para ganar, un jugador debe llegar a 4 puntos con al menos 2 puntos de
 * diferencia.
 */
public class TennisScore {
    public static final String[] SCORES = { "Love", "Fifteen", "Thirty", "Forty" };
    private int player1Score;
    private int player2Score;

    /**
     * Calcula y retorna el estado actual del marcador en formato texto.
     */
    public String score() {
        // Regla 4: Ganar el juego
        if (player1Score >= 4 && player1Score - player2Score >= 2) {
            return "Game won Player1";
        } else if (player2Score >= 4 && player2Score - player1Score >= 2) {
            return "Game won Player2";
        }

        // Reglas 2 y 3: Deuce y Advantage
        if (player1Score >= 3 && player2Score >= 3) {
            if (player1Score == player2Score) {
                return "Deuce";
            } else if (player1Score == player2Score + 1) {
                return "Advantage Player1";
            } else if (player2Score == player1Score + 1) {
                return "Advantage Player2";
            } else {
                // Este caso solo ocurriría si no se detectó la victoria antes
                throw new IllegalStateException("Unexpected score state!");
            }
        } else {
            // Regla 1: Puntuación normal (0, 15, 30, 40)
            return SCORES[player1Score] + "-" + SCORES[player2Score];
        }
    }

    /**
     * Añade un punto al jugador especificado (1 o 2).
     */
    public void addPoint(int playerNumber) {
        if (playerNumber == 1) {
            player1Score++;
        } else {
            player2Score++;
        }
    }

}
