package ca.georgiancollege.vehicledealership;

import static android.view.View.*;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import ca.georgiancollege.vehicledealership.model.Vehicle;

public class _8_VehicleModificationActivity extends MainActivity{
    TextView make, model, condition, engine, year, numberofdoors,  color;
    EditText price, sellingdate;
    ImageView image;
    Button save;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle_modification_8);

        make = (TextView) findViewById(R.id.editVehicleMakeTextView);
        model = (TextView) findViewById(R.id.editVehicleModelTextView);
        condition = (TextView) findViewById(R.id.editVehicleConditionTextView);
        engine = (TextView) findViewById(R.id.editVehicleEngineTextView);
        year = (TextView) findViewById(R.id.editVehicleYearTextView);
        numberofdoors = (TextView) findViewById(R.id.editVehicleNumberOfDoorsTextView);
        price = (EditText) findViewById(R.id.editVehiclePriceTextView);
        color = (TextView) findViewById(R.id.editVehicleColorTextView);
        image = (ImageView) findViewById(R.id.editUploadedImage);
        sellingdate = (EditText) findViewById(R.id.editVehicleSoldDateTextView);

        save = findViewById(R.id.editVehicleButton);

        Intent intent = getIntent();
        String companyName = intent.getStringExtra("companymake");
        String carModel = intent.getStringExtra("carmodel");
        String carCondition = intent.getStringExtra("carcondition");
        String carEngine = intent.getStringExtra("carengine");
        String carYear = intent.getStringExtra("caryear");
        String numberOfDoors = intent.getStringExtra("numberOfDoors");
        String carPrice = intent.getStringExtra("carPrice");
        String carColor = intent.getStringExtra("carColor");
        //String carImage = intent.getStringExtra("carImage");
        String carSellingDate = intent.getStringExtra("carSellingDate");

        make.setText(companyName);
        model.setText(carModel);
        condition.setText(carCondition);
        engine.setText(carEngine);
        year.setText(carYear);
        numberofdoors.setText(numberOfDoors);
        price.setText(carPrice);
        color.setText(carColor);
        //image.setImageResource(Integer.parseInt(carImage));
        sellingdate.setText(carSellingDate);


        save.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String carPrice = price.getText().toString();
                String carSellingDate = sellingdate.getText().toString();


                Intent intent = new Intent(getApplicationContext(), _1_AllVehicleActivity.class);
                intent.putExtra("price", carPrice);
                intent.putExtra("sellingdate", carSellingDate);
                startActivity(intent);

                boolean validate = validateinfo(carPrice,carSellingDate);

                if (validate==true){

                    Toast.makeText(getApplicationContext(),"Vehicle Modification Successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check The Information Again",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean validateinfo(String carPrice, String carSellingDate) {

        if (carPrice.length()==0) {
            price.requestFocus();
            price.setError("Car Price Cannot Be Empty");
            return false;
        }
        else if (!carPrice.matches("^[$][0-9]{4,6}$")) {
            price.requestFocus();
            price.setError("Correct Price Format $12345");
            return false;
        }
        else if (carSellingDate.length()==0) {
            sellingdate.requestFocus();
            sellingdate.setError("Selling Date Cannot Be Empty");
            return false;
        }
        else if(!carSellingDate.matches("^((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])$")){
            sellingdate.requestFocus();
            sellingdate.setError("Correct Date Format is YYYY-MM-DD");
            return false;
        }
        else {
            return true;
        }
    }
}
