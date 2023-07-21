module OopProject {
    exports app.history.person;
    exports app.history.relic;
    exports app.crawler.person;
    exports app.crawler.relic;
    exports app.crawler.festival;
    exports app.crawler.event;
    exports app.crawler.dynasty;
    exports app.history.event;
    exports app.history.dynasty;
    exports app.history.festival;
    exports app.crawler.base;
    exports app.screen.controller;
    exports app.screen.controller.base;
    exports app.screen.controller.components;

    requires com.google.gson;
    requires java.desktop;
    requires javafx.base;
    requires org.jsoup;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens app to javafx.graphics;
    opens app.crawler.person to com.google.gson;
    opens app.crawler.relic to com.google.gson;
    opens app.crawler.festival to com.google.gson;
    opens app.crawler.event to com.google.gson;
    opens app.crawler.dynasty to com.google.gson;
    opens app.history.person to com.google.gson;
    opens app.history.dynasty to com.google.gson;
    opens app.history.event to com.google.gson;
    opens app.history.festival to com.google.gson;
    opens app.history.relic to com.google.gson;
    opens app.screen.controller to javafx.fxml;
    opens app.screen.controller.person to javafx.fxml;
    opens app.screen.controller.dynasty to javafx.fxml;
    opens app.screen.controller.event to javafx.fxml;
    opens app.screen.controller.relic to javafx.fxml;
    opens app.screen.controller.festival to javafx.fxml;
    opens app.screen.controller.components to javafx.fxml;
}
