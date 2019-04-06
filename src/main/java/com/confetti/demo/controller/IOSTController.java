package com.confetti.demo.controller;

import com.confetti.demo.service.IOSTService;
import iost.IOST;
import iost.Keychain;
import iost.model.account.Account;
import iost.model.transaction.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

public class IOSTController {

    IOST iost;
    IOSTService iostService;
    Keychain account;

    @Autowired
    public IOSTController(IOSTService iostService, Keychain account, IOST iost) {
        this.iostService = iostService;
        this.account = account;
        this.iost = iost;
    }

    public void callContract (String contract, String api, Object data){
        Transaction t = iostService.callContract(contract,api,data);
        account.publish(t);
    }




}
