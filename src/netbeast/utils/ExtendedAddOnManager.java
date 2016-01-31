/*
 * Copyright (c) 2016 Tim Vaughan
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see http://www.gnu.org/licenses/.
 */

package netbeast.utils;

import beast.util.AddOnManager;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Tim Vaughan <tgvaughan@gmail.com>
 */
public class ExtendedAddOnManager extends AddOnManager {

    /**
     * return list of installed package names
     */
    public static List<String> getInstalledPackageNames() {

        List<String> dirs = new ArrayList<>();
        // check if there is the BEAST environment variable is set
        if (System.getProperty("BEAST_ADDON_PATH") != null) {
            String BEAST = System.getProperty("BEAST_ADDON_PATH");
            for (String dirName : BEAST.split(":")) {
                dirs.add(dirName);
            }
        }
        if (System.getenv("BEAST_ADDON_PATH") != null) {
            String BEAST = System.getenv("BEAST_ADDON_PATH");
            for (String dirName : BEAST.split(":")) {
                dirs.add(dirName);
            }
        }

        // add user package directory
        dirs.add(getPackageUserDir());

        // add application package directory
        dirs.add(getPackageSystemDir());

        // add BEAST installation directory
        if (getBEASTInstallDir() != null)
            dirs.add(getBEASTInstallDir());

        // pick up directories in class path, useful when running in an IDE
        String strClassPath = System.getProperty("java.class.path");
        String [] paths = strClassPath.split(":");
        for (String path : paths) {
            if (!path.endsWith(".jar")) {
                path = path.replaceAll("\\\\","/");
                if (path.contains("/")) {
                    path = path.substring(0, path.lastIndexOf("/"));
                    // deal with the way Mac's appbundler sets up paths
                    path = path.replaceAll("/[^/]*/Contents/Java", "");
                    // exclude Intellij build path out/production
                    if (!dirs.contains(path) && !path.contains("production")) {
                        dirs.add(path);
                    }
                }
            }
        }

        List<String> packageNames = new ArrayList<>();
        for (String dirName : dirs) {
            File dir = new File(dirName);
            if (dir.isDirectory()) {
                File[] files = dir.listFiles();
                if (files == null)
                    continue;

                for (File file : files) {
                    if (file.isDirectory()) {
                        File versionFile = new File(file, "version.xml");
                        if (versionFile.exists())
                            packageNames.add(file.getName());
                    }
                }
            }
        }


        return packageNames;
    }
}
