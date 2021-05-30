package it.prova.csvmigrationmaven.service;

import it.prova.csvmigrationmaven.dao.nuovo.NuovoDAO;
import it.prova.csvmigrationmaven.model.Assicurato;
import it.prova.csvmigrationmaven.model.NotProcessed;

public interface NuovoService {

    public void setNuovoDAO(NuovoDAO nuovoDAO);

    public int inserisciAssicurato(Assicurato input) throws Exception;

    public int inserisciNotProcessed(NotProcessed input) throws Exception;
}
