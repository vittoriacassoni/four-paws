module four.paws {
    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.sql;
    requires java.desktop;
    requires com.microsoft.sqlserver.jdbc;
    requires junit;
    //requires java.mail;
    opens sample;
    opens comuns.access to javafx.base;
    opens business.tests to junit;
}