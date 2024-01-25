import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

@DisplayName("Main Test")
public class MainTest {

    @Disabled
    @Test
    @Timeout(value = 22)
    void mainRunsIsNoLongerThan22Sec() {
        try {
            Main.main(new String[]{});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
