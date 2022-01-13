module com.kgozdz.centus {
    requires java.naming;
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires java.persistence;

    opens com.kgozdz.centus to javafx.fxml;
    opens com.kgozdz.centus.model to org.hibernate.orm.core;
    exports com.kgozdz.centus;
    exports com.kgozdz.centus.controller;
    opens com.kgozdz.centus.controller to javafx.fxml;
}