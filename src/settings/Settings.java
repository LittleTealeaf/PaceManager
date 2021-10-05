package settings;

import app.App;
import app.Launcher;
import app.Serialization;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

//TODO use standardized description style

/**
 * Class containing all current settings of the program. Stores these values in a location dependent on the operating
 * system. Or at least that is what it's supposed to do.
 *
 * @author Thomas Kwashnak
 * @version 1.0.0
 * @since 1.0.0
 */
public class Settings {

    /**
     * File object referencing to where the Settings JSON is stored
     */
    public static transient File settingsFile;

    /**
     * Current application version of the settings object
     *
     * @settings.default see {@link App#version}
     * @see App#version
     *
     */
    private final String settingsVersion;
    /**
     * List of recently opened Pace files
     *
     * @settings.default Empty List
     * @see Launcher
     *
     */
    private final List<String> recentFiles;

    /**
     * What the file extension should be for files opened by the program. Extensions are in the format {@code .___}
     *
     * @settings.default [{@code .pace}, {@code .json}]
     *
     */
    private String[] fileExtensions;
    /**
     * Default directory to begin looking for pace files in
     *
     * @settings.default User Documents directory
     * @see Launcher
     *
     */
    private String paceDirectory;

    /**
     * Determines if the file should be automatically saved every update
     *
     * @settings.default {@code true}
     * @see App#update()
     *
     */
    private boolean aggressiveSave;

    /**
     * Determines if additional steps are taken to reduce memory used by the program.
     *
     * @settings.default {@code false}
     *
     */
    private boolean aggressiveMemorySave;

    /**
     * Determines whether the launcher should be initially maximized to the whole screen or as a small and movable window
     * <p>{@code true} if the launcher should be maximized, {@code false} otherwise</p>
     *
     * @settings.default {@code false}
     *
     */
    private boolean launcherMaximized;
    /**
     * Determines whether the application should be initially maximized to the whole screen or as a small and movable
     * window<p>{@code true} if the application should be maximized, {@code false} otherwise</p>
     *
     * @settings.default {@code true}
     *
     */
    private boolean appMaximized;

    /**
     * Determines if the application should warn the user first before deleting anything or to just delete it outright
     * <p>If set to {@code true}, will display a prompt to the user asking if they really want to delete whatever,
     * and then if they verify that they wish to delete it, will continue with the process</p>
     *
     * @settings.default {@code true}
     * @see App#warnDelete(String)
     *
     */
    private boolean warnOnDelete;

    /**
     * <p>Whether the fastest and slowest team should be excluded from averages.</p>
     * <ul><li>If set to {@code true}, then the slowest and fastest team in any division will not be included when
     * calculating the average time. This will only happen if the division has at least 3 riders, in which the
     * calculated average will be of at least one team</li>
     * <li>If set to {@code false}, then all teams within a division will be included when calculating the average time
     * </li></ul>
     *
     * @settings.default {@code true}
     *
     */
    private boolean excludeOutliers;

    /**
     * Whether to default to the average if no goal-time is presented for a division
     *
     * @settings.default {@code true}
     *
     */
    private boolean useAverageAsGoalTime;

    /**
     * Allows for multiple teams editing windows to be opened
     */
    private boolean multipleTeamsEditing;

    /**
     * Creates a new {@code Settings} object and sets values to their defaults.
     *
     *
     */
    public Settings() {
        aggressiveMemorySave = false;
        fileExtensions = new String[]{".pace", ".json"};
        settingsVersion = App.version;
        aggressiveSave = true;
        paceDirectory = System.getProperty("user.home") + File.separatorChar + "Documents" + File.separatorChar;
        launcherMaximized = false;
        appMaximized = true;
        warnOnDelete = true;
        recentFiles = new ArrayList<>();
        excludeOutliers = true;
        multipleTeamsEditing = false;
    }

    /**
     * Saves the current configuration
     *
     *
     */
    public void save() {
        if (settingsFile.getParentFile().mkdirs()) {
            System.out.println("Created " + settingsFile.getParentFile().getPath());
        }
        try {
            FileWriter writer = new FileWriter(settingsFile);
            Serialization.getGson().toJson(this, Settings.class, writer);
            writer.close();
        } catch (Exception ignored) {}
    }

    /**
     * Returns whether to aggressively save memory (ram)
     *
     * @return {@code true} if the setting is set to true, {@code false} if the setting is set to false
     * @see #aggressiveMemorySave
     *
     */
    public boolean isAggressiveMemorySave() {
        return aggressiveMemorySave;
    }

    /**
     * <p> Sets whether the program should be concerned about aggressively saving values</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param value {@code true} if the program should take extra steps to reduce memory, {@code false} if not
     * @see #aggressiveMemorySave
     *
     */
    public void setAggressiveMemorySave(boolean value) {
        //Only updates / saves if there is a change
        if (aggressiveMemorySave != value) {
            aggressiveMemorySave = value;
            save();
        }
    }

    /**
     * Gets the list of file extensions used when opening a pace file
     *
     * @return String array of valid file extensions. Extensions are in the format {@code .___}.
     * @see #fileExtensions
     *
     */
    public String[] getFileExtensions() {
        return fileExtensions;
    }

    /**
     * <p>Sets the list of file extensions.</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param fileExtensions String array of valid file extensions. Extensions are in the format {@code .___}.
     * @see #fileExtensions
     *
     */
    public void setFileExtensions(String[] fileExtensions) {
        this.fileExtensions = fileExtensions;
        save();
    }

    /**
     * Gets the pace directory path string
     *
     * @return String Path of the default directory to begin looking for pace files in
     * @see #paceDirectory
     *
     */
    public String getPaceDirectory() {
        return paceDirectory;
    }

    /**
     * <p>Sets the pace directory</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param directory String Path of the default directory to begin looking for pace files in
     * @see #paceDirectory
     *
     */
    public void setPaceDirectory(String directory) {
        paceDirectory = directory;
    }

    /**
     * Returns a list of recently opened pace files
     *
     * @return List of recently opened files stored as strings of the file locations
     * @see #recentFiles
     *
     */
    public List<String> getRecentFiles() {
        return recentFiles;
    }

    /**
     * <p>Adds a new recent file to the list of recent files if that file is not already listed</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param path String Path representation of the new file to add
     * @see #recentFiles
     *
     */
    public void addRecentFile(String path) {
        if (path != null) {
            int length = recentFiles.size();
            for (int i = 0; i < length; i++) {
                if (recentFiles.get(i).contentEquals(path)) {
                    recentFiles.remove(i);
                    break;
                }
            }
            recentFiles.add(0, path);
        }
        save();
    }

    /**
     * <p>Clears the list of recently opened pace files</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @see #recentFiles
     *
     */
    public void cleanRecentFiles() {
        int size = recentFiles.size();
        for (int i = 0; i < size; i++) {
            if (recentFiles.get(i) == null || !new File(recentFiles.get(i)).exists()) {
                recentFiles.remove(i--);
                size--;
            }
        }
        save();
    }

    /**
     * Returns whether to aggressively save the pace file whenever a modification has been made to it
     *
     * @return {@code true} if a pace object should attempt to save whenever a modification ping is sent to it,
     * {@code false} otherwise
     * @see #aggressiveSave
     *
     */
    public boolean isAggressiveSave() {
        return aggressiveSave;
    }

    /**
     * <p>Sets whether to aggressively save the pace file whenever a modification has been made to it</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param aggressiveSave {@code true} if a pace object should attempt to save whenever a modification ping is sent
     *                       to it, {@code false} otherwise
     * @see #aggressiveSave
     *
     */
    public void setAggressiveSave(boolean aggressiveSave) {
        this.aggressiveSave = aggressiveSave;
        save();
    }

    /**
     * Returns whether the launcher should be opened maximized or in window mode
     *
     * @return {@code true} if the launcher should take up the whole screen, {@code false} otherwise
     * @see #launcherMaximized
     *
     */
    public boolean isLauncherMaximized() {
        return launcherMaximized;
    }

    /**
     * <p>Sets whether the launcher should be opened maximized or in window mode</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param launcherMaximized {@code true} if the launcher should be opened maximized, {@code false} otherwise
     * @see #launcherMaximized
     *
     */
    public void setLauncherMaximized(boolean launcherMaximized) {
        this.launcherMaximized = launcherMaximized;
        save();
    }

    /**
     * Checks if the application should be maximized next time it is opened
     *
     * @return {@code true} if the application should take up the whole screen, {@code false} if it should be in
     * windowed mode
     * @see #appMaximized
     *
     */
    public boolean isAppMaximized() {
        return appMaximized;
    }

    /**
     * <p>Sets whether the application should be maximized next time it is opened</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param appMaximized {@code true} if the application should take up the whole screen, {@code false} if it
     *                     should be in windowed mode
     * @see #appMaximized
     *
     */
    public void setAppMaximized(boolean appMaximized) {
        this.appMaximized = appMaximized;
        save();
    }

    /**
     * Checks if the application should warn the user before deleting anything
     *
     * @return {@code true} if the user should be prompted to verify they wish to delete an object, {@code false} if
     * the user is taking the risk and the object should be deleted immediately without verification
     * @see #warnOnDelete
     *
     */
    public boolean warnOnDelete() {
        return warnOnDelete;
    }

    /**
     * <p>Sets whether the user should be warned before deleting anything</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param warnOnDelete {@code true} if the user should be prompted to verify they wish to delete an object, {@code false} if
     *                     the user is taking the risk and the object should be deleted immediately without verification
     * @see #warnOnDelete
     */
    public void setWarnOnDelete(boolean warnOnDelete) {
        this.warnOnDelete = warnOnDelete;
        save();
    }

    /**
     * Checks whether the calculations for division average should exclude the slowest and the fastest team
     *
     * @return {@code true} if the slowest and fastest should be excluded, {@code false} if they should be included
     * @see #excludeOutliers
     *
     */
    public boolean excludeOutliers() {
        return excludeOutliers;
    }

    /**
     * <p>Sets whether the calculations for division average should exclude the slowest and fastest team</p>
     * <p>Will save settings to the settings file after the modification</p>
     *
     * @param excludeOutliers {@code true} if the slowest and fastest should be excluded, {@code false} if they should
     *                        be included
     * @see #excludeOutliers
     *
     */
    public void setExcludeOutliers(boolean excludeOutliers) {
        this.excludeOutliers = excludeOutliers;
        save();
    }

    /**
     * Checks whether the average should be used as the goal time if there is no goal time specified
     * @return {@code true} if the average time should be used when no goal time is specified, {@code false} otherwise
     *
     * @see #useAverageAsGoalTime
     */
    public boolean useAverageAsGoalTime() {
        return useAverageAsGoalTime;
    }

    /**
     * Sets whether the average should be used as the goal time if there is no goal time specified
     * <p>Will save settings to the settings file after the modification</p>
     * @param useAverageAsGoalTime {@code true} if the average time should be used when no goal time is specified,
     * {@code false} otherwise
     *
     * @see #useAverageAsGoalTime
     */
    public void setUseAverageAsGoalTime(boolean useAverageAsGoalTime) {
        this.useAverageAsGoalTime = useAverageAsGoalTime;
        save();
    }

    /**
     * Checks whether multiple team editors are allowed to be opened at once
     * @return {@code true} if multiple team editors are allowed to be open at the same time, {@code false} if only
     * one should be allowed to be opened
     *
     * @see #multipleTeamsEditing
     */
    public boolean isMultipleTeamsEditing() {
        return multipleTeamsEditing;
    }

    /**
     * Sets whether multiple team editors are allowed to be opened at once.
     * <p>Will save settings to the settings file after the modification</p>
     * @param multipleTeamsEditing if multiple team editors are allowed to be open at the same time, {@code false} if
     *                             only one should be allowed to be opened
     *
     * @see #multipleTeamsEditing
     */
    public void setMultipleTeamsEditing(Boolean multipleTeamsEditing) {
        this.multipleTeamsEditing = multipleTeamsEditing;
    }


}
