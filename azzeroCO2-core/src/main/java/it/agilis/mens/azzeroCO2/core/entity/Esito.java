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
            return "Pagato";
        }

    },
    IN_PAGAMENTO {
        @Override
        public String toString() {
            return "In pagamento";
        }

    },
    OMAGGIO {
        @Override
        public String toString() {
            return "In omaggio";
        }

    },
    PAGAMENTO_NON_AVVENUTO {
        @Override
        public String toString() {
            return "Pagamento non avvenuto";
        }

    };


}
