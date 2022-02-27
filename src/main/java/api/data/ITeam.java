package api.data;

import java.util.UUID;

/**
 * @author Thomas Kwashnak
 * @since 2.0.0
 * @version 2.0.0
 */
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

    IClock getElapsedTime();
    TeamStatus getStatus();
}
