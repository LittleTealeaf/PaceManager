package api.database;

import api.interfaces.PaceComponent;

import java.util.List;
import java.util.UUID;

/**
 * @author Thomas Kwashnak
 */
public interface ITeam extends PaceComponent {

    String getName();
    void setName(String name);
    List<IRider> getRiders();
    void clearRiders();
    void addRider(IRider rider);
    void removeRider(IRider rider);
    String getNotes();
    void setNotes(String notes);

//    time instances
    ITimeInstance getTimeStart();
    void setTimeStart(ITimeInstance timeStart);
    ITimeInstance getTimeFinish();
    void setTimeFinish(ITimeInstance timeFinish);
    ITimeInstance getTimeElapsed();

    //Division
    UUID getDivisionUUID();
    IDivision getDivision();
    void setDivision(IDivision division);
}
