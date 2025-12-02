module ProjetoP {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires java.sql;
	requires com.fazecast.jSerialComm;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.view to javafx.fxml;
	exports application.view;
}
