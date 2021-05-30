package it.prova.csvmigrationmaven.test;


import com.opencsv.bean.CsvToBeanBuilder;
import it.prova.csvmigrationmaven.model.Assicurato;
import it.prova.csvmigrationmaven.model.CsvModel;
import it.prova.csvmigrationmaven.model.NotProcessed;
import it.prova.csvmigrationmaven.service.MyServiceFactory;
import it.prova.csvmigrationmaven.service.NuovoService;

import java.io.FileReader;
import java.util.List;

public class Test {


    public static void main(String[] args) throws Exception {

        NuovoService nuovoService = MyServiceFactory.getNuovoService();

        String fileName = "C:\\test\\archivio.csv";

        List<CsvModel> beans = new CsvToBeanBuilder(new FileReader(fileName))
                .withType(CsvModel.class)
                .build()
                .parse();

       beans.forEach(System.out::println);


        //inizializzo un contatore per vedere il progresso dell'operazione
        int contatore = 0;


        for (CsvModel csvModelItem : beans) {

            contatore++;


            if (csvModelItem.getNumeroSinistri() > 14) {
                NotProcessed notProcessed = new NotProcessed();
                notProcessed.setCodiceFiscale(csvModelItem.getCodiceFiscale());
                notProcessed.setOldId(csvModelItem.getId());
                nuovoService.inserisciNotProcessed(notProcessed);
                System.out.println(contatore + "-L'assicurato " + csvModelItem.getNome() +" "+ csvModelItem.getCognome() + " è sospetto");

            } else {

                Assicurato assicurato = new Assicurato();
                assicurato.setNome(csvModelItem.getNome());
                assicurato.setCognome(csvModelItem.getCognome());
                assicurato.setCodiceFiscale(csvModelItem.getCodiceFiscale());
                assicurato.setData(csvModelItem.getData());
                assicurato.setNumeroSinistri(csvModelItem.getNumeroSinistri());
                nuovoService.inserisciAssicurato(assicurato);
                System.out.println(contatore + "-L'assicurato " + csvModelItem.getNome() +" "+ csvModelItem.getCognome() + " è stato inserito nel db");


            }
        }


    }
}
