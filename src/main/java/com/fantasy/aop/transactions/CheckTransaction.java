package com.fantasy.aop.transactions;

import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by Djelu on 04.09.2017.
 */
public class CheckTransaction {

    public void chechTransactionActive(){
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
    }
}
