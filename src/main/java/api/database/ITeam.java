package api.database;

import api.util.PaceComponent;

import java.util.List;

public interface ITeam extends PaceComponent {
    String getName();
    List<IRider> getRiders();
    IClock getStartTime();
    IClock getEndTime();
    IClock getElapsedTime();
    IDivision getDivision();
    String getNotes();
    Boolean isExcluded();

    void setNotes(String notes);
    void setName(String name);
    void setStartTime(IClock startTime);
    void setEndTime(IClock endTime);
    void setDivision(IDivision division);
    void setExcluded(Boolean excluded);

    default boolean addRider(IRider rider) {
        return getRiders().add(rider);
    }
    default boolean removeRider(IRider rider) {
        return getRiders().remove(rider);
    }
}
