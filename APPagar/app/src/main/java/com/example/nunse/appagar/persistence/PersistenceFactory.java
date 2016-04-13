package com.example.nunse.appagar.persistence;

import com.example.nunse.appagar.conf.APPagar;
import com.example.nunse.appagar.conf.DBConf;
import com.example.nunse.appagar.persistence.gateway.ContactoGateway;
import com.example.nunse.appagar.persistence.gateway.DeudaGateway;
import com.example.nunse.appagar.persistence.gateway.impl.ContactoGatewayImpl;

/**
 * Created by nunse on 13/04/2016.
 */
public class PersistenceFactory {

    private static ContactoGateway cg;
    private static DeudaGateway dg;


    public static ContactoGateway getContactoGateway()
    {
        DBConf.setAssets(APPagar.getContext());
        if(cg == null)
        {
            cg = new ContactoGatewayImpl();
        }
        cg.establecerDB(getDBHelper());
        return cg;
    }


    private static DBHelper getDBHelper()
    {
        DBConf.setAssets(APPagar.getContext());
        return new DBHelper(APPagar.getContext());
    }
}