package tn.bfpme.services;

import tn.bfpme.utils.MyDataBase;

import java.sql.Connection;


public class ServiceConge {
    private final Connection cnx;

    public ServiceConge() {
        cnx = MyDataBase.getInstance().getCnx();
    }
}
