package ca.georgiancollege.vehicledealership;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import ca.georgiancollege.vehicledealership.model.Vehicle;

public class _7_AddVehicleActivity extends MainActivity {

    Vehicle vehicle = new Vehicle();
    final String FILE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static final int upload_image = 1;

    Button add, image;
    EditText make, model, condition, engine, year, numberOfDoors, price, color;
    ImageView uploadedimage;



    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle_7);



        make = findViewById(R.id.addVehicleMakeTextView);
        model = findViewById(R.id.addVehicleModelTextView);
        condition = findViewById(R.id.addVehicleConditionTextView);
        engine = findViewById(R.id.addVehicleEngineTextView);
        year = findViewById(R.id.addVehicleYearTextView);
        numberOfDoors = findViewById(R.id.addVehicleNumberOfDoorsTextView);
        price = findViewById(R.id.addVehiclePriceTextView);
        color = findViewById(R.id.addVehicleColorTextView);

        uploadedimage = findViewById(R.id.uploadedimage);

        image = findViewById(R.id.addVehicleImageButton);
        add = findViewById(R.id.addVehicleButton);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String company = make.getText().toString();
                String carModel = model.getText().toString();
                String carCondition = condition.getText().toString();
                String carEngine = engine.getText().toString();
                String modelYear = year.getText().toString();
                String carNumberOfDoors = numberOfDoors.getText().toString();
                String carPrice = price.getText().toString();
                String carColor = color.getText().toString();
                String carImage = image.getText().toString();

                boolean validate = validateinfo(company,carModel,carCondition,carEngine,modelYear,carNumberOfDoors,carPrice,carColor,carImage);

                if (validate==true){
                    Toast.makeText(getApplicationContext(),"Vehicle Added Successfully",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getApplicationContext(),"Sorry Check The Information Again",Toast.LENGTH_SHORT).show();
                }

                try {
                    writeFileContents((String) vehicle.writeToFile());
                }
                catch (Exception e) {
                    Toast.makeText(getApplicationContext(),"Error Writing The Information To File",Toast.LENGTH_SHORT).show();
                }
            }
        });

        askForPermissions();

    }

    protected void writeFileContents(String content ) throws Exception{
        FileOutputStream fileOutputStream = openFileOutput(db_file, Context.MODE_APPEND);

        fileOutputStream.write(content.getBytes());
    }


    public void onUploadImageClick(View view){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent,"Choose Image"), upload_image);
    }

    private boolean validateinfo(String company, String carModel, String carCondition, String carEngine, String modelYear, String carNumberOfDoors, String carPrice, String carColor, String carImage) {
        if(company.length()==0){
            make.requestFocus();
            make.setError("Company Name Cannot Be Empty");
            return false;
        }
        else if(carModel.length()==0) {
            model.requestFocus();
            model.setError("Car Model Cannot Be Empty");
            return false;
        }
        else if (carCondition.length()==0) {
            condition.requestFocus();
            condition.setError("Car Condition Cannot Be Empty");
            return false;
        }
        else if (carEngine.length()==0) {
            engine.requestFocus();
            engine.setError("Engine Cannot Be Empty");
            return false;
        }
        else if (modelYear.length()==0) {
            year.requestFocus();
            year.setError("Car Model Year Cannot Be Empty");
            return false;
        }
        else if (!modelYear.matches("^(19|20){2}$")) {
            year.requestFocus();
            year.setError("Invalid Year");
            return false;
        }
        else if (carNumberOfDoors.length()==0) {
            numberOfDoors.requestFocus();
            numberOfDoors.setError("Number Of Doors Cannot Be Empty");
            return false;
        }
        else if (!carNumberOfDoors.matches("[2,4]")) {
            numberOfDoors.requestFocus();
            numberOfDoors.setError("Car Doors Can be 2 or 4");
            return false;
        }
        else if (carPrice.length()==0) {
            price.requestFocus();
            price.setError("Car Price Cannot Be Empty");
            return false;
        }
        else if (!carPrice.matches("^[$][0-9]{4,6}$")) {
            price.requestFocus();
            price.setError("Correct Price Format $12345");
            return false;
        }
        else if (carColor.length()==0) {
            color.requestFocus();
            color.setError("Car Color Cannot Be Empty");
            return false;
        }
        else {
            return true;
        }
    }

    public String randomStringGenerator(int limit) {
        int leftLimit = 48; // numeral '0'
        int rightLimit = 122; // letter 'z'

        Random random = new Random();
        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(limit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }
    @Override
    protected void onActivityResult(int response, int result, Intent info){

        super.onActivityResult(response, result, info);

        String filename = randomStringGenerator(20) + ".png";

        String file_path = FILE_DIR + filename;
        File file = new File(file_path);
        Bitmap photo;
        InputStream stream;
        uploadedimage.setImageURI(Uri.parse(file_path));


        if (response == upload_image) {

            Uri locationOfImage = info.getData();

            try {
                stream = getContentResolver().openInputStream(locationOfImage);
                photo = BitmapFactory.decodeStream(stream);
                uploadedimage.setImageBitmap(photo);


                file.createNewFile();
                FileOutputStream fos = new FileOutputStream(file);
                photo.compress(Bitmap.CompressFormat.PNG, 100, fos);
                fos.close();
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();

            }
        }
    }

    public void askForPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager()) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                startActivity(intent);
            }
        }
    }
}

