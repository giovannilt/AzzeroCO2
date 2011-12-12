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
            return "Pagato";//"PAGATO";
        }

    },
    IN_PAGAMENTO {
        @Override
        public String toString() {
            return "In pagamento";//"IN_PAGAMENTO";
        }

    }, PAGAMENTO_NON_AVVENUTO {
        @Override
        public String toString() {
            return "Pagamento non avvenuto"; //"PAGAMENTO_NON_AVVENUTO";
        }

    };


}
