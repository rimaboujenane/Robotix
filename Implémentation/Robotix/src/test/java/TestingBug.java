import ift2255.robotix.Modeles.Testing;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestingBug {
    private Testing testing;
    @BeforeEach
    void setUp() {
        testing = new Testing();
        testing.print(true);

    }
    @Test
    void test1() {
        System.out.println("test1");
    }
    @Test
    void test2() {
        System.out.println("test2");
    }
}
