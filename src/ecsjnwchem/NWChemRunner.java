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

    private int numParallel;

    private Process process;

    public NWChemRunner(File inpFile, String outName) {
        if (inpFile == null) {
            throw new IllegalArgumentException("inpFile is null.");
        }

        if (outName == null || outName.isEmpty()) {
            throw new IllegalArgumentException("outName is empty.");
        }

        this.inpFile = inpFile;

        this.outName = outName;

        this.numParallel = 1;

        this.process = null;
    }

    public synchronized void setNumParallel(int numParallel) {
        this.numParallel = numParallel;
    }

    public void runNWChem() {
        synchronized (this) {
            if (this.process != null) {
                return;
            }
        }

        File execDir = null;
        String execPath = null;
        String mpiPath = null;

        if (this.isWindows()) {
            execDir = new File("nwchem");
            execDir = new File(execDir, "bin");
            execPath = new File(execDir, "nwchem.exe").getAbsolutePath();
            mpiPath = new File(execDir, "mpiexec.exe").getAbsolutePath();

        } else {
            execDir = null;
            execPath = "nwchem";
            mpiPath = "mpirun";
        }

        if (execPath == null || execPath.isEmpty()) {
            return;
        }

        if (mpiPath == null || mpiPath.isEmpty()) {
            return;
        }

        File workDir = this.inpFile.getParentFile();
        String inpName = this.inpFile.getName();

        try {
            ProcessBuilder builder = null;
            if (this.numParallel > 1) {
                builder = new ProcessBuilder(
                        mpiPath, "-localonly", "-n", Integer.toString(this.numParallel), execPath, inpName);
            } else {
                builder = new ProcessBuilder(execPath, inpName);
            }

            builder.directory(workDir);
            builder.redirectErrorStream(true);
            builder.redirectOutput(new File(workDir, this.outName));

            Map<String, String> envMap = builder.environment();
            if (envMap != null) {
                if (this.isWindows()) {
                    String path = envMap.get("PATH");
                    path = path == null ? null : path.trim();

                    if (path == null || path.isEmpty()) {
                        path = execDir.getAbsolutePath();
                    } else {
                        path = execDir.getAbsolutePath() + File.pathSeparator + path;
                    }

                    envMap.put("PATH", path);
                    envMap.put("Path", path);
                }

                envMap.put("OMP_NUM_THREADS", "1");

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

            synchronized (this) {
                this.process = process;
            }

            process.waitFor();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            synchronized (this) {
                this.process = null;
            }
        }
    }

    public void stop() {
        Process process = null;
        synchronized (this) {
            process = this.process;
        }

        if (process == null) {
            return;
        }

        try {
            process.getInputStream().close();
            process.getOutputStream().close();
            process.getErrorStream().close();
        } catch (Exception e) {
        }

        process.destroy();
        process.destroyForcibly();
    }

    private boolean isWindows() {
        String osName = System.getProperty("os.name", null);
        if (osName == null || osName.isEmpty()) {
            return false;
        }

        osName = osName.toLowerCase().trim();
        return osName.startsWith("windows");
    }
}
