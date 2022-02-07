package api.database;

import api.util.PaceComponent;

import java.util.List;

public interface ITeam extends PaceComponent {

    String getName();

    void setName(String name);

    IClock getStartTime();

    void setStartTime(IClock startTime);

    IClock getEndTime();

    void setEndTime(IClock endTime);

    IClock getElapsedTime();

    IDivision getDivision();

    void setDivision(IDivision division);

    String getNotes();

    void setNotes(String notes);

    Boolean isExcluded();

    void setExcluded(Boolean excluded);

    default boolean addRider(IRider rider) {
        return getRiders().add(rider);
    }

    List<IRider> getRiders();

    default boolean removeRider(IRider rider) {
        return getRiders().remove(rider);
    }
}
