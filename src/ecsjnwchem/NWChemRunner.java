/*
 * Copyright (C) 2022 AdvanceSoft Corporation
 *
 * Licensed under the Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain a copy of the
 * License at https://opensource.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package ecsjnwchem;

import java.io.File;
import java.util.Map;

public final class NWChemRunner {

    private File inpFile;

    private String outName;

    public NWChemRunner(File inpFile, String outName) {
        if (inpFile == null) {
            throw new IllegalArgumentException("inpFile is null.");
        }

        if (outName == null || outName.isEmpty()) {
            throw new IllegalArgumentException("outName is empty.");
        }

        this.inpFile = inpFile;

        this.outName = outName;
    }

    public void runNWChem() {
        File execFile = null;
        String execPath = null;

        if (this.isWindows()) {
            execFile = new File("nwchem");
            execFile = new File(execFile, "bin");
            execFile = new File(execFile, "nwchem.exe");
            execPath = execFile.getPath();

        } else {
            execPath = "nwchem";
        }

        if (execPath == null || execPath.isEmpty()) {
            return;
        }

        File workDir = this.inpFile.getParentFile();
        String inpName = this.inpFile.getName();

        try {
            ProcessBuilder builder = new ProcessBuilder(execPath, inpName);
            builder.directory(workDir);
            builder.redirectErrorStream(true);
            builder.redirectOutput(new File(workDir, this.outName));

            Map<String, String> envMap = builder.environment();
            if (envMap != null) {
                if (this.isWindows()) {
                    String path = envMap.get("PATH");
                    path = path == null ? null : path.trim();

                    if (path == null || path.isEmpty()) {
                        path = execFile.getAbsolutePath();
                    } else {
                        path = execFile.getAbsolutePath() + File.pathSeparator + path;
                    }

                    envMap.put("PATH", path);
                }

                String basisLib = envMap.get("NWCHEM_BASIS_LIBRARY");
                basisLib = basisLib == null ? null : basisLib.trim();

                if (basisLib == null || basisLib.isEmpty()) {
                    File libFile = new File("nwchem");
                    libFile = new File(libFile, "basis");
                    String libPath = libFile.getAbsolutePath();
                    if (!libPath.endsWith("/")) {
                        libPath += "/";
                    }

                    envMap.put("NWCHEM_BASIS_LIBRARY", libPath);
                }
            }

            Process process = builder.start();
            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean isWindows() {
        String osName = System.getProperty("os.name", null);
        if (osName == null || osName.isEmpty()) {
            return false;
        }

        osName = osName.toLowerCase().trim();
        return osName.startsWith("windows");
    }
}
