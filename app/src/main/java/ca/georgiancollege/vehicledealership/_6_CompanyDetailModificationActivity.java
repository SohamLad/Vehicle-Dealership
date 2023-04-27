package ca.georgiancollege.vehicledealership;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;


public class _6_CompanyDetailModificationActivity extends MainActivity{

    final String FILE_DIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/";
    public static final int upload_image = 1;
    TextView companyDetailsLine1, companyDetailsLine2, companyDetailsLine3;
    EditText companyName, addressLine1, addressLine2, addressLine3;
    ActionBar title;
    Button imageUpload, submit;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_modification_6);

        companyName = (EditText) findViewById(R.id.comanyModificationName);
        addressLine1 = (EditText)findViewById(R.id.textCompanyDetailsModificationAddressLine1);
        addressLine2 = (EditText)findViewById(R.id.textCompanyDetailsModificationAddressLine2);
        addressLine3 = (EditText)findViewById(R.id.textCompanyDetailsModificationAddressLine3);
        companyDetailsLine1 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine1);
        companyDetailsLine2 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine2);
        companyDetailsLine3 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine3);


        imageUpload = findViewById(R.id.comanyDetailsModificationImageButton);
        submit = findViewById(R.id.comanyDetailsModificationSubmitButton);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String companyname = companyName.getText().toString();
                String addressline1 = addressLine1.getText().toString();
                String addressline2 = addressLine2.getText().toString();
                String addressline3 = addressLine3.getText().toString();


                if (title != null){
                    title.setTitle(companyname);
                }

                Intent intent = new Intent(getApplicationContext(), _5_CompanyDetailActivity.class);
                intent.putExtra("addressline1", addressline1);
                intent.putExtra("addressline2", addressline2);
                intent.putExtra("addressline3", addressline3);
                startActivity(intent);
            }
        });

    }



    public void onUploadImageClick(View view){

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");

        startActivityForResult(Intent.createChooser(intent,"Choose Image"), upload_image);

    }

}


//We need to take file uploader controller