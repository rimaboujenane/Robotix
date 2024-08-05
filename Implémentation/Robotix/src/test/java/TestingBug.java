import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestingBug {
    @BeforeEach
    void setUp() {
        System.out.println("Before each");
    }
    @Test
    void test1() {
        System.out.println("test1");
    }
    @Test
    void test2() {
        System.out.println("test1");
    }
    @Test
    void test3() {
        System.out.println("test1");
    }
    @Test
    void test4() {
        System.out.println("test1");
    }
}
