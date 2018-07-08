package com.dlsc.workbenchfx.extended;

import com.dlsc.workbenchfx.Workbench;
import com.dlsc.workbenchfx.custom.CustomDemo;
import com.dlsc.workbenchfx.custom.overlay.CustomOverlay;
import com.dlsc.workbenchfx.extended.calendar.CalendarModule;
import com.dlsc.workbenchfx.extended.notes.NotesModule;
import com.dlsc.workbenchfx.extended.preferences.PreferencesModule;
import com.dlsc.workbenchfx.view.controls.Dropdown;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CustomMenuItem;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class ExtendedDemo extends Application {

  public Workbench workbench;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    Scene myScene = new Scene(initWorkbench());

    primaryStage.setTitle("Extended WorkbenchFX Demo");
    primaryStage.setScene(myScene);
    primaryStage.setWidth(1000);
    primaryStage.setHeight(700);
    primaryStage.show();
    primaryStage.centerOnScreen();
  }

  private Workbench initWorkbench() {
    // Navigation Drawer
    Menu menu1 = new Menu("Customer", createIcon(FontAwesomeIcon.USER));
    Menu menu2 = new Menu("Tariff Management", createIcon(FontAwesomeIcon.BUILDING));
    Menu menu3 = new Menu("Complaints", createIcon(FontAwesomeIcon.BOMB));

    FontAwesomeIcon genericIcon = FontAwesomeIcon.QUESTION;
    MenuItem item11 = new MenuItem("Item 1.1", createIcon(genericIcon));
    MenuItem item12 = new MenuItem("Item 1.2", createIcon(genericIcon));
    MenuItem item13 = new MenuItem("Item 1.3", createIcon(genericIcon));
    MenuItem item14 = new MenuItem("Item 1.4", createIcon(genericIcon));

    Menu item21 = new Menu("Item 2.1", createIcon(genericIcon));
    MenuItem item22 = new MenuItem("Item 2.2", createIcon(genericIcon));

    MenuItem item211 = new MenuItem("Item 2.1.1", createIcon(genericIcon));
    MenuItem item212 = new MenuItem("Item 2.1.2", createIcon(genericIcon));
    MenuItem item213 = new MenuItem("Item 2.1.3", createIcon(genericIcon));
    MenuItem item214 = new MenuItem("Item 2.1.4", createIcon(genericIcon));
    MenuItem item215 = new MenuItem("Item 2.1.5", createIcon(genericIcon));

    MenuItem item31 = new MenuItem("Item 3.1", createIcon(genericIcon));
    MenuItem item32 = new MenuItem("Item 3.2", createIcon(genericIcon));
    MenuItem item33 = new MenuItem("Item 3.3", createIcon(genericIcon));

    MenuItem itemA = new MenuItem("Complaints", createIcon(FontAwesomeIcon.BOMB));
    MenuItem itemB = new MenuItem("Printing", createIcon(FontAwesomeIcon.PRINT));
    MenuItem itemC = new MenuItem("Settings", createIcon(FontAwesomeIcon.COGS));

    MenuItem showOverlay = new MenuItem("Show overlay");
    MenuItem showBlockingOverlay = new MenuItem("Show blocking overlay");

    item21.getItems().addAll(item211, item212, item213, item214, item215);

    menu1.getItems().addAll(item11, item12, item13, item14);
    menu2.getItems().addAll(item21, item22);
    menu3.getItems().addAll(item31, item32, item33);

    Button buttonLeft = new Button("Settings", new FontAwesomeIconView(FontAwesomeIcon.GEARS));
    buttonLeft.getStyleClass().add("button-inverted");

    workbench =
        Workbench.builder(
                new PreferencesModule(),
                new PreferencesModule(),
                new PreferencesModule(),
                new PreferencesModule(),
                new PreferencesModule(),
                new CalendarModule(),
                new CalendarModule(),
                new CalendarModule(),
                new CalendarModule(),
                new CalendarModule(),
                new NotesModule(),
                new NotesModule(),
                new NotesModule(),
                new NotesModule(),
                new NotesModule())
            .toolbarLeft(buttonLeft)
            .toolbarRight(
                Dropdown.of(
                    new FontAwesomeIconView(FontAwesomeIcon.ADDRESS_BOOK),
                    new CustomMenuItem(new Label("Content 1")),
                    new CustomMenuItem(new Label("Content 2"))),
                Dropdown.of(
                    new ImageView(CustomDemo.class.getResource("user_light.png").toExternalForm()),
                    new Menu(
                        "Submenus",
                        new FontAwesomeIconView(FontAwesomeIcon.PLUS),
                        new MenuItem("Submenu 1"),
                        new CustomMenuItem(new Label("CustomMenuItem"), false))),
                Dropdown.of(
                    "Text",
                    new ImageView(CustomDemo.class.getResource("user_light.png").toExternalForm()),
                    new CustomMenuItem(new Label("Content 1")),
                    new CustomMenuItem(new Label("Content 2"))))
            .navigationDrawerItems(
                menu1, menu2, menu3, itemA, itemB, itemC, showOverlay, showBlockingOverlay)
            .build();

    CustomOverlay customOverlay = new CustomOverlay(workbench, false);
    CustomOverlay blockingCustomOverlay = new CustomOverlay(workbench, true);
    showOverlay.setOnAction(event -> workbench.showOverlay(customOverlay, false));
    showBlockingOverlay.setOnAction(event -> workbench.showOverlay(blockingCustomOverlay, true));
    buttonLeft.setOnAction(event -> workbench.showOverlay(customOverlay, false));

    // This sets the custom style. Comment this out to have a look at the default styles.
    // workbenchFx.getStylesheets().add(CustomDemo.class.getResource("customTheme.css").toExternalForm());

    workbench
        .getStylesheets()
        .add(CustomDemo.class.getResource("customOverlay.css").toExternalForm());

    return workbench;
  }

  private Node createIcon(FontAwesomeIcon icon) {
    FontAwesomeIconView fontAwesomeIconView = new FontAwesomeIconView(icon);
    fontAwesomeIconView.getStyleClass().add("icon");
    return fontAwesomeIconView;
  }
}