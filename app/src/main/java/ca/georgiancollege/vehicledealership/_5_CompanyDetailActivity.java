package ca.georgiancollege.vehicledealership;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class _5_CompanyDetailActivity extends MainActivity {

    TextView companyDetailsLine1, companyDetailsLine2, companyDetailsLine3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_details_5);


        companyDetailsLine1 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine1);
        companyDetailsLine2 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine2);
        companyDetailsLine3 = (TextView) findViewById(R.id.textCompanyDetailsAddressLine3);

        Intent intent = getIntent();
        String companydetailsline1 = intent.getStringExtra("addressline1");
        String companydetailsline2 = intent.getStringExtra("addressline2");
        String companydetailsline3 = intent.getStringExtra("addressline3");

        companyDetailsLine1.setText(companydetailsline1);
        companyDetailsLine2.setText(companydetailsline2);
        companyDetailsLine3.setText(companydetailsline3);

    }
}
