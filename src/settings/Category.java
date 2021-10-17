package settings;

/**
 * Categorization of settings. Each setting may have one or many categories. Filters within the SettingsEditor
 * allow the user to filter out settings by category.
 *
 * @author Thomas Kwashnak
 * @verion 1.0.0
 * @since 1.0.0
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

    /**
     * String name of the category with proper capitalization
     */
    final String display;

    /**
     * Creates a new category with a given display name
     *
     * @param display Name to display within the application when referring to the category
     */
    Category (String display) {
        this.display = display;
    }

    /**
     * Prints the specified display name instead of the default toString
     *
     * @return Display Name of the category
     */
    public String toString () {
        return display;
    }
}
