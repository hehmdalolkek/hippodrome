import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hippodrome Test")
public class HippodromeTest {

    @Test
    void constructorNullHorsesThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
    }

    @Test
    void constructorNullHorsesExceptionHasMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));

        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    void constructorEmptyHorsesThrowsException() {
        assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    void constructorEmptyHorsesExceptionHasMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));

        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    void getHorsesReturnsCurrentValues() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            horses.add(new Horse("" + i, i, i));
        }

        assertIterableEquals(horses, new Hippodrome(horses).getHorses());
    }

    @Test
    void moveUsesHorseMove() {
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            horses.add(Mockito.mock(Horse.class));
        }
        new Hippodrome(horses).move();

        for (Horse horse : horses)
            Mockito.verify(horse).move();
    }

    @Test
    void getWinnerReturnsCorrectValue() {
       Horse horse1 = new Horse("test1", 10, 20);
       Horse horse2 = new Horse("test2", 11, 1);
       Horse horse3 = new Horse("test3", 6, 33);
       Horse horse4 = new Horse("test4", 10, 32.999);
        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2,
                horse3, horse4));

        assertSame(horse3, hippodrome.getWinner());
    }
}
