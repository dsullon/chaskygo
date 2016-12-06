package pe.com.chaskygo;

import android.app.Application;

import pe.com.chaskygo.services.ChaskyGoService;

/**
 * Created by dsullon on 5/12/2016.
 */

public class ChaskyGoApp extends Application {
    static ChaskyGoApp instance;
    ChaskyGoService service = new ChaskyGoService();

    public ChaskyGoApp() {
        super();
        instance = this;
    }

    public static ChaskyGoApp getInstance() {
        return instance;
    }

    public ChaskyGoService getService() {
        return service;
    }
}
