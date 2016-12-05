package pe.com.chaskygo.models;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by dsullon on 5/12/2016.
 */

public class User {
    private String  id;
    private String  name;
    private String  userName;

    public User() {
    }

    public User(String id, String name, String userName) {
        this.setId(id);
        this.setName(name);
        this.setUserName(userName);
    }

    public String getId() {
        return id;
    }

    public User setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public User setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public static User build(JSONObject jsonSource) {
        User user = new User();
        try {
            user.setId(jsonSource.getString("id"))
                    .setName(jsonSource.getString("name"))
                    .setUserName(jsonSource.getString("user_name"));
            return user;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
