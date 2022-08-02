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

import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import netscape.javascript.JSObject;

public final class JsmolView extends BorderPane {

    private WebEngine webEngine;

    public JsmolView(Node viewNode) {
        if (viewNode == null) {
            throw new IllegalArgumentException("viewNode is null.");
        }

        super.setCenter(viewNode);

        this.webEngine = null;
    }

    public void setWebEngine(WebEngine webEngine) {
        this.webEngine = webEngine;
    }

    public void reload() {
        if (this.webEngine != null) {
            this.webEngine.reload();
        }
    }

    public void executeScript(String data) {
        if (this.webEngine == null) {
            return;
        }

        if (data == null || data.isEmpty()) {
            return;
        }

        try {
            Object obj = this.webEngine.executeScript("window");
            if (obj != null && obj instanceof JSObject) {
                JSObject window = ((JSObject) obj);
                window.call("executeJsmol", data);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
