module Tim {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.web;
    requires javafx.base;
//    requires jlfgr;
    requires java.desktop;
    requires junit;
    requires java.sql;

    opens sec13a_javafx.helloworld;
    opens sec13b_javafx.layouts;
    opens sec13c_javafx.controls;
    opens sec13d_javafx.uiapp_ver0204;
    opens sec13d_javafx.uiapp_ver0211;
    opens sec13e_css;
    opens sec13f_scenebuilder;
    opens sec13g_challenge;
    opens sec13g_challenge.datamodel;
    opens sec15n_task_javafx.sample;
    opens sec16b_lambdafx;
    opens sec18b_unittesting1;
    opens sec18c_unittesting2;
    opens sec18d_parametrizedtesting;
    opens sec18e_challenge;
    opens sec19f_musicui;
    opens sec19f_musicui.model;
}