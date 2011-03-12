package it.agilis.mens.azzeroCO2.pages.main;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Eventi {

     EVENTO{
        @Override
		public String toString() {
            return "Evento";
        }
     },
    ANNO_DI_ATTIVITA{
        @Override
		public String toString() {
            return "Anno Di Attivita'";
        }
    },
    CONSUMO_CARTA{
        @Override
		public String toString() {
            return "Consumo Carta";
        }
    },
    CONOSCI_CO2{
        @Override
		public String toString() {
            return "Conosci CO2";
        }
    }


}


