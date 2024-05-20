/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package loadcsvtomysql;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

/**
 * This class provides functionality to load data from a CSV file into a MySQL database.
 * It reads data from a CSV file, creates instances of the Utente class, inserts them into the database,
 * and retrieves them back from the database.
 *
 * @author ricky
 */
public class LoadCSVtoMYSQL {

    /**
     * Main method to execute the program.
     *
     * @param args Command line arguments (not used in this program)
     */
    public static void main(String[] args) {
        // Path to the CSV file
        String csvFile = "users.csv";
        String line;
        List<Utente> utenti = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            // Skip header line
            br.readLine();

            // Read each line of the CSV file
            while ((line = br.readLine()) != null) {
                // Split the line by comma to get user data
                String[] utenteData = line.split(",");
                // Create a new Utente object and add it to the list
                utenti.add(new Utente(Integer.parseInt(utenteData[0]), String.valueOf(utenteData[1]), String.valueOf(utenteData[2])));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize UtenteDAO implementation
        UtenteDAO utenteDAO = new UtenteDAOImpl();

        try {
            // Insert each Utente object into the database
            for (int i = 0; i < utenti.size(); i++)
                utenteDAO.insert(utenti.get(i));

            // Retrieve all Utente objects from the database
            List<Utente> allUtenti = utenteDAO.getAll();
            // Print details of each Utente object
            for (int i = 0; i < allUtenti.size(); i++) {
                Utente u = allUtenti.get(i);
                System.out.println("UserID: " + u.getUserid() + ", Username: " + u.getUsername() + ", Password: " + u.getPassword());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
