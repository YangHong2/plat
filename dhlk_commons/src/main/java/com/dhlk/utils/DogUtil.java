package com.dhlk.utils;


import Aladdin.Hasp;
import Aladdin.HaspStatus;


public class DogUtil {
    public static boolean checkLoginDog(){

        long feature = 1;

        String vendorCode =
                "cC18IPTp6t49JODIVoH2hq08K1vpE24OVQFzPvQlGa95rziQKxg2n2V4tbq+r31byhMd5hc/ReduXlbf" +
                        "lS6JOTEI+BcevkTl348h+wF4Ep8iP76dcM8z92wTmZxI5kp0bBruXTKpkGtlpvgOnR9Meo1T+8qTIfik" +
                        "QsDll9Hcqe3O85Wp8HnTKKdFu40yAKONneiKCJktfKKr8Hb24XQtARFJRw/vdDqgZsuxfzs/eaVQ2pWq" +
                        "T9KXjYvNv8CNkAtJyQ5f3r2R2ql3ONSMlOUfUNvPLQLA8PGjiAxdUSosKJBDo1TNF14Lj2ddC5w+ED//" +
                        "reUxDYvtYnQKCcMOmS2hgnMgmKyhjWVmv8LCOsSG2G5y9Umg6gH4JkZhZ1SRUwQaqL8ZAZ3MzAZDtl/k" +
                        "Gbj4yGxKIsd6w19PoZImqbRqcQOlKtl2KgvciSyoqhr98rHJ9pVkYRh3nSbLc6b+qy2DF/DgsPIHSCzY" +
                        "/nXE7eo3LEf61CNgotDrr/0cRQrHJWIasNBohxFKtsPqp++eDG2T5EkScpsrp72cPQuDKLERdFlsAMXr" +
                        "YcvZUG89d0XSzJRECCNCqfGaGB8VRkRJupEV+FbnJqxcA0o7HytnfFYDRpUbha9+EUP2F6lxYHbQEOCn" +
                        "F2nQfW8iYLwg6PaanTO99vQyR5HMqjdSPKqeqPgIVhMHj1MsLYqe4NKd/ZzPCXl1xNZ2IrtWW6rYWpC3" +
                        "pu76vmJMGT8ADkLaWcxng52Jo2f0njVVdzrkYfILL+F8mkIkv7SvFw24BAcMUPk97fvicSM13TY/2ilw" +
                        "rWilmB1AsXlKcqNnX/lBS20d/a1Ea8mle7Obcj0sEePLiBTRy7HKjF6hfdVGaLdfkzXKQg9HvyNRsGHX" +
                        "46QdhIsjTTSwXCsSbusKRJQkESSk1cif2uZCoUU/tsBfPZlrNUGiXH8VhbESDx49zn37xI8LtR3GSYJA" +
                        "Js6QbhI3pPrlBfzcm/LZOw==";

        Hasp hasp = new Hasp(feature);

        hasp.login(vendorCode);

        int status = hasp.getLastError();

        System.out.println(status);

        if (HaspStatus.HASP_STATUS_OK != status)
        {
            return false;
            /*handle error*/
        }
        return true;
    }



}
