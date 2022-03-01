package data.structure;

import org.junit.jupiter.api.Test;
import test.resources.RandomUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ClockTest implements RandomUtil {

    @Test
    void emptyConstructor() {
        Clock clock = new Clock();
        assertEquals(0, clock.getTime());
    }

    @Test
    void getTime() {
        int val = RANDOM.nextInt();
        Clock clock = new Clock(val);
        assertEquals(val, clock.getTime());
        val = RANDOM.nextInt();
        assertNotEquals(val, clock.getTime());
        clock.setTime(val);
        assertEquals(val, clock.getTime());
    }

    @Test
    void setTime() {
        Clock clock = new Clock();
        int val = RANDOM.nextInt();
        assertNotEquals(val, clock.getTime());
        clock.setTime(val);
        assertEquals(val, clock.getTime());
    }
}