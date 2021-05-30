package it.prova.csvmigrationmaven.dao.nuovo;


import it.prova.csvmigrationmaven.dao.AbstractMySQLDAO;
import it.prova.csvmigrationmaven.model.Assicurato;
import it.prova.csvmigrationmaven.model.NotProcessed;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class NuovoDAOImpl extends AbstractMySQLDAO implements NuovoDAO {


    public void setConnection(Connection connection) {
        this.connection = connection;

    }

    public int insertAssicurato(Assicurato input) throws Exception {
        // prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO assicurato (nome,cognome,codfis,data,numeroSinistri) VALUES (?, ?, ?, ?, ?);")) {

            ps.setString(1, input.getNome());
            ps.setString(2, input.getCognome());
            ps.setString(3, input.getCodiceFiscale());
            ps.setDate(4, new java.sql.Date(input.getData().getTime()));
            ps.setInt(5, input.getNumeroSinistri());
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    public int insertNotProcessed(NotProcessed input) throws Exception {
        // prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
        if (isNotActive())
            throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

        if (input == null)
            throw new Exception("Valore di input non ammesso.");

        int result = 0;
        try (PreparedStatement ps = connection.prepareStatement(
                "INSERT INTO not_processed (codiceFiscale, oldID) VALUES (?, ?);")) {

            ps.setString(1, input.getCodiceFiscale());
            ps.setLong(2, input.getOldId());
            result = ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }

}
