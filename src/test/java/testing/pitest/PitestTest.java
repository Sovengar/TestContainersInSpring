package testing.pitest;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * ═══════════════════════════════════════════════════════════════════════════════
 * PITEST - MUTATION TESTING DEMO
 * ═══════════════════════════════════════════════════════════════════════════════
 * This test is INTENTIONALLY WEAK.
 * It checks if 10 is positive, but it does NOT check 0.
 *
 * Use command:
 * ./mvnw test-compile org.pitest:pitest-maven:mutationCoverage
 *
 * Expected Result:
 * - A mutation "changed conditional boundary" (>= to >) will SURVIVE.
 * - This shows that our test suite is not robust enough.
 * ═══════════════════════════════════════════════════════════════════════════════
 */
class PitestTest {

    @Test
    void testIsPositive_WeakTest() {
        Calculator calc = new Calculator();

        // Weak Test: Only checks a number widely inside the range.
        // It fails to detect if logic changes to (number > 0)
        assertThat(calc.isPositive(10)).isTrue();
    }
}

class Calculator {
    /**
     * Checks if a number is positive (including zero).
     */
    public boolean isPositive(int number) {
        // Mutation Opportunity:
        // Pitest will try to change ">=" to ">" (Conditional Boundary Mutator).
        // If our test only checks "10", it will pass both conditions.
        // So the mutation will SURVIVE (which is bad/vulnerable).
        if (number >= 0) {
            return true;
        }
        return false;
    }
}