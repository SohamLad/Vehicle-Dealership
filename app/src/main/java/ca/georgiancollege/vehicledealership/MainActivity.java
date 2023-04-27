package ca.georgiancollege.vehicledealership;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.io.FileOutputStream;


public class MainActivity extends AppCompatActivity {

    final String db_file = "vehicledata";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){

        Intent intent;

        switch (item.getItemId()){

            case R.id.mainMenuAllVehicle:
                intent = new Intent(getApplicationContext(), _1_AllVehicleActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuVehicleNotSold:
                intent = new Intent(getApplicationContext(), _2_VehicleNotSoldActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuSoldVehicle:
                intent = new Intent(getApplicationContext(), _3_SoldVehicleActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuVehicleSpecifications:
                intent = new Intent(getApplicationContext(), _4_VehicleSpecificationActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuCompanyDetails:
                intent = new Intent(getApplicationContext(), _5_CompanyDetailActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuCompanyDetailsModifications:
                intent = new Intent(getApplicationContext(), _6_CompanyDetailModificationActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuAddVehicle:
                intent = new Intent(getApplicationContext(), _7_AddVehicleActivity.class);
                startActivity(intent);
                break;
            case R.id.mainMenuVehicleModification:
                intent = new Intent(getApplicationContext(), _8_VehicleModificationActivity.class);
                startActivity(intent);
                break;
            default:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
        }

        return true;
    }

    protected void writeFileContents(String content) throws Exception {

        FileOutputStream fileOutputStream = openFileOutput(db_file, Context.MODE_APPEND);

        fileOutputStream.write(content.getBytes());
    }

}