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
import android.view.Menu;
import android.view.MenuItem;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import pe.com.chaskygo.R;
import pe.com.chaskygo.adapters.VehiclesAdapter;
import pe.com.chaskygo.models.Vehicle;
import pe.com.chaskygo.services.ChaskyGoService;

public class MainActivity extends AppCompatActivity {

    RecyclerView vehicleRecyclerView;
    VehiclesAdapter vehiclesAdapter;
    GridLayoutManager vehiclesLayoutManager;
    List<Vehicle> vehicles;
    int                 spanCount;
    static String       TAG = "Vehicles";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        vehicles = new ArrayList<>();
        vehicleRecyclerView = (RecyclerView) findViewById(R.id.vehiclesRecyclerView);
        vehiclesAdapter = new VehiclesAdapter();
        spanCount = 2; // adjustSpanCount(getResources().getConfiguration());
        vehiclesLayoutManager = new GridLayoutManager(this, spanCount);
        vehiclesAdapter.setVehicles(vehicles);
        vehicleRecyclerView.setAdapter(vehiclesAdapter);
        vehicleRecyclerView.setLayoutManager(vehiclesLayoutManager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateSources();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        spanCount = adjustSpanCount(newConfig);
        vehiclesLayoutManager.setSpanCount(spanCount);
    }

    private int adjustSpanCount(Configuration config) {
        return config.orientation ==
                Configuration.ORIENTATION_LANDSCAPE ?
                3 : 2;
    }
    private void updateSources() {
        AndroidNetworking.get(ChaskyGoService.VEHICLES_URL)
                .addHeaders("X-API-KEY", "123456")
                .setTag(TAG)
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if(response.getString("status").equalsIgnoreCase("ok")) {
                                vehicles = Vehicle.build(response.getJSONArray("vehicles"));
                                vehiclesAdapter.setVehicles(vehicles);
                                vehiclesAdapter.notifyDataSetChanged();
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
