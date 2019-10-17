module paceManager {
	exports application;
	exports classes;
	
	opens classes to javafx.base;

	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires poi.ooxml;
	requires poi;
}