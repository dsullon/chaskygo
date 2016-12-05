package pe.com.chaskygo.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import pe.com.chaskygo.R;
import pe.com.chaskygo.models.User;
import pe.com.chaskygo.services.ChaskyGoService;

public class LoginActivity extends AppCompatActivity {
    User                user;
    Button              signInbutton;
    TextInputEditText   userTextInputEditText;
    TextInputEditText   passwordTextInputEditText;
    Intent              intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        userTextInputEditText = (TextInputEditText) findViewById(R.id.userTextInputEditText);
        passwordTextInputEditText = (TextInputEditText) findViewById(R.id.passwordTextInputEditText);
        signInbutton = (Button) findViewById(R.id.signInbutton);
        signInbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(view.getContext(), MainActivity.class);
                logIn();
            }
        });
    }

    private void logIn() {
        String url = ChaskyGoService.getUrl("users/login");
        AndroidNetworking.post(url)
                .addHeaders("Content-Type", "application/x-www-form-urlencoded")
                .addHeaders("X-API-KEY", "123456")
                .addBodyParameter("user", userTextInputEditText.getText().toString())
                .addBodyParameter("password", passwordTextInputEditText.getText().toString())
                .setTag("Login")
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("ok")) {
                                user = User.build(response.getJSONObject("user"));
                                startActivity(intent);
                            }
                        } catch (JSONException e) {
                            Log.d("Login", "Error: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d("Login", "Error: " + anError.getErrorBody());

                    }
                });
    }
}
