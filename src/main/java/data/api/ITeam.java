package data.api;

import data.interfaces.Identifiable;
import data.interfaces.PaceComponent;

import java.util.Collection;

public interface ITeam extends Identifiable, PaceComponent {

    String getName();

    void setName(String name);

    IDivision getDivision();

    void setDivision(IDivision division);

    void setDivision(java.util.UUID divisionUUID);

    IClock getStartTime();

    void setStartTime(IClock startTime);

    IClock getEndTime();

    void setEndTime(IClock endTime);

    Status getStatus();

    IClock getElapsedTime();

    Collection<IRider> getRiders();

    boolean addRider(IRider rider);

    boolean removeRider(IRider rider);

    boolean isIncluded();

    void setIncluded(boolean included);

    String getNotes();

    void setNotes(String notes);

    enum Status {

        NOT_STARTED,

        IN_PROGRESS,

        COMPLETED
    }
}
