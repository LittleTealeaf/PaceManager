package data.api;

import data.interfaces.PaceComponent;
import data.interfaces.Identifiable;
import java.util.Collection;

/**
 * Outlines the requirements that a team object must implement. This includes getters and setters for the name, division, start and end time, as well as riders
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
public interface ITeam extends Identifiable, PaceComponent {

    String KEY_NAME = ITeam.class.getName() + ".name";
    String KEY_DIVISION_UUID = ITeam.class.getName() + ".divisionUUID";
    String KEY_START_TIME = ITeam.class.getName() + ".timeStart";
    String KEY_END_TIME = ITeam.class.getName() + ".timeEnd";
    String KEY_INCLUDED = ITeam.class.getName() + ".included";

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

    enum Status {
        NOT_STARTED,
        IN_PROGRESS,
        COMPLETED;
    }
}
