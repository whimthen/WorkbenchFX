package com.dlsc.workbenchfx;

import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class TestView extends BorderPane {

  public TestView() {
    setCenter(new Label("If you can read this, the test view has been successfully shown!"));
    getStylesheets().add("com/dlsc/workbenchfx/css/main.css");
  }

}
