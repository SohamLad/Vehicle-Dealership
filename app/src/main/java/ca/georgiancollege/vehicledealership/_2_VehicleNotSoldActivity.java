package ca.georgiancollege.vehicledealership;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import ca.georgiancollege.vehicledealership.adaptor.RecyclerAdapter;
import ca.georgiancollege.vehicledealership.model.Vehicle;

public class _2_VehicleNotSoldActivity extends MainActivity{

    RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    private List<Vehicle> viewItems = new ArrayList<>();

    private RecyclerView.Adapter mAdapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_not_sold_2);

        recyclerView = findViewById(R.id.vehicleNotSoldRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        addItemsFromJSON();

        mAdapter = new RecyclerAdapter(this, viewItems, new RecyclerAdapter.VehicleClickListener() {
            @Override
            public void onVehicleCLick(Vehicle vehicle) {
                Intent intent = new Intent(getApplicationContext(), _4_VehicleSpecificationActivity.class);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(mAdapter);

    }


    private void addItemsFromJSON() {
        try {

            String jsonDataString = readJSONDataFromFile();
            JSONArray jsonArray = new JSONArray(jsonDataString);

            for (int i=0; i<jsonArray.length(); ++i) {

                JSONObject itemObj = jsonArray.getJSONObject(i);

                String id = itemObj.getString("id");
                String make = itemObj.getString("make");
                String model = itemObj.getString("model");
                String condition = itemObj.getString("condition");
                String engine = itemObj.getString("engine");
                String year = itemObj.getString("year");
                String numberofdoors = itemObj.getString("numberofdoors");
                String price = itemObj.getString("price");
                String color = itemObj.getString("color");
                String image = itemObj.getString("image");
                String solddate = itemObj.getString("solddate");

                Vehicle vehicle = new Vehicle(id, make, model, condition, engine, year, numberofdoors, price, color, image, solddate);


                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (vehicle.getSolddate().equalsIgnoreCase("")) {
                    viewItems.add(vehicle);
                }
            }



        } catch (JSONException | IOException e) {
            Log.d("TAG", "addItemsFromJSON: ", e);
        }
    }

    private String readJSONDataFromFile() throws IOException {

        InputStream inputStream = null;
        StringBuilder builder = new StringBuilder();

        try {

            String jsonString = null;
            inputStream = getResources().openRawResource(R.raw.vehicle);
            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(inputStream, "UTF-8"));

            while ((jsonString = bufferedReader.readLine()) != null) {
                builder.append(jsonString);
            }

        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return new String(builder);
    }
}
