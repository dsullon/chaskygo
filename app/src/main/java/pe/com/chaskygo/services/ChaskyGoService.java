package pe.com.chaskygo.services;

import pe.com.chaskygo.models.Vehicle;

/**
 * Created by dsullon on 3/12/2016.
 */

public class ChaskyGoService {

    public static String VEHICLES_URL = "http://www.aymicg.com.pe/api/vehicles";
    public static String DRIVERS_URL = "http://www.aymicg.com.pe/api/drivers";

    private Vehicle currentVehicle;

    public Vehicle getCurrentVehicle() {
        return currentVehicle;
    }

    public void setCurrentSource(Vehicle currentVehicle) {
        this.currentVehicle = currentVehicle;
    }
}
