package ca.georgiancollege.vehicledealership.adaptor;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.List;

import ca.georgiancollege.vehicledealership.R;
import ca.georgiancollege.vehicledealership._4_VehicleSpecificationActivity;
import ca.georgiancollege.vehicledealership.model.Vehicle;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Vehicle> listRecyclerItem;
    private VehicleClickListener vehicleClickListener;

    public RecyclerAdapter(Context context, List<Vehicle> listRecyclerItem, VehicleClickListener vehicleClickListener) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
        this.vehicleClickListener = vehicleClickListener;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView make;
        private TextView model;
        private TextView condition;
        private TextView engine;
        private TextView year;
        private TextView numberofdoors;
        private TextView price;
        private TextView color;
        private ImageView image;
        private TextView solddate;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            make = (TextView) itemView.findViewById(R.id.make);
            model = (TextView) itemView.findViewById(R.id.model);
            condition = (TextView) itemView.findViewById(R.id.condition);
            engine = (TextView) itemView.findViewById(R.id.enginecylinder);
            year = (TextView) itemView.findViewById(R.id.year);
            numberofdoors = (TextView) itemView.findViewById(R.id.numberofdoors);
            price = (TextView) itemView.findViewById(R.id.price);
            color = (TextView) itemView.findViewById(R.id.color);
            image = (ImageView) itemView.findViewById(R.id.image);
            solddate = (TextView) itemView.findViewById(R.id.solddate);


            itemView.setOnClickListener(this::onClick);
        }


        public void onClick(View v) {
            int position = getAdapterPosition();
            Intent intent = new Intent(context, _4_VehicleSpecificationActivity.class);
            Gson gson = new Gson();
            String json = gson.toJson(listRecyclerItem.get(position));
            intent.putExtra("VehicleObject",json);
            context.startActivity(intent);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

                default:

                    View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                            R.layout.list_item, viewGroup, false);

                    return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);
        switch (viewType) {
            case TYPE:
                default:

                    ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                    Vehicle vehicle = (Vehicle) listRecyclerItem.get(i);



                    itemViewHolder.make.setText(vehicle.getMake());
                    itemViewHolder.model.setText(vehicle.getModel());
                    itemViewHolder.condition.setText(vehicle.getCondition());
                    itemViewHolder.engine.setText(vehicle.getEngine());
                    itemViewHolder.year.setText(vehicle.getYear());
                    itemViewHolder.numberofdoors.setText(vehicle.getNumberofdoors());
                    itemViewHolder.price.setText(vehicle.getPrice());
                    itemViewHolder.color.setText(vehicle.getColor());
                    //itemViewHolder.image.setImageResource(Integer.parseInt(value));
                    itemViewHolder.solddate.setText(vehicle.getSolddate());

        }

    }


    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }

    public interface VehicleClickListener{
        void onVehicleCLick(Vehicle vehicle);
    }
}
