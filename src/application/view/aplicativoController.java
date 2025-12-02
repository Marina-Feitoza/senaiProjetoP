package application.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class aplicativoController {

    @FXML
    private AnchorPane conteudoPane;
    private void carregarTela(String fxmlfile) {
    	try {
    		Parent fxml = FXMLLoader.load(getClass().getResource(fxmlfile));
    		conteudoPane.getChildren().clear();
    		conteudoPane.getChildren().add(fxml);
    		
    		conteudoPane.setTopAnchor(fxml,0.0);
    		conteudoPane.setBottomAnchor(fxml,0.0);
    		conteudoPane.setLeftAnchor(fxml,0.0);
    		conteudoPane.setRightAnchor(fxml,0.0);
    		Scene cena = conteudoPane.getScene();
    		
    		if (cena != null) {
    			Stage stage = (Stage) cena.getWindow();
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    @FXML
	private void abrirPaginaInicial() {
		carregarTela("principal.fxml");
	}
	
    @FXML
    private void abrirCalculadora() {
    	carregarTela("calculadora.fxml");
    }
    
    @FXML
    private void abrirCalculadoraIMC() {
    	carregarTela("calcularIMC.fxml");
    }
    
    @FXML
    private void abrirGame() {
    	carregarTela("jogo.fxml");
    }
    
    @FXML
    private void abrirTiro() {
    	carregarTela("jogoTiro.fxml");
    }
    
    @FXML
    private void abrirMedia() {
    	carregarTela("calcularMedia.fxml");
    }
    @FXML
    private void abrirIdade() {
    	carregarTela("idade.fxml");
    }
    
    @FXML
    private void abrirLed() {
    	carregarTela("led.fxml");
    }
    @FXML
    public static void voltar(Button btnVoltar) {
    	try {
    		Stage stage = (Stage) btnVoltar.getScene().getWindow();
    		AnchorPane conteudoPane = (AnchorPane) stage.getScene().lookup("#conteudoPane");
    		Parent fxml = FXMLLoader.load(aplicativoController.class.getResource("aplicativo.fxml"));
    		
    		conteudoPane.getChildren().setAll(fxml); 
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }
    /*public static void voltar(String file, AnchorPane pane) {
    	try {
    		Parent fxml = FXMLLoader.load(getClass().getResource(file));
    		pane.getChildren().clear();
    		pane.getChildren().add(fxml);
    		
    		pane.setTopAnchor(fxml,0.0);
    		pane.setBottomAnchor(fxml,0.0);
    		pane.setLeftAnchor(fxml,0.0);
    		pane.setRightAnchor(fxml,0.0);
    		Scene cena = pane.getScene();
    		
    		if (cena != null) {
    			Stage stage = (Stage) cena.getWindow();
    		}
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
    }*/
    
	@FXML
	private void initialize() {
		//abrirPaginaInicial();
	}
}
