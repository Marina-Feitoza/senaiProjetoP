package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class ledController implements Initializable {

    @FXML private Button btnLigar;
    @FXML private Button btnDesligar;
    @FXML private Button btnVoltar;
    @FXML private Text txtStatus;

    private SerialPort porta;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnVoltar.setOnAction(e -> { aplicativoController.voltar(btnVoltar); });

        porta = SerialPort.getCommPort("COM11");
        porta.setBaudRate(9600);

        // abre só uma vez
        boolean abriu = porta.openPort();

        if (abriu) {
            System.out.println("Conectado à COM11");
            txtStatus.setText("Conectado na porta: " + porta.getSystemPortName());
        } else {
            System.out.println("Erro ao abrir COM11");
            txtStatus.setText("Erro ao conectar porta serial!");
        }

        btnLigar.setOnAction(e -> enviarSerial("1"));
        btnDesligar.setOnAction(e -> enviarSerial("0"));
    }

    private void enviarSerial(String comando) {
        if (porta != null && porta.isOpen()) {
            porta.writeBytes(comando.getBytes(), 1);
            txtStatus.setText("Enviado: " + comando);
        } else {
            txtStatus.setText("Porta não está aberta!");
        }
    }
}