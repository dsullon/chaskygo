package pe.com.chaskygo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsullon on 3/12/2016.
 */

public class Vehicle {
    private String id;
    private String model;
    private String licencePlate;
    private String color;
    private String manufactureYear;
    private String engineNumber;
    private String serialNumber;
    private String idBrand;

    public Vehicle() {
    }

    public Vehicle(String id, String model, String licencePlate, String color, String manufactureYear, String engineNumber, String serialNumber, String idBrand) {
        this.setId(id);
        this.setModel(model);
        this.setLicencePlate(licencePlate);
        this.setColor(color);
        this.setManufactureYear(manufactureYear);
        this.setEngineNumber(engineNumber);
        this.setSerialNumber(serialNumber);
        this.setIdBrand(idBrand);
    }

    public String getId() {
        return id;
    }

    public Vehicle setId(String id) {
        this.id = id;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Vehicle setModel(String model) {
        this.model = model;
        return this;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public Vehicle setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
        return this;
    }

    public String getColor() {
        return color;
    }

    public Vehicle setColor(String color) {
        this.color = color;
        return this;
    }

    public String getManufactureYear() {
        return manufactureYear;
    }

    public Vehicle setManufactureYear(String manufactureYear) {
        this.manufactureYear = manufactureYear;
        return this;
    }

    public String getEngineNumber() {
        return engineNumber;
    }

    public Vehicle setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
        return this;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public Vehicle setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
        return this;
    }

    public String getIdBrand() {
        return idBrand;
    }

    public Vehicle setIdBrand(String idBrand) {
        this.idBrand = idBrand;
        return this;
    }

    public static Vehicle build(JSONObject jsonSource) {
        Vehicle vehicle = new Vehicle();
        try {
            vehicle.setId(jsonSource.getString("id"))
                    .setModel(jsonSource.getString("model"))
                    .setColor(jsonSource.getString("color"))
                    .setEngineNumber(jsonSource.getString("engine_number"))
                    .setIdBrand(jsonSource.getString("brand"))
                    .setLicencePlate(jsonSource.getString("licence_plate"))
                    .setManufactureYear(jsonSource.getString("manufacture_year"))
                    .setSerialNumber("serial_number");
            return vehicle;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Vehicle> build(JSONArray jsonSources) {
        int vehiclesCount = jsonSources.length();
        List<Vehicle> vehicles = new ArrayList<>();
        for(int i = 0; i < vehiclesCount; i++) {
            try {
                JSONObject jsonSource = (JSONObject) jsonSources.get(i);
                Vehicle vehicle = Vehicle.build(jsonSource);
                vehicles.add(vehicle);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return vehicles;
    }
}
