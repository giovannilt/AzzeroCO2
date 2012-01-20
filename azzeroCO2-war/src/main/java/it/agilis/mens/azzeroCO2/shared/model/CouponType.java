package it.agilis.mens.azzeroCO2.shared.model;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */

public enum CouponType {
    PERCENTO {
        @Override
        public String toString() {
            return "%";
        }

    },
    EURO {
        @Override
        public String toString() {
            return "€";
        }

    },
    OMAGGIO {
        @Override
        public String toString() {
            return "Omaggio";
        }

    }
}


