package pe.com.chaskygo.activities;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.com.chaskygo.R;
import pe.com.chaskygo.adapters.DriversAdapter;
import pe.com.chaskygo.models.Driver;
import pe.com.chaskygo.services.ChaskyGoService;

public class DriversActivity extends AppCompatActivity {
    RecyclerView driversRecyclerView;
    DriversAdapter driversAdapter;
    GridLayoutManager driversLayoutManager;
    List<Driver> drivers;
    int                 spanCount;
    static String       TAG = "LatestNews";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drivers);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drivers = new ArrayList<>();
        driversRecyclerView = (RecyclerView) findViewById(R.id.driversRecyclerView);
        driversAdapter = new DriversAdapter();
        spanCount = adjustSpanCount(getResources().getConfiguration());
        driversLayoutManager = new GridLayoutManager(this, spanCount);
        driversAdapter.setDrivers(drivers);
        driversRecyclerView.setAdapter(driversAdapter);
        driversRecyclerView.setLayoutManager(driversLayoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateDrivers();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        spanCount = adjustSpanCount(newConfig);
        driversLayoutManager.setSpanCount(spanCount);
    }

    private int adjustSpanCount(Configuration config) {
        return config.orientation ==
                Configuration.ORIENTATION_LANDSCAPE ?
                3 : 2;
    }
    private void updateDrivers() {
        String url = ChaskyGoService.getUrl("drivers");
        AndroidNetworking.get(url)
                .addHeaders("X-API-KEY", "123456")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("ok")) {
                                drivers = Driver.build(response.getJSONArray("drivers"));
                                driversAdapter.setDrivers(drivers);
                                driversAdapter.notifyDataSetChanged();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onError(ANError anError) {
                        Log.d(TAG, "Error: " + anError.getErrorBody());

                    }
                });
    }

}
