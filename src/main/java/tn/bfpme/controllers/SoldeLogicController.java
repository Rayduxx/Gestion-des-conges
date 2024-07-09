package tn.bfpme.controllers;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SoldeLogicController {
    public static int calculateSoldeMaternite(LocalDate creationDate) {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
        return (int) ChronoUnit.YEARS.between(creationDate, LocalDate.now()) * 30;
    }

    public static double calculateSoldeAnnuelle(LocalDate creationDate) {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
        return (double) ChronoUnit.YEARS.between(creationDate, LocalDate.now()) * 2.5;
    }

    public static int calculateSoldeExceptionnel(LocalDate creationDate) {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
        return (int) ChronoUnit.YEARS.between(creationDate, LocalDate.now()) * 5;
    }

    public static int calculateSoldeMaladie(LocalDate creationDate) {
        if (creationDate == null) {
            creationDate = LocalDate.now();
        }
        return (int) ChronoUnit.YEARS.between(creationDate, LocalDate.now()) * 10;
    }
}
