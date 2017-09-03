package com.fantasy.dao.exceptions;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;

import java.sql.SQLException;

/**
 * Created by Djelu on 03.09.2017.
 */
public class SQLiteExceptionTranslator extends SQLErrorCodeSQLExceptionTranslator {

    @Override
    protected DataAccessException customTranslate(String task, String sql, SQLException sqlEx) {
        MyException myException = null;

        if(sqlEx.getErrorCode() == 0){
            myException = new MyException(sql = " - " + sqlEx.getMessage());
        }

        return myException;
    }
}
