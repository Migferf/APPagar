package com.example.nunse.appagar.conf;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by nunse on 13/04/2016.
 */
public class DBConf {
    private static final String CONF_FILE = "sql.properties";

    private static DBConf instance;
    private Properties properties;
    private static InputStream inputStream;

    private DBConf() {
        properties = new Properties();
        try {
            //properties.load(Conf.class.getClassLoader().getResourceAsStream(CONF_FILE));
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("No se pudo cargar el archivo de SQL", e);
        }
    }

    public static String get(String key) {
        return getInstance().getProperty(key);
    }

    public static void setAssets(Context context)
    {
        try {
            inputStream=context.getResources().getAssets().open("sql.properties");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("SQL no encontrado en el archivo de configuración");
        }
        return value;
    }

    private static DBConf getInstance() {
        if (instance == null) {
            instance = new DBConf();
        }
        return instance;
    }
}
