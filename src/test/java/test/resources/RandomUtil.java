package test.resources;

import java.util.Random;

/**
 * Provides a static random object to use during testing
 */
public interface RandomUtil {

    /**
     * An instance of the {@link Random} object
     */
    Random RANDOM = new Random();
}
