package pe.com.chaskygo.services;

import pe.com.chaskygo.models.Vehicle;

/**
 * Created by dsullon on 3/12/2016.
 */

public class ChaskyGoService {
    private static String BASE_URL = "http://www.aymicg.com.pe/api/";
    public static String VEHICLES_URL = "http://www.aymicg.com.pe/api/vehicles";
    public static String DRIVERS_URL = "http://www.aymicg.com.pe/api/drivers";

    private Vehicle currentVehicle;

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }

    public static String getUrl(String resource){
        return  BASE_URL + resource;
    }
}
