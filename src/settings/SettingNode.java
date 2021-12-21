package settings;

import app.Updatable;
import javafx.scene.Node;

/**
 * Abstract class depicting a SettingNode, functionality should be implemented using the following methods
 * <ul><li>{@link #initialize()}: Executed immediately after the {@link #SettingNode(String, Category...)} constructor</li>
 * <li>{@link #getNode()}: Returns the node of the setting editor</li></ul>
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public abstract class SettingNode implements Updatable {

    /**
     * Array of categories the setting is classified under
     */
    private final Category[] categories;
    /**
     * Display name of the setting
     */
    private final String name;

    /**
     * Creates a new abstract SettingNode with the setting name and its categories
     *
     * @param name       Display name of the setting
     * @param categories Categories to categorize the setting under
     */
    public SettingNode(String name, Category... categories) {
        this.name = name;
        this.categories = categories;
        initialize();
    }

    /**
     * Method called immediately after the constructor, used for initializing and configuring the editing nodes
     */
    public abstract void initialize();

    /**
     * Checks if the SettingNode is classified under a specific category
     *
     * @param category Category to check if SettingNode is a part of
     *
     * @return {@code true} if the setting node is classified under the given category, {@code false} otherwise
     */
    public boolean isCategory(Category category) {
        for (Category c : categories) {
            if (c == category) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the node used to edit the setting
     *
     * @return Node of the setting editor
     */
    public abstract Node getNode();

    public String toString() {
        return getName();
    }

    /**
     * Gets the display name of the object
     *
     * @return Name of the setting
     */
    public String getName() {
        return name;
    }
}