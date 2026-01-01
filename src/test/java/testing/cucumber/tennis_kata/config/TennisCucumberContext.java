package testing.cucumber.tennis_kata.config;

import org.springframework.stereotype.Component;
import testing.cucumber.tennis_kata.TennisScore;

/**
 * 🧠 TennisCucumberContext
 * 
 * Este componente es un Spring Bean que actúa como "World" o "Contexto Shared".
 * 
 * ❓ ¿Por qué es necesario?
 * En Cucumber, cada Step Definition puede estar en una clase distinta. Para
 * compartir
 * el estado (ej. la instancia del juego actual) entre Given, When y Then sin
 * usar
 * variables estáticas (que ensucian los tests), inyectamos este bean en los
 * steps.
 */
@Component
public class TennisCucumberContext {

    private TennisScore tennisScore;

    public TennisScore getTennisScore() {
        return tennisScore;
    }

    public void setTennisScore(TennisScore tennisScore) {
        this.tennisScore = tennisScore;
    }
}
