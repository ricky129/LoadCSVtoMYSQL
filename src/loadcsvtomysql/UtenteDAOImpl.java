/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package loadcsvtomysql;

/**
 *
 * @author ricky
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UtenteDAOImpl implements UtenteDAO {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
    }

    @Override
    public void insert(Utente utente) throws Exception {
        String sql = "INSERT INTO utenti (userid, username, password) VALUES (?, ?, ?)";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, utente.getUserid());
            stmt.setString(2, utente.getUsername());
            stmt.setString(3, utente.getPassword());
            stmt.executeUpdate();
        }
    }

    @Override
    public List<Utente> getAll() throws Exception {
        List<Utente> utenti = new ArrayList<>();
        String sql = "SELECT userid, username, password FROM utenti";
        try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int userid = rs.getInt("userid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                utenti.add(new Utente(userid, username, password));
            }
        }
        return utenti;
    }
}
