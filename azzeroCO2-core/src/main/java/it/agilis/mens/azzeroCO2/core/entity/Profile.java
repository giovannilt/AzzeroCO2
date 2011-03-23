package it.agilis.mens.azzeroCO2.core.entity;

/**
 * Created by IntelliJ IDEA.
 * User: giovannilt
 * Date: 3/12/11
 * Time: 7:26 PM
 * To change this template use File | Settings | File Templates.
 */


public enum Profile {
    Administrator{
        @Override
		public String toString() {
            return "Administrator";
        }

    },
    Guest{
        @Override
		public String toString() {
            return "Guest";
        }

    };



}


