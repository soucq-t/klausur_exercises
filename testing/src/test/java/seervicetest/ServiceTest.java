package seervicetest;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import service.Service;

import static org.junit.jupiter.api.Assertions.*;


public class ServiceTest {
    private Service service;
    @BeforeEach
     void setUp(){
        service = new Service();
    }
    @Nested

    class isPrime{
        @Test
        void work(){
            assertTrue(service.isPrime(7));
            assertFalse(service.isPrime(4));

        }
    }

    @Nested

    class min{
        @Test
        void work(){
            assertEquals(1, service.min(new int[]{5, 1, 9, 3}));
            assertEquals(-2, service.min(new int[]{5, -2, 8, 0}));

        }
    }

    @Test
    void reverse() {
        assertEquals(321, service.reverse(123));
        assertEquals(-321, service.reverse(-123));
    }

    @Test
    void isPalindrome() {
        assertTrue(service.isPalindrome("racecar"));
        assertTrue(service.isPalindrome("A man a plan a canal Panama"));
        assertFalse(service.isPalindrome("hello"));
    }
    @Test
    void areAmicableNumbers() {
        assertTrue(service.areAmicableNumbers(220, 284));
        assertFalse(service.areAmicableNumbers(10, 20));
    }

    @Test
    void isAnagram() {
        assertTrue(service.isAnagram("listen", "silent"));
        assertFalse(service.isAnagram("hello", "world"));
    }

}
