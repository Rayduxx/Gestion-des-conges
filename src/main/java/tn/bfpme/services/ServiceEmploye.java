package tn.bfpme.services;

import tn.bfpme.utils.MyDataBase;
import java.sql.Connection;
import tn.bfpme.interfaces.IEmploye;

public class ServiceEmploye implements IEmploye{
    private final Connection cnx;

    public ServiceEmploye() {
        cnx = MyDataBase.getInstance().getCnx();
    }
}
