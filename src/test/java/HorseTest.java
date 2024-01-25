import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("Horse Test")
public class HorseTest {

    @Test
    void constructorNullNameThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 30.0, 0));
    }

    @Test
    void constructorNullNameExceptionHasMessage() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 30.0, 0));

        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    ", "        ", "    "})
    void constructorBlankNameThrowsException(String horseName) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(horseName, 30.0, 0));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "  ", "    ", "        ", "    "})
    void constructorBlankNameExceptionHasMessage(String horseName) {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(horseName, 30.0, 0));

        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    void constructorNegativeSpeedThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Ivan", -10, 0));
    }

    @Test
    void constructorNegativeSpeedExceptionHasMessage() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Ivan", -10, 0));

        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    void constructorNegativeDistanceThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Ivan", 30.0, -15));
    }

    @Test
    void constructorNegativeDistanceExceptionHasMessage() {
        final IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Ivan", 30.0, -15));

        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    @Test
    void getNameReturnsName() {
        final String givenName = "Ivan";
        Horse horse = new Horse(givenName, 30, 0);

        assertEquals(givenName, horse.getName());
    }

    @Test
    void getSpeedReturnsSpeed() {
        final int givenSpeed = 15;
        Horse horse = new Horse("Ivan", givenSpeed, 0);

        assertEquals(givenSpeed, horse.getSpeed());
    }

    @Test
    void getDistanceReturnsDistance() {
        final int givenDistance = 22;
        Horse horse = new Horse("Ivan", 15, givenDistance);

        assertEquals(givenDistance, horse.getDistance());
    }

    @Test
    void getDistanceReturnZeroByDefault() {
        assertEquals(0, new Horse("Ivan", 20).getDistance());
    }

    @Test
    void moveUsesGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            new Horse("Ivan", 20).move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.4, 0.9})
    void moveSetCorrectDistance(double randomDouble) {
        try (MockedStatic<Horse> mockedHorse = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse("Ivan", 30, 4);
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomDouble);

            horse.move();

            assertEquals(4 + 30 * randomDouble, horse.getDistance());
        }
    }
}
