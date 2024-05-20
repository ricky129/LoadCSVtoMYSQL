/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package loadcsvtomysql;

/**
 *
 * @author ricky
 */
import java.util.List;

public interface UtenteDAO {
    void insert(Utente utente) throws Exception;
    List<Utente> getAll() throws Exception;
}
