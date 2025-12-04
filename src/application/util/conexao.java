package application.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/db?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "MaLe0606";

    public static Connection getConnection() throws SQLException {
        try {
            // GARANTE QUE O DRIVER MYSQL SEJA CARREGADO
            Class.forName("com.mysql.cj.jdbc.Driver"); 
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL não encontrado!");
            e.printStackTrace();
        }

        return DriverManager.getConnection(URL, USER, PASS);
    }
    
    private void salvarNoBanco(String descricao, int statusLed) {
        String sql = "INSERT INTO registro_led (descricao, status_led, data_acao, hora_acao) "
                   + "VALUES (?, ?, CURDATE(), CURTIME())";

        try (Connection conn = conexao.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, descricao);
            stmt.setInt(2, statusLed);

            stmt.executeUpdate();
            System.out.println("Registro salvo no banco: " + descricao);

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao salvar no banco!");
        }
    }

}
