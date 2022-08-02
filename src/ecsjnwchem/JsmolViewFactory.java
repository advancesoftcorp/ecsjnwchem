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

import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

public final class JsmolViewFactory {

    private static final double ZOOM = 1.00;

    private static final String JSMOL_ROOT = "jsmol";
    private static final String JSMOL_HTML = "jsmol_ecsjnwchem.html";

    private static JsmolViewFactory instance = null;

    public static JsmolViewFactory getInstance() {
        if (instance == null) {
            instance = new JsmolViewFactory();
        }

        return instance;
    }

    private JsmolViewFactory() {
        // NOP
    }

    public JsmolView getJsmolView() {
        WebView webView = new WebView();
        webView.setContextMenuEnabled(false);
        webView.setZoom(ZOOM);

        JsmolView jsmolView = new JsmolView(webView);

        WebEngine webEngine = this.createWebEngine(webView, jsmolView);
        if (webEngine != null) {
            jsmolView.setWebEngine(webEngine);
        }

        return jsmolView;
    }

    private WebEngine createWebEngine(WebView webView, JsmolView jsmolView) {
        WebEngine webEngine = webView == null ? null : webView.getEngine();
        if (webEngine == null) {
            return null;
        }

        webEngine.setJavaScriptEnabled(true);

        if (jsmolView != null) {
            Object obj = webEngine.executeScript("window");
            if (obj != null && obj instanceof JSObject) {
                ((JSObject) obj).setMember("ecsjnwchem", jsmolView);
            }
        }

        File file = new File(JSMOL_ROOT, JSMOL_HTML);

        try {
            if (!file.isFile()) {
                return null;
            }
        } catch (Exception e) {
            return null;
        }

        String path = file.getAbsolutePath();
        if (path == null || path.isEmpty()) {
            return null;
        }

        if (path.charAt(0) == '/') {
            path = path.substring(1);
            if (path == null || path.isEmpty()) {
                return null;
            }
        }

        webEngine.load("file:///" + path);

        return webEngine;
    }
}
