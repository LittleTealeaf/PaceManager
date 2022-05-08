package org.tealeaf.pacemanager.system;

import net.harawata.appdirs.AppDirs;
import net.harawata.appdirs.AppDirsFactory;
import org.tealeaf.pacemanager.Constants;

import java.io.File;

public class AppData {
    private static final AppDirs appdirs = AppDirsFactory.getInstance();

    public static File getConfigDir() {
        return new File(appdirs.getUserConfigDir(Constants.APP_NAME, "", Constants.COMPANY_NAME));
    }
}
