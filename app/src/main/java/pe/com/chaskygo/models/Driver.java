package pe.com.chaskygo.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dsullon on 5/12/2016.
 */

public class Driver {
    private String id;
    private String firstName;
    private String lastName;
    private String identityCard;
    private String address;
    private String phone;
    private String driverLicence;

    public Driver() {
    }

    public Driver(String id, String firstName, String lastName, String identityCard, String address, String phone, String driverLicence) {
        this.setId(id);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setIdentityCard(identityCard);
        this.setAddress(address);
        this.setPhone(phone);
        this.setDriverLicence(driverLicence);
    }

    public String getId() {
        return id;
    }

    public Driver setId(String id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Driver setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Driver setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public Driver setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Driver setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Driver setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getDriverLicence() {
        return driverLicence;
    }

    public Driver setDriverLicence(String driverLicence) {
        this.driverLicence = driverLicence;
        return this;
    }

    public static Driver build(JSONObject jsonSource) {
        Driver driver = new Driver();
        try {
            driver.setId(jsonSource.getString("id"))
                    .setFirstName(jsonSource.getString("last_name"))
                    .setLastName(jsonSource.getString("first_name"))
                    .setIdentityCard(jsonSource.getString("identity_card"))
                    .setAddress(jsonSource.getString("address"))
                    .setPhone(jsonSource.getString("phone"))
                    .setDriverLicence(jsonSource.getString("driver_licence"));
            return driver;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<Driver> build(JSONArray jsonSources) {
        int driversCount = jsonSources.length();
        List<Driver> drivers = new ArrayList<>();
        for(int i = 0; i < driversCount; i++) {
            try {
                JSONObject jsonSource = (JSONObject) jsonSources.get(i);
                Driver driver = Driver.build(jsonSource);
                drivers.add(driver);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return drivers;
    }
}
