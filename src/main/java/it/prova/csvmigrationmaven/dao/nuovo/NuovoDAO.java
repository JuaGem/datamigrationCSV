package it.prova.csvmigrationmaven.dao.nuovo;

import it.prova.csvmigrationmaven.model.Assicurato;
import it.prova.csvmigrationmaven.model.NotProcessed;

import java.sql.Connection;

public interface NuovoDAO {

    public void setConnection(Connection connection);

    public int insertAssicurato(Assicurato input) throws Exception;

    public int insertNotProcessed(NotProcessed input) throws Exception;


}
