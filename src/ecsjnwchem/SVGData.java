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

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.shape.SVGPath;
import javafx.scene.transform.Scale;

public final class SVGData {

    private static final double RELOAD_WIDTH = 17.12;

    private static final double RELOAD_HEIGHT = 17.12;

    private static final String RELOAD_CONTENT = "M8.661,0.001c0.006,0,0.01,0,0.01,0c0.007,0,0.007,0,0.011,0c0.002,0,0.007,0,0.009,0"
            + "c0,0,0,0,0.004,0c0.019-0.002,0.027,0,0.039,0c2.213,0,4.367,0.876,5.955,2.42l1.758-1.776c0.081-0.084,0.209-0.11,0.314-0.065"
            + "c0.109,0.044,0.186,0.152,0.186,0.271l-0.294,6.066h-5.699c-0.003,0-0.011,0-0.016,0c-0.158,0-0.291-0.131-0.291-0.296"
            + "c0-0.106,0.059-0.201,0.146-0.252l1.73-1.751c-1.026-0.988-2.36-1.529-3.832-1.529c-2.993,0.017-5.433,2.47-5.433,5.51"
            + "c0.023,2.978,2.457,5.4,5.481,5.422c1.972-0.106,3.83-1.278,4.719-3.221l2.803,1.293l-0.019,0.039"
            + "c-1.92,3.713-4.946,5.277-8.192,4.944c-4.375-0.348-7.848-4.013-7.878-8.52C0.171,3.876,3.976,0.042,8.661,0.001z";

    private static final double RUN_WIDTH = 487.811;

    private static final double RUN_HEIGHT = 487.81;

    private static final String RUN_CONTENT = "M150.463,109.521h150.512c3.955,0,7.16-3.206,7.16-7.161c0-3.955-3.205-7.161-7.16-7.161H150.463"
            + "c-3.955,0-7.161,3.206-7.161,7.161C143.302,106.315,146.508,109.521,150.463,109.521z"
            + "M15.853,179.537h150.511c3.955,0,7.161-3.206,7.161-7.161s-3.206-7.16-7.161-7.16H15.853"
            + "c-3.955,0-7.161,3.205-7.161,7.16S11.898,179.537,15.853,179.537z"
            + "M56.258,253.214c0,3.955,3.206,7.162,7.161,7.162H213.93c3.955,0,7.161-3.207,7.161-7.162s-3.206-7.16-7.161-7.16H63.419"
            + "C59.464,246.054,56.258,249.259,56.258,253.214z"
            + "M142.396,336.44H7.161C3.206,336.44,0,339.645,0,343.6s3.206,7.161,7.161,7.161h135.235c3.955,0,7.161-3.206,7.161-7.161"
            + "S146.351,336.44,142.396,336.44z"
            + "M385.729,154.418c21.6,0,39.111-17.513,39.111-39.114s-17.512-39.113-39.111-39.113"
            + "c-21.605,0-39.119,17.513-39.119,39.113C346.609,136.905,364.123,154.418,385.729,154.418z"
            + "M450.066,143.155c-22.459,31.459-52.533,35.102-84.895,15.89c-2.203-1.306-11.977-6.691-14.141-7.977"
            + "c-52.061-30.906-104.061-18.786-138.934,30.05c-14.819,20.771,19.455,40.459,34.108,19.93"
            + "c18.018-25.232,40.929-32.533,65.986-24.541c-12.83,22.27-24.047,44.405-39.875,75.853"
            + "c-15.832,31.448-50.787,56.562-84.374,36.92c-24.235-14.165-46.09,20.651-21.928,34.772"
            + "c45.854,26.799,99.619,10.343,127.066-24.493c0.952,0.509,1.958,0.968,3.062,1.354c22.422,7.812,51.814,28.61,60.77,35.981"
            + "c8.953,7.371,24.336,44.921,33.471,63.788c11.082,22.893,46.871,6.219,35.748-16.771c-10.355-21.406-27.736-64.129-41.293-74.938"
            + "c-10.875-8.669-31.988-24.803-49.895-33.956c12.115-23.466,24.729-46.679,38.008-69.491"
            + "c42.328,12.969,82.561-2.308,111.215-42.446C498.996,142.312,464.73,122.624,450.066,143.155z";

    private static final double SAVE_WIDTH = 45.0;

    private static final double SAVE_HEIGHT = 45.0;

    private static final String SAVE_CONTENT = "M44.449,16.561c-0.475-0.591-1.191-0.936-1.949-0.936H40c0-1.381-1.119-2.5-2.5-2.5H35v-2.5c0-1.381-1.119-2.5-2.5-2.5"
            + "h-1.25v-5c0-0.69-0.561-1.25-1.25-1.25H5c-0.691,0-1.25,0.56-1.25,1.25v5H2.5c-1.381,0-2.5,1.119-2.5,2.5v30"
            + "c0,1.381,1.119,2.5,2.5,2.5h5h25h5c1.172,0,2.188-0.814,2.439-1.958l5-22.5C45.105,17.927,44.924,17.152,44.449,16.561z"
            + " M3.75,40.625H2.5v-30h1.25V40.625z M31.468,28.469l-8.75,8.749c-0.488,0.488-1.279,0.488-1.768,0l-8.75-8.75"
            + "c-0.357-0.356-0.465-0.895-0.271-1.362c0.193-0.467,0.648-0.771,1.154-0.771h3.125V15.626H12.5c-1.172,0-2.188,0.814-2.441,1.958"
            + "c0,0-5.057,22.861-5.057,23.042H5v-37.5h25v10c-1.381,0-2.5,1.119-2.5,2.5h-0.042v10.71h3.125c0.506,0,0.961,0.305,1.155,0.771"
            + "C31.934,27.574,31.825,28.111,31.468,28.469z M32.5,13.125h-1.25v-2.5h1.25V13.125z M8.667,7.417c0-0.69,0.56-1.25,1.25-1.25"
            + "h14.916c0.69,0,1.25,0.56,1.25,1.25s-0.56,1.25-1.25,1.25H9.917C9.228,8.667,8.667,8.107,8.667,7.417z M24.833,13.75H9.917"
            + "c-0.69,0-1.25-0.56-1.25-1.25s0.56-1.25,1.25-1.25h14.916c0.69,0,1.25,0.56,1.25,1.25S25.523,13.75,24.833,13.75z";

    private SVGData() {
        // NOP
    }

    public static Node getReloadGraphic(double size, String style, String styleClass) {
        return getGraphic(RELOAD_WIDTH, RELOAD_HEIGHT, RELOAD_CONTENT, size, style, styleClass);
    }

    public static Node getRunGraphic(double size, String style, String styleClass) {
        return getGraphic(RUN_WIDTH, RUN_HEIGHT, RUN_CONTENT, size, style, styleClass);
    }

    public static Node getSaveGraphic(double size, String style, String styleClass) {
        return getGraphic(SAVE_WIDTH, SAVE_HEIGHT, SAVE_CONTENT, size, style, styleClass);
    }

    private static Node getGraphic(
            double width, double height, String content, double size, String style, String styleClass) {

        if (size <= 0.0) {
            throw new IllegalArgumentException("size is not positive.");
        }

        Pane pane = new Pane();
        pane.setPrefSize(size, size);

        if (content != null) {

            SVGPath svgPath = new SVGPath();

            if (styleClass != null && (!styleClass.isEmpty())) {
                svgPath.getStyleClass().add(styleClass);
            }

            if (style != null && (!style.isEmpty())) {
                svgPath.setStyle(style);
            }

            svgPath.setContent(content);

            svgPath.getTransforms().add(new Scale(size / width, size / height, 0.0, 0.0));

            pane.getChildren().add(svgPath);
        }

        Group group = new Group(pane);
        return group;
    }
}
