package interfaces;

public interface AutoSavable {

    default <T> T autoSave(T object) {
        autoSave();
        return object;
    }

    void autoSave();
}
