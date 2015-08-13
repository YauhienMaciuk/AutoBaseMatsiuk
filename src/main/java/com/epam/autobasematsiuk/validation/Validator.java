package com.epam.autobasematsiuk.validation;

import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * The class Validator. Designed for validate the data.
 */
public class Validator {

    public static final Pattern NAME_PATTERN = Pattern.compile("[A-Za-zА-Яа-я]{2,25}$");
    public static final Pattern LOGIN_AND_PASSWORD_PATTERN = Pattern.compile("[A-Za-z0-9]{7,25}$");

    /**
     * The method validates the data of new client
     *
     * @param firstName is the first name
     * @param lastName  is the last name
     * @param login     is the login
     * @param password  is the password
     * @return true if data of new client are correct
     */
    public boolean validateNewClient(String firstName, String lastName, String login, String password) {
        boolean flag = true;
        if (!NAME_PATTERN.matcher(firstName).find()) {
            flag = false;
        }
        if (!NAME_PATTERN.matcher(lastName).find()) {
            flag = false;
        }
        if (!LOGIN_AND_PASSWORD_PATTERN.matcher(login).find()) {
            flag = false;
        }
        if (!LOGIN_AND_PASSWORD_PATTERN.matcher(password).find()) {
            flag = false;
        }
        return flag;
    }

    /**
     * The method checks the date of the new bid
     *
     * @param date is the date
     * @return true if the date is correct
     */
    public boolean checkDate(String date) {
        LocalDate currentDate = LocalDate.now();
        LocalDate clientDate = LocalDate.parse(date);
        if (clientDate.isAfter(currentDate)) {
            return true;
        }
        return false;
    }

}

