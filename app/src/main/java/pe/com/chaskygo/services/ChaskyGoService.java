package pe.com.chaskygo.services;

import pe.com.chaskygo.models.Driver;
import pe.com.chaskygo.models.Vehicle;

/**
 * Created by dsullon on 3/12/2016.
 */

public class ChaskyGoService {
    private static String BASE_URL = "http://www.aymicg.com.pe/api/";
    public static String VEHICLES_URL = "http://www.aymicg.com.pe/api/vehicles";
    public static String DRIVERS_URL = "http://www.aymicg.com.pe/api/drivers";

    private Vehicle currentVehicle;
    private Driver currentDriver;

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }
    public Driver getCurrentDriver() {
        return currentDriver;
    }

    public void setCurrentVehicle(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }
    public void setCurrentDriver(Driver currentDriver) {
        this.currentDriver = currentDriver;
    }

    public static String getUrl(String resource){
        return  BASE_URL + resource;
    }
}
