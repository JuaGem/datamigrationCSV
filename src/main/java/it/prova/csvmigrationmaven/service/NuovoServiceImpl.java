package it.prova.csvmigrationmaven.service;


import it.prova.csvmigrationmaven.connection.MyConnection;
import it.prova.csvmigrationmaven.dao.Constants;
import it.prova.csvmigrationmaven.dao.nuovo.NuovoDAO;
import it.prova.csvmigrationmaven.model.Assicurato;
import it.prova.csvmigrationmaven.model.NotProcessed;

import java.sql.Connection;

public class NuovoServiceImpl implements NuovoService {

    private NuovoDAO nuovoDAO;


    public void setNuovoDAO(NuovoDAO nuovoDAO) {
        this.nuovoDAO = nuovoDAO;

    }


    public int inserisciAssicurato(Assicurato input) throws Exception {
        if (input == null)
            throw new Exception("Valore di input non ammesso.");


        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            nuovoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = nuovoDAO.insertAssicurato(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }


    public int inserisciNotProcessed(NotProcessed input) throws Exception {
        if (input == null)
            throw new Exception("Valore di input non ammesso.");


        int result = 0;
        try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

            // inietto la connection nel dao
            nuovoDAO.setConnection(connection);

            // eseguo quello che realmente devo fare
            result = nuovoDAO.insertNotProcessed(input);

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return result;
    }
}
