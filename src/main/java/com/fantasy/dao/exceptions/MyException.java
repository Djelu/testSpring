package com.fantasy.dao.exceptions;

import org.springframework.dao.DataAccessException;

/**
 * Created by Djelu on 03.09.2017.
 */
public class MyException extends DataAccessException {

    public MyException(String msg) {
        super(msg);
    }
}
