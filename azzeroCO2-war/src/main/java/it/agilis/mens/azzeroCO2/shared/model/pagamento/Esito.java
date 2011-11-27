package it.agilis.mens.azzeroCO2.shared.model.pagamento;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 11/15/11
 * Time: 10:22 PM
 * To change this template use File | Settings | File Templates.
 */
public enum Esito {

    PAGATO {
        @Override
        public String toString() {
            return "PAGATO";
        }

    },
    IN_PAGAMENTO {
        @Override
        public String toString() {
            return "IN_PAGAMENTO";
        }

    },
    ANNULLATO {
        @Override
        public String toString() {
            return "ANNULLATO";
        }
    },
    PAGAMENTO_NON_AVVENUTO {
        @Override
        public String toString() {
            return "PAGAMENTO_NON_AVVENUTO";
        }

    };

}

