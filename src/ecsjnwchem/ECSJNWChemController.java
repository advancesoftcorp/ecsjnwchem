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

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Queue;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Dragboard;
import javafx.scene.input.KeyCode;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public final class ECSJNWChemController implements Initializable {

    private static final double GRAPHIC_SIZE = 41.0;
    private static final String GRAPHIC_STYLE = "-fx-fill: derive(-fx-dark-text-color, 50.0%)";

    private static final String XYZ_FILE = "nwchem.xyz";
    private static final String OUT_FILE = "nwchem.out";

    private static final int OUT_BUFFER = 2048;

    private static final long SLEEP_OUT_BUFFER = 200L;

    private static final long SLEEP_AFTER_RUN = 500L;

    @FXML
    private Hyperlink asHyperlink;

    @FXML
    private ImageView imageView;

    @FXML
    private BorderPane basePane;

    @FXML
    private TabPane tabPane;

    @FXML
    private StackPane inpPane;

    @FXML
    private Label inpLabel;

    @FXML
    private TextArea inpArea;

    @FXML
    private TextArea outArea;

    @FXML
    private Button saveButton;

    @FXML
    private Button runButton;

    @FXML
    private Button stopButton;

    @FXML
    private Button reloadOutButton;

    @FXML
    private TextField grepField;

    @FXML
    private TextArea grepArea;

    @FXML
    private Button grepButton;

    @FXML
    private Button reloadAtomButton;

    @FXML
    private BorderPane jsmolPane;

    private Stage stage;

    private File inpFile;

    private JsmolView jsmolView;

    private NWChemRunner nwchemRunner;

    private boolean runningNWChem;

    private boolean[] loggingNWChem;

    private boolean[] grepingNWChem;

    public ECSJNWChemController() {

        this.stage = null;

        this.inpFile = null;

        this.jsmolView = null;

        this.nwchemRunner = null;

        this.runningNWChem = false;

        this.loggingNWChem = new boolean[] { false };

        this.grepingNWChem = new boolean[] { false };
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.setupImageView();
        this.setupAsHyperlink();
        this.setupBasePane();
        this.setupInpPane();
        this.setupInpArea();
        this.setupOutArea();
        this.setupSaveButton();
        this.setupRunButton();
        this.setupStopButton();
        this.setupReloadOutButton();
        this.setupGrepField();
        this.setupGrepButton();
        this.setupReloadAtomButton();
        this.setupJsmolPane();
    }

    private void setupImageView() {
        if (this.imageView == null) {
            return;
        }

        URL url = ECSJNWChem.class.getResource("ECSJNWChemTitle.png");
        String imageName = url == null ? null : url.toExternalForm();
        if (imageName == null || imageName.isEmpty()) {
            return;
        }

        Image image = new Image(imageName);

        double h1 = this.imageView.getFitHeight();
        double w2 = image.getWidth();
        double h2 = image.getHeight();

        double w3 = w2 * (h1 / h2);
        double h3 = h1;

        this.imageView.setFitWidth(w3);
        this.imageView.setFitHeight(h3);
        this.imageView.setImage(image);
    }

    private void setupAsHyperlink() {
        if (this.asHyperlink == null) {
            return;
        }

        this.asHyperlink.setOnAction(event -> {
            try {
                if (!Desktop.isDesktopSupported()) {
                    return;
                }

                Desktop desktop = Desktop.getDesktop();
                if (!desktop.isSupported(Desktop.Action.BROWSE)) {
                    return;
                }

                URI uri = new URI("https://www.advancesoft.jp");
                desktop.browse(uri);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    private void setupBasePane() {
        if (this.basePane == null) {
            return;
        }

        this.basePane.setOnKeyPressed(event -> {
            if (event != null && event.isShortcutDown()) {
                if (event.getCode() == KeyCode.Q || event.getCode() == KeyCode.W) {
                    if (this.stage != null) {
                        this.stage.close();
                    }
                }
            }
        });
    }

    private void setupInpPane() {
        if (this.inpPane == null) {
            return;
        }

        this.inpPane.setOnDragOver(event -> {
            if (event == null) {
                return;
            }

            Object source = event.getGestureSource();
            if (source != null && source instanceof Node) {
                return;
            }

            Dragboard board = event.getDragboard();
            if (board == null) {
                return;
            }

            if (board.hasFiles()) {
                event.acceptTransferModes(TransferMode.COPY_OR_MOVE);
            }
        });

        this.inpPane.setOnDragDropped(event -> {
            if (event == null) {
                return;
            }

            Object source = event.getGestureSource();
            if (source != null && source instanceof Node) {
                return;
            }

            Dragboard board = event.getDragboard();
            if (board == null) {
                return;
            }

            if (board.hasFiles()) {
                List<File> files = board.getFiles();
                if (files != null && !files.isEmpty()) {
                    this.inpFile = files.get(0);
                    this.updateInpArea();
                }

                event.setDropCompleted(true);
            }
        });
    }

    private void setupInpArea() {
        if (this.inpArea == null) {
            return;
        }

        this.inpArea.setPromptText(
                System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + "            Drag & Drop Input File of NWChem.");

        this.inpArea.setOnKeyPressed(event -> {
            if (event == null) {
                return;
            }

            if (event.getCode() == KeyCode.F5) {
                this.pressButton(this.reloadAtomButton);

            } else if (event.isShortcutDown() && event.getCode() == KeyCode.S) {
                this.saveInpArea();
            }
        });

        this.updateInpArea();
    }

    private void updateInpArea() {
        if (this.inpArea == null) {
            return;
        }

        this.inpArea.setText("");
        this.inpArea.setEditable(false);

        try {
            if (this.inpFile == null || !this.inpFile.isFile()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        boolean hasInp = false;

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(this.inpFile));

            String line = null;
            while ((line = reader.readLine()) != null) {
                hasInp = true;
                this.inpArea.appendText(line);
                this.inpArea.appendText(System.lineSeparator());
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (hasInp) {
            this.inpArea.setEditable(true);

            if (this.tabPane != null) {
                SingleSelectionModel<Tab> selectionModel = this.tabPane.getSelectionModel();
                if (selectionModel != null) {
                    selectionModel.select(0);
                }
            }

            if (this.inpLabel != null) {
                this.inpLabel.setText("file:///" + this.inpFile.getAbsolutePath());
            }

            this.updateJsmolPane();
        }
    }

    private void saveInpArea() {
        if (this.inpArea == null) {
            return;
        }

        String text = this.inpArea.getText();
        if (text == null || text.trim().isEmpty()) {
            return;
        }

        try {
            if (this.inpFile == null || !this.inpFile.isFile()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        Alert alert = new Alert(AlertType.CONFIRMATION);
        if (this.stage != null) {
            alert.initOwner(this.stage);
        }

        alert.getButtonTypes().clear();
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.setHeaderText("入力ファイルを保存しますか ?");

        Optional<ButtonType> optButtonType = alert.showAndWait();
        if (optButtonType == null || !optButtonType.isPresent()) {
            return;
        }
        if (optButtonType.get() != ButtonType.YES) {
            return;
        }

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(this.inpFile)));
            writer.print(text);

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }

    private void setupOutArea() {
        if (this.outArea == null) {
            return;
        }

        this.outArea.setPromptText(
                System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + System.lineSeparator()
                        + "              Please Reload Output File of NWChem.");

        this.outArea.setOnKeyPressed(event -> {
            if (event != null && event.getCode() == KeyCode.F5) {
                this.pressButton(this.reloadOutButton);
            }
        });

        this.updateOutArea();
    }

    private void updateOutArea() {
        if (this.outArea == null) {
            return;
        }

        this.updateTextArea(this.outArea, null, this.loggingNWChem);
    }

    private void setupSaveButton() {
        if (this.saveButton == null) {
            return;
        }

        this.saveButton.setText("");
        this.saveButton.setTooltip(new Tooltip("save input file"));
        this.saveButton.setGraphic(SVGData.getSaveGraphic(GRAPHIC_SIZE, GRAPHIC_STYLE, null));

        this.saveButton.setOnAction(event -> {
            this.saveInpArea();
        });
    }

    private void setupRunButton() {
        if (this.runButton == null) {
            return;
        }

        this.runButton.setText("");
        this.runButton.setTooltip(new Tooltip("run NWChem"));
        this.runButton.setGraphic(SVGData.getRunGraphic(GRAPHIC_SIZE, GRAPHIC_STYLE, null));

        this.runButton.setOnAction(event -> {
            try {
                if (this.inpFile == null || !this.inpFile.isFile()) {
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            synchronized (this) {
                if (this.runningNWChem) {
                    return;
                }

                this.runningNWChem = true;
            }

            Label mpiLabel = new Label("並列数 :  ");
            TextField mpiField = new TextField();
            BorderPane mpiPane = new BorderPane();
            mpiPane.setLeft(new StackPane(mpiLabel));
            mpiPane.setCenter(mpiField);

            Alert alert = new Alert(AlertType.CONFIRMATION);
            if (this.stage != null) {
                alert.initOwner(this.stage);
            }

            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.setHeaderText("NWChemの計算を実行しますか ?");
            alert.getDialogPane().setContent(mpiPane);

            Optional<ButtonType> optButtonType = alert.showAndWait();
            if (optButtonType == null || !optButtonType.isPresent()) {
                synchronized (this) {
                    this.runningNWChem = false;
                }
                return;
            }

            if (optButtonType.get() != ButtonType.YES) {
                synchronized (this) {
                    this.runningNWChem = false;
                }
                return;
            }

            String mpiText = mpiField.getText();
            mpiText = mpiText == null ? null : mpiText.trim();

            int mpiValue = 1;
            if (mpiText != null && !mpiText.isEmpty()) {
                try {
                    mpiValue = Integer.parseInt(mpiText);
                } catch (NumberFormatException e) {
                    mpiValue = 1;
                }
            }

            int numParallel = Math.max(1, mpiValue);

            Thread thread = new Thread(() -> {
                NWChemRunner nwchemRunner = new NWChemRunner(this.inpFile, OUT_FILE);
                synchronized (this) {
                    this.nwchemRunner = nwchemRunner;
                }

                nwchemRunner.setNumParallel(numParallel);
                nwchemRunner.runNWChem();

                synchronized (this) {
                    this.nwchemRunner = null;
                    this.runningNWChem = false;
                }
            });

            thread.start();

            try {
                Thread.sleep(SLEEP_AFTER_RUN);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {
                if (this.tabPane != null) {
                    SingleSelectionModel<Tab> selectionModel = this.tabPane.getSelectionModel();
                    if (selectionModel != null) {
                        selectionModel.select(1);
                    }
                }

                this.updateOutArea();
            });
        });
    }

    private void setupStopButton() {
        if (this.stopButton == null) {
            return;
        }

        this.stopButton.setText("");
        this.stopButton.setTooltip(new Tooltip("stop NWChem"));
        this.stopButton.setGraphic(SVGData.getStopGraphic(GRAPHIC_SIZE, GRAPHIC_STYLE, null));

        this.stopButton.setOnAction(event -> {
            NWChemRunner nwchemRunner = null;
            synchronized (this) {
                nwchemRunner = this.nwchemRunner;
            }

            if (nwchemRunner == null) {
                return;
            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            if (this.stage != null) {
                alert.initOwner(this.stage);
            }

            alert.getButtonTypes().clear();
            alert.getButtonTypes().add(ButtonType.YES);
            alert.getButtonTypes().add(ButtonType.NO);
            alert.setHeaderText("実行中のNWChemの計算を停止させますか ?");

            Optional<ButtonType> optButtonType = alert.showAndWait();
            if (optButtonType == null || !optButtonType.isPresent()) {
                return;
            }

            if (optButtonType.get() != ButtonType.YES) {
                return;
            }

            nwchemRunner.stop();
        });
    }

    private void setupReloadOutButton() {
        if (this.reloadOutButton == null) {
            return;
        }

        this.reloadOutButton.setText("");
        this.reloadOutButton.setTooltip(new Tooltip("reload output file"));
        this.reloadOutButton.setGraphic(SVGData.getReloadGraphic(GRAPHIC_SIZE, GRAPHIC_STYLE, null));

        this.reloadOutButton.setOnAction(event -> {
            this.updateOutArea();
        });
    }

    private void setupGrepField() {
        if (this.grepField == null) {
            return;
        }

        this.grepField.setOnAction(event -> this.pressButton(this.grepButton));
    }

    private void setupGrepButton() {
        if (this.grepButton == null) {
            return;
        }

        this.grepButton.setOnAction(event -> this.updateGrepArea());
    }

    private void updateGrepArea() {
        if (this.grepArea == null) {
            return;
        }

        String text = null;
        if (this.grepField != null) {
            text = this.grepField.getText();
        }

        if (text != null) {
            text = text.trim();
        }

        if (text != null && !text.isEmpty()) {
            this.updateTextArea(this.grepArea, text, this.grepingNWChem);

        } else {
            this.grepArea.setText("");
        }
    }

    private void updateTextArea(TextArea textArea, String grepText, boolean[] doingFlag) {
        if (textArea == null) {
            return;
        }

        synchronized (this) {
            if (doingFlag[0]) {
                return;
            }

            doingFlag[0] = true;
        }

        textArea.setText("");

        try {
            if (this.inpFile == null || !this.inpFile.isFile()) {
                synchronized (this) {
                    doingFlag[0] = false;
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            synchronized (this) {
                doingFlag[0] = false;
            }
            return;
        }

        File directory = this.inpFile.getParentFile();
        File outFile = new File(directory, OUT_FILE);

        try {
            if (!outFile.isFile()) {
                synchronized (this) {
                    doingFlag[0] = false;
                }
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            synchronized (this) {
                doingFlag[0] = false;
            }
            return;
        }

        Thread thread = new Thread(() -> {
            BufferedReader reader = null;
            try {
                reader = new BufferedReader(new FileReader(outFile));

                String line = null;
                Queue<String> lines = new LinkedList<>();

                while ((line = reader.readLine()) != null) {
                    if (grepText == null || grepText.isEmpty() || line.contains(grepText)) {
                        lines.offer(line);
                    }

                    if (lines.size() >= OUT_BUFFER) {
                        StringBuilder strBuilder = new StringBuilder();
                        while (!lines.isEmpty()) {
                            strBuilder.append(lines.poll());
                            strBuilder.append(System.lineSeparator());
                        }

                        Platform.runLater(() -> {
                            textArea.appendText(strBuilder.toString());
                        });

                        try {
                            Thread.sleep(SLEEP_OUT_BUFFER);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }

                if (!lines.isEmpty()) {
                    StringBuilder strBuilder = new StringBuilder();
                    while (!lines.isEmpty()) {
                        strBuilder.append(lines.poll());
                        strBuilder.append(System.lineSeparator());
                    }

                    Platform.runLater(() -> {
                        textArea.appendText(strBuilder.toString());
                    });

                    try {
                        Thread.sleep(SLEEP_OUT_BUFFER);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            synchronized (this) {
                doingFlag[0] = false;
            }
        });

        thread.start();
    }

    private void setupReloadAtomButton() {
        if (this.reloadAtomButton == null) {
            return;
        }

        this.reloadAtomButton.setText("");
        this.reloadAtomButton.setTooltip(new Tooltip("reload atomic model"));
        this.reloadAtomButton.setGraphic(SVGData.getReloadGraphic(GRAPHIC_SIZE, GRAPHIC_STYLE, null));

        this.reloadAtomButton.setOnAction(event -> {
            this.updateJsmolPane();
        });
    }

    private void setupJsmolPane() {
        if (this.jsmolPane == null) {
            return;
        }

        this.jsmolView = JsmolViewFactory.getInstance().getJsmolView();

        if (this.jsmolView != null) {
            this.jsmolPane.setCenter(this.jsmolView);
        }

        this.jsmolPane.setOnKeyPressed(event -> {
            if (event != null && event.getCode() == KeyCode.F5) {
                this.pressButton(this.reloadAtomButton);
            }
        });
    }

    private void updateJsmolPane() {
        if (this.jsmolPane == null) {
            return;
        }

        try {
            if (this.inpFile == null || !this.inpFile.isFile()) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        // read geometry
        String text = this.inpArea == null ? null : this.inpArea.getText();
        text = text == null ? null : text.trim();

        if (text == null || text.isEmpty()) {
            return;
        }

        String[] lines = text.split("[\\n\\r]+");
        if (lines == null || lines.length < 1) {
            return;
        }

        int index = 0;
        for (int i = 0; i < lines.length; i++) {
            String line = lines[i];
            line = line == null ? null : line.trim();
            if (line != null && "geometry".startsWith(line.toLowerCase())) {
                index = i + 1;
                break;
            }
        }

        List<String> atoms = new ArrayList<>();
        for (int i = index; i < lines.length; i++) {
            String line = lines[i];
            line = line == null ? null : line.trim();
            if (line == null || "end".equalsIgnoreCase(line)) {
                break;
            }

            atoms.add(line);
        }

        if (atoms.isEmpty()) {
            return;
        }

        // write geometry
        File directory = this.inpFile.getParentFile();
        File xyzFile = new File(directory, XYZ_FILE);

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(new BufferedWriter(new FileWriter(xyzFile)));
            writer.println(atoms.size());
            writer.println();

            for (String atom : atoms) {
                writer.println(atom);
            }

        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        // show by Jsmol
        if (this.jsmolView != null) {
            this.jsmolView.executeScript("load \"" + xyzFile.getAbsolutePath() + "\"");
        }
    }

    private void pressButton(Button button) {
        if (button == null) {
            return;
        }

        EventHandler<ActionEvent> handler = button.getOnAction();
        if (handler != null) {
            handler.handle(new ActionEvent());
        }
    }
}