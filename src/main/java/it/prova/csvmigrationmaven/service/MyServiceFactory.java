package it.prova.csvmigrationmaven.service;


import it.prova.csvmigrationmaven.dao.nuovo.NuovoDAOImpl;

public class MyServiceFactory {

    public static NuovoService getNuovoService() {
        NuovoService nuovoService = new NuovoServiceImpl();
        nuovoService.setNuovoDAO(new NuovoDAOImpl());
        return nuovoService;
    }


}
