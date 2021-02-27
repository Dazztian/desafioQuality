package com.mercadolibre.desafioquality.utils;

public class InterestUtils {

    public static double calculateInterests(int dues)
    {
    double interest = 0;

    if (dues >= 1 && dues <= 3) interest = 5;
    if (dues > 3 && dues <= 6) interest = 10;
    if (dues > 6 && dues <= 9) interest = 15;
    if (dues > 9 && dues <= 12) interest = 20;

    return interest;

    }

    public static double calculatePorcentageInterests(int dues)
    {
        return calculateInterests(dues)/100;
    }


    }