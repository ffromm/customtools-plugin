/*
 * Copyright 2012, CloudBees Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cloudbees.jenkins.plugins.customtools;

import hudson.FilePath;
import hudson.Launcher;
import hudson.Proc;
import hudson.remoting.Channel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * A launcher which delegates to a provided inner launcher.
 * Allows subclasses to only implement methods they want to 
 * override.
 * 
 * @author rcampbell
 *
 */
public class DecoratedLauncher extends Launcher {
    private Launcher inner = null;

    public DecoratedLauncher(Launcher inner) {
        super(inner);
        this.inner = inner;
    }

    @Override
    public Proc launch(ProcStarter starter) throws IOException {
        return inner.launch(starter);
    }

    @Override
    public Channel launchChannel(String[] cmd, OutputStream out,
            FilePath workDir, Map<String, String> envVars) throws IOException,
            InterruptedException {
        return inner.launchChannel(cmd, out, workDir, envVars);
    }

    @Override
    public void kill(Map<String, String> modelEnvVars) throws IOException,
            InterruptedException {
        inner.kill(modelEnvVars);

    }

}
