package it.agilis.mens.azzeroCO2.client.components.eventi;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */


public enum Eventi {
    MAIN{
        @Override
		public String toString() {
            return "MAIN";
        }

    },
    EVENTO{
        @Override
		public String toString() {
            return "evento";
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
    WEB{
       @Override
		public String toString() {
            return "WEB";
        }
    },
    CONOSCI_CO2{
        @Override
		public String toString() {
            return "Conosci CO2";
        }

    };


}


