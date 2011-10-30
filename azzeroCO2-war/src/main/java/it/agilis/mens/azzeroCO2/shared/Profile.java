package it.agilis.mens.azzeroCO2.shared;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */


public enum Profile{
    Guest {
        @Override
        public String toString() {
            return "Guest";
        }

    },
    User {
        @Override
        public String toString() {
            return "SuperAdministrator";
        }

    },
    Administrator {
        @Override
        public String toString() {
            return "Administrator";
        }

    },
    SuperAdministrator {
        @Override
        public String toString() {
            return "SuperAdministrator";
        }

    };
}


