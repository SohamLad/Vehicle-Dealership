package ca.georgiancollege.vehicledealership;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
import java.util.List;

import ca.georgiancollege.vehicledealership.adaptor.RecyclerAdapter;
import ca.georgiancollege.vehicledealership.model.Vehicle;


public class _4_VehicleSpecificationActivity extends MainActivity{

    TextView make, model, condition, engine, year, numberofdoors, price, color, sellingdate;
    ImageView image;

    Button edit;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_specification_4);

        make = (TextView) findViewById(R.id.vehicleSpecificationMakeTextView);
        model = (TextView) findViewById(R.id.vehicleSpecificationModelTextView);
        condition = (TextView) findViewById(R.id.vehicleSpecificationConditionTextView);
        engine = (TextView) findViewById(R.id.vehicleSpecificationEngineTextView);
        year = (TextView) findViewById(R.id.vehicleSpecificationYearTextView);
        numberofdoors = (TextView) findViewById(R.id.vehicleSpecificationNumberOfDoorsTextView);
        price = (TextView) findViewById(R.id.vehicleSpecificationPriceTextView);
        color = (TextView) findViewById(R.id.vehicleSpecificationColorTextView);
        image = (ImageView) findViewById(R.id.vehicleSpecificationImageView);
        sellingdate = (TextView) findViewById(R.id.vehicleSpecificationSoldDateTextView);

        edit = findViewById(R.id.vehicleSpecificationEditButton);

      String data = getIntent().getStringExtra("VehicleObject");

        Vehicle object = new Gson().fromJson(data,Vehicle.class);
        make.setText(object.getMake());
        model.setText(object.getModel());
        condition.setText(object.getCondition());
        engine.setText(object.getEngine());
        year.setText(object.getYear());
        numberofdoors.setText(object.getNumberofdoors());
        price.setText(object.getPrice());
        color.setText(object.getColor());
       // image.setImageResource(object.);
        sellingdate.setText(object.getSolddate());
        Toast.makeText(getApplicationContext(),object.getMake(),Toast.LENGTH_SHORT).show();

        edit.setOnClickListener(v -> {
            String companyName = make.getText().toString();
            String carModel = model.getText().toString();
            String carCondition = condition.getText().toString();
            String carEngine = engine.getText().toString();
            String carYear = year.getText().toString();
            String numberOfDoors = numberofdoors.getText().toString();
            String carPrice = price.getText().toString();
            String carColor = color.getText().toString();
            //String carImage = image.getText().toString();
            String carSellingDate = sellingdate.getText().toString();

            Intent intent = new Intent(this,_8_VehicleModificationActivity.class);
            intent.putExtra("companyname", companyName);
            intent.putExtra("carmodel", carModel);
            intent.putExtra("carcondition", carCondition);
            intent.putExtra("carengine", carEngine);
            intent.putExtra("caryear", carYear);
            intent.putExtra("numberOfDoors", numberOfDoors);
            intent.putExtra("carPrice", carPrice);
            intent.putExtra("carColor", carColor);
            //intent.putExtra("carImage", carImage);
            intent.putExtra("carSellingDate", carSellingDate);
            startActivity(intent);
        });


    }
}
