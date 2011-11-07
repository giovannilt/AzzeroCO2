package it.agilis.mens.azzeroCO2.core.entity;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/7/11
 * Time: 9:51 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Esito {

    PAGATO {
        @Override
        public String toString() {
            return "PAGATO";
        }

    },
    KO {
        @Override
        public String toString() {
            return "KO";
        }

    },
    WAITING {
        @Override
        public String toString() {
            return "WAITING";
        }

    }
}
