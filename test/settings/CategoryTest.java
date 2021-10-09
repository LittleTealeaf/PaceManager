package settings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CategoryTest {

    @Test
    public void testCategories() {
        for(Category category : Category.values()) {
            assertNotNull(category.display);
        }
    }

}