module org.example{
    requires javafx.controls;
    requires javafx.fxml;
    requires AnimateFX;

    requires java.naming;
    requires com.oracle.database.jdbc;
    requires java.sql;
    requires java.desktop;
    requires org.postgresql.jdbc;
    requires java.mail;

    opens org.example to javafx.fxml;
    exports org.example;
}