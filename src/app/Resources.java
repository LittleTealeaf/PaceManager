package app;

import java.io.InputStream;

public class Resources {

    /**
     * Grabs a system resources as a stream.
     * <p>
     * Make sure to run a {@code maven compile} cycle before testing, otherwise the file will not be included in
     * the resources
     * </p>
     *
     * @param name Resource Path of resource <br>If in a subdirectory, format such as {@code /dev/pace2021.json},
     *             if not format such as {@code pace2021.json}
     *
     * @return Input Stream of desired resource
     */
    public static InputStream getResource(String name) {
        return Resources.class.getResourceAsStream(name);
    }
}
