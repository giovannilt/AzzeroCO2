package it.agilis.mens.azzeroCO2.core.entity;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

public enum Eventi {

    EVENTO {
        @Override
        public String toString() {
            return "evento";
        }

    },
    ANNO_DI_ATTIVITA {
        @Override
        public String toString() {
            return "Anno Di Attivita";
        }

    },
    UNA_PUBBLICAZIONE {
        @Override
        public String toString() {
            return "Una pubblicazione";
        }

    },
    WEB {
        @Override
        public String toString() {
            return "WEB";
        }
    },
    CONOSCI_CO2 {
        @Override
        public String toString() {
            return "Conosci la CO2";
        }

    };


}


