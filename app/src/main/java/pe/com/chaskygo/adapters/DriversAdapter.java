package pe.com.chaskygo.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import pe.com.chaskygo.ChaskyGoApp;
import pe.com.chaskygo.R;
import pe.com.chaskygo.activities.DeliveriesActivity;
import pe.com.chaskygo.models.Driver;

/**
 * Created by dsullon on 5/12/2016.
 */

public class DriversAdapter extends RecyclerView.Adapter<DriversAdapter.ViewHolder> {
    List<Driver> drivers;

    public void setDrivers(List<Driver> drivers) { this.drivers = drivers; }

    @Override
    public DriversAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.card_driver, parent,false);
        DriversAdapter.ViewHolder viewHolder = new DriversAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(DriversAdapter.ViewHolder holder, int position) {
        holder.nameTextView.setText(drivers.get(position).getLastName() + ", " + drivers.get(position).getFirstName());
        holder.driverCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChaskyGoApp.getInstance().getService().setCurrentDriver(drivers.get(position));
                Intent intent = new Intent(view.getContext(), DeliveriesActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return drivers.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView driverCardView;
        TextView nameTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            driverCardView = (CardView) itemView.findViewById(R.id.driverCardView);
            nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
        }
    }
}
