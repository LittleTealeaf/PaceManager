package settings;

/**
 * Categorization of settings. Each setting may have one or many categories. Filters within the SettingsEditor
 * allow the user to filter out settings by category.
 */
public enum Category {
    /**
     * Category for more general settings that the user will most likely wish to change at some point
     */
    GENERAL("General"),
    /**
     * Category for any application specific settings
     */
    APPLICATION("Application"),
    /**
     * Settings pertaining more to the calculation of average times and winners
     */
    CALCULATIONS("Calculations"),
    /**
     * Settings pertaining to the storing of config or other files
     */
    FILES("Files"),
    /**
     * Settings pertaining to application optimizations, such as freeing up memory (allowing for garbage collection)
     * or other optimizations
     */
    OPTIMIZATIONS("Optimizations");


    final String display;

    Category(String display) {
        this.display = display;
    }

    public String toString() {
        return display;
    }
}
