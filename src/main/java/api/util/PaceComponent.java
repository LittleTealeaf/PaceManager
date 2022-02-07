package api.util;

import api.database.IPace;

public interface PaceComponent {
    void setPace(IPace pace);
    IPace getPace();
}
