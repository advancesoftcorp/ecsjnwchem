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

    private static final double STOP_WIDTH = 40.0;

    private static final double STOP_HEIGHT = 40.0;

    private static final String STOP_CONTENT = "M20,0C8.954,0,0,8.954,0,20c0,11.046,8.954,20,20,20c11.046,0,20-8.954,20-20C40,8.954,31.046,0,20,0z M4,20"
            + "c0-8.822,7.178-16,16-16c3.691,0,7.085,1.269,9.795,3.377L7.377,29.795C5.269,27.085,4,23.691,4,20z M20,36"
            + "c-3.691,0-7.085-1.269-9.795-3.377l22.419-22.419C34.731,12.915,36,16.309,36,20C36,28.823,28.823,36,20,36z";

    private static final double ATOMS_WIDTH = 399.998;

    private static final double ATOMS_HEIGHT = 399.997;

    private static final String ATOMS_CONTENT = "M292.41,236.617l-42.814-27.769c5.495-15.665,4.255-33.162-3.707-48.011l35.117-31.373"
            + "c19.292,12.035,45.001,9.686,61.771-7.085c19.521-19.52,19.521-51.171,0-70.692c-19.522-19.521-51.175-19.521-70.694,0"
            + "c-15.378,15.378-18.632,38.274-9.788,56.848l-35.121,31.378c-16.812-11.635-38.258-13.669-56.688-6.078l-40.5-55.733"
            + "c14.528-19.074,13.095-46.421-4.331-63.849c-19.004-19.004-49.816-19.004-68.821,0c-19.005,19.005-19.005,49.818,0,68.822"
            + "c13.646,13.646,33.374,17.491,50.451,11.545l40.505,55.738c-20.002,23.461-18.936,58.729,3.242,80.906"
            + "c0.426,0.426,0.864,0.825,1.303,1.237l-39.242,68.874c-16.31-3.857-34.179,0.564-46.899,13.286"
            + "c-19.521,19.522-19.521,51.175,0,70.694c19.521,19.521,51.173,19.521,70.693,0c19.317-19.315,19.508-50.503,0.593-70.069"
            + "l39.239-68.867c19.705,5.658,41.737,0.978,57.573-14.033l42.855,27.79c-2.736,12.706,0.821,26.498,10.696,36.372"
            + "c15.469,15.469,40.544,15.469,56.012,0c15.468-15.466,15.468-40.543,0-56.011C329.831,226.518,307.908,225.209,292.41,236.617z"
            + " M83.129,338.906c-0.951,1.078-1.846,2.096-2.724,2.973c-1.094,1.093-2.589,2.425-4.444,2.998"
            + "c-2.33,0.719-4.711,0.086-6.536-1.739c-4.772-4.771-2.947-13.799,4.246-20.989c7.195-7.195,16.219-9.021,20.993-4.247"
            + "c1.824,1.822,2.457,4.205,1.737,6.536c-0.572,1.855-1.904,3.354-2.997,4.444c-0.878,0.876-1.896,1.771-2.975,2.722"
            + "c-1.245,1.096-2.535,2.229-3.805,3.497C85.355,336.37,84.224,337.66,83.129,338.906z M279.56,59.17"
            + "c7.193-7.193,16.219-9.02,20.991-4.247c1.823,1.823,2.458,4.205,1.737,6.537c-0.572,1.856-1.905,3.354-2.997,4.446"
            + "c-0.876,0.875-1.894,1.77-2.974,2.72c-1.246,1.097-2.534,2.229-3.805,3.498c-1.271,1.271-2.403,2.562-3.5,3.808"
            + "c-0.948,1.076-1.846,2.097-2.72,2.973c-1.093,1.093-2.591,2.425-4.446,2.998c-2.332,0.719-4.712,0.086-6.536-1.739"
            + "C270.541,75.391,272.366,66.362,279.56,59.17z M73.322,37.854c-0.928,1.05-1.799,2.042-2.648,2.895"
            + "c-1.063,1.063-2.521,2.358-4.329,2.919c-2.269,0.698-4.587,0.083-6.364-1.691c-4.646-4.647-2.866-13.436,4.138-20.438"
            + "c7.003-7.004,15.788-8.782,20.436-4.135c1.776,1.776,2.395,4.095,1.692,6.363c-0.561,1.807-1.854,3.265-2.918,4.326"
            + "c-0.854,0.854-1.846,1.727-2.896,2.648c-1.213,1.066-2.469,2.17-3.704,3.406C75.492,35.384,74.387,36.642,73.322,37.854z"
            + " M159.967,155.76c8.593-8.594,19.371-10.774,25.073-5.073c2.18,2.181,2.937,5.024,2.078,7.81"
            + "c-0.688,2.218-2.277,4.005-3.583,5.312c-1.047,1.047-2.265,2.112-3.553,3.248c-1.486,1.311-3.026,2.662-4.544,4.179"
            + "c-1.518,1.519-2.87,3.058-4.178,4.547c-1.136,1.287-2.205,2.505-3.251,3.55c-1.306,1.31-3.093,2.896-5.311,3.582"
            + "c-2.784,0.859-5.628,0.104-7.811-2.077C149.189,175.132,151.374,164.354,159.967,155.76z M299.11,262.103"
            + "c-0.868,0.866-2.056,1.923-3.524,2.376c-1.846,0.569-3.729,0.068-5.178-1.377c-3.783-3.781-2.338-10.933,3.365-16.633"
            + "c5.697-5.7,12.849-7.146,16.632-3.362c1.443,1.443,1.945,3.33,1.376,5.179c-0.453,1.471-1.51,2.656-2.375,3.521"
            + "c-0.694,0.692-1.5,1.402-2.355,2.155c-0.984,0.866-2.008,1.766-3.013,2.771c-1.007,1.006-1.907,2.026-2.771,3.016"
            + "C300.512,260.604,299.802,261.409,299.11,262.103z";

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

    public static Node getStopGraphic(double size, String style, String styleClass) {
        return getGraphic(STOP_WIDTH, STOP_HEIGHT, STOP_CONTENT, size, style, styleClass);
    }

    public static Node getAtomsGraphic(double size, String style, String styleClass) {
        return getGraphic(ATOMS_WIDTH, ATOMS_HEIGHT, ATOMS_CONTENT, size, style, styleClass);
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
