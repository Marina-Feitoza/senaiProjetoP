package application.view;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.fazecast.jSerialComm.SerialPort;

import application.util.conexao;
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

        // BOTÃO VOLTAR (SEU ORIGINAL)
        btnVoltar.setOnAction(e -> { aplicativoController.voltar(btnVoltar); });

        porta = SerialPort.getCommPort("COM11");
        porta.setBaudRate(9600);

        boolean abriu = porta.openPort();

        if (abriu) {
            System.out.println("Conectado à COM11");
            txtStatus.setText("Conectado na porta: " + porta.getSystemPortName());
            iniciarLeitorSerial();    // <---- APENAS ADICIONADO
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

    // ---------------- LEITOR DA PORTA SERIAL ----------------
    private void iniciarLeitorSerial() {
        Thread leitor = new Thread(() -> {

            try {
                StringBuilder buffer = new StringBuilder();

                while (porta.isOpen()) {
                    while (porta.bytesAvailable() > 0) {

                        byte[] leitura = new byte[1];
                        porta.readBytes(leitura, 1);

                        char c = (char) leitura[0];

                        if (c == '\n') {
                            String msg = buffer.toString().trim();
                            buffer.setLength(0);

                            System.out.println("Recebido: " + msg);
                            processarMensagem(msg);
                        } else {
                            buffer.append(c);
                        }
                    }

                    Thread.sleep(5);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        });

        leitor.setDaemon(true);
        leitor.start();
    }

    // ---------------- PROCESSAMENTO DA MENSAGEM ----------------
    private void processarMensagem(String msg) {

        if (msg.contains("LED ligado")) {
            salvarNoBanco("ligado", 1);
        }

        else if (msg.contains("LED desligado")) {
            salvarNoBanco("desligado", 0);
        }

        else if (msg.contains("Tempo que ficou ligado")) {
            salvarNoBanco("tempo", 0);
        }
    }

    // ---------------- SALVAR NO BANCO (MYSQL) ----------------
    private void salvarNoBanco(String descricao, int statusLed) {
        String sql = "INSERT INTO registro_led (descricao, status_led, data_acao, hora_acao) "
                   + "VALUES (?, ?, CURDATE(), CURTIME())";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setInt(2, statusLed);

            stmt.executeUpdate();

            System.out.println("REGISTRO SALVO: " + descricao);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
