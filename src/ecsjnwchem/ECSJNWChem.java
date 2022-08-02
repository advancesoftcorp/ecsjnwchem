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

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ECSJNWChem extends Application {

    @Override
    public void start(Stage stage) {
        try {
            ECSJNWChemController controller = new ECSJNWChemController();

            FXMLLoader fxmlLoader = new FXMLLoader(this.getClass().getResource("ECSJNWChem.fxml"));
            fxmlLoader.setController(controller);
            Parent root = fxmlLoader.load();

            setUserAgentStylesheet(STYLESHEET_MODENA);
            Scene scene = new Scene(root);
            stage.setTitle("電気化学会関東支部／計算化学セミナー[実践編], GUI for NWChem");
            stage.setFullScreen(false);
            stage.setResizable(true);
            stage.setScene(scene);

            URL url = ECSJNWChem.class.getResource("ECSJNWChem.png");
            String iconName = url == null ? null : url.toExternalForm();
            if (iconName != null && (!iconName.isEmpty())) {
                Image icon = new Image(iconName);
                stage.getIcons().add(icon);
            }

            controller.setStage(stage);
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
