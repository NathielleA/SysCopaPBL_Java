module PBL3 {	
	exports app;
	exports app.model;
	
	requires javafx.graphics;
	requires javafx.fxml;
	
	requires javafx.controls;
	requires javafx.base;
	
	opens app.controller to javafx.graphics, javafx.fxml;
	opens app.model;
}