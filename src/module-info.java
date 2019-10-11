module paceManager {
	exports application;
	
	opens classes to javafx.base;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
}