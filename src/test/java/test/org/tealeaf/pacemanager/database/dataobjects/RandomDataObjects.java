package test.org.tealeaf.pacemanager.database.dataobjects;

import org.tealeaf.pacemanager.database.dataobjects.Division;
import test.org.tealeaf.pacemanager.RandomValues;

public interface RandomDataObjects extends RandomValues {
    default Division randomDivision() {
        return new Division() {{
            setName(randomName());
        }};
    }
}
