package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import pl.wojciech.konieczny.apkamobilna30166.MyCars.MyCarsNew;
import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyTanksNew extends AppCompatActivity {
    private EditText textViewAddDate, textViewAddMileage, textViewAddCostLiter, textViewAddCostSum, textViewAddStation;
    private Button buttonAddTank;

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_DATE = "EXTRA_DATE";
    public static final String EXTRA_MILEAGE = "EXTRA_MILEAGE";
    public static final String EXTRA_LITER_COST = "EXTRA_LITER_COST";
    public static final String EXTRA_SUM_COST = "EXTRA_SUM_COST";
    public static final String EXTRA_STATION = "EXTRA_STATION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tanks_new);

        // initializing our variables for each view.
        textViewAddDate = findViewById(R.id.textView_tank_add_date);
        textViewAddMileage = findViewById(R.id.textView_tank_add_mileage);
        textViewAddCostLiter = findViewById(R.id.textView_tank_add_cost_liter);
        textViewAddCostSum = findViewById(R.id.textView_tank_add_cost_sum);
        textViewAddStation = findViewById(R.id.textView_tank_add_station);
        buttonAddTank = findViewById(R.id.button_tank_add);

        // below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are setting values to our edit text fields.
            textViewAddDate.setText(intent.getStringExtra(EXTRA_DATE));
            textViewAddMileage.setText(intent.getStringExtra(EXTRA_MILEAGE));
            textViewAddCostLiter.setText(intent.getStringExtra(EXTRA_LITER_COST));
            textViewAddCostSum.setText(intent.getStringExtra(EXTRA_SUM_COST));
            textViewAddStation.setText(intent.getStringExtra(EXTRA_STATION));
        }

        // adding on click listener for our save button.
        buttonAddTank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if the text fields are empty or not.
                String date = textViewAddDate.getText().toString();
                String mileage =  textViewAddMileage.getText().toString();
                String costPerLiter = textViewAddCostLiter.getText().toString();
                String costSum = textViewAddCostSum.getText().toString();
                String gasStation = textViewAddStation.getText().toString();


                if (date.isEmpty() || mileage.isEmpty() || costPerLiter.isEmpty() || costSum.isEmpty() || gasStation.isEmpty()) {
                    Toast.makeText(MyTanksNew.this, "Please enter the valid tank details.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // calling a method to save our course.
                    float litersFuel = Float.parseFloat(costSum) / Float.parseFloat(costPerLiter);
                    Log.d("WK", String.valueOf(litersFuel));
                    saveTank(date, mileage, litersFuel, costPerLiter, costSum, gasStation);
                }
            }
        });
    }

    private void saveTank(String date, String mileage, float litersFuel, String costPerLiter, String costSum, String gasStation) {
        // inside this method we are passing all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our tank detail.
        data.putExtra(EXTRA_DATE, date);
        data.putExtra(EXTRA_MILEAGE, mileage);
        data.putExtra(EXTRA_LITER_COST, costPerLiter);
        data.putExtra(EXTRA_SUM_COST, costSum);
        data.putExtra(EXTRA_STATION, gasStation);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Tank has been saved to Database. ", Toast.LENGTH_SHORT).show();
//        finish();
    }
}