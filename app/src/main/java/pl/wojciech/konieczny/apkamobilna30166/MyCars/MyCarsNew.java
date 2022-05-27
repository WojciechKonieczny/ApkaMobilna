package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyCarsNew extends AppCompatActivity {

    private EditText textViewAddMark, textViewAddModel, textViewAddFuel, textViewAddYear, textViewAddEngine;
    private Button buttonAddCar;

    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_MARK = "EXTRA_MARK";
    public static final String EXTRA_MODEL = "EXTRA_MODEL";
    public static final String EXTRA_FUEL = "EXTRA_FUEL";
    public static final String EXTRA_YEAR = "EXTRA_YEAR";
    public static final String EXTRA_ENGINE = "EXTRA_ENGINE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars_new);

        // initializing our variables for each view.
        textViewAddMark = findViewById(R.id.textView_car_add_mark);
        textViewAddModel = findViewById(R.id.textView_car_add_model);
        textViewAddFuel = findViewById(R.id.textView_car_add_fuel);
        textViewAddYear = findViewById(R.id.textView_car_add_year);
        textViewAddEngine = findViewById(R.id.textView_car_add_engine);
        buttonAddCar = findViewById(R.id.button_car_add);

        // below line is to get intent as we are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are setting values to our edit text fields.
            textViewAddMark.setText(intent.getStringExtra(EXTRA_MARK));
            textViewAddModel.setText(intent.getStringExtra(EXTRA_MODEL));
            textViewAddFuel.setText(intent.getStringExtra(EXTRA_FUEL));
            textViewAddYear.setText(intent.getStringExtra(EXTRA_YEAR));
            textViewAddEngine.setText(intent.getStringExtra(EXTRA_ENGINE));
        }

        // adding on click listener for our save button.
        buttonAddCar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getting text value from edittext and validating if the text fields are empty or not.
                String mark = textViewAddMark.getText().toString();
                String model = textViewAddModel.getText().toString();
                String fuel = textViewAddFuel.getText().toString();
                String year = textViewAddYear.getText().toString();
                String engine = textViewAddEngine.getText().toString();

                if (mark.isEmpty() || model.isEmpty() || fuel.isEmpty() || year.isEmpty() || engine.isEmpty() || Integer.parseInt(year.toString()) <= 0
                        || Calendar.getInstance().get(Calendar.YEAR) < Integer.parseInt(year.toString()) || Integer.parseInt(engine.toString()) <= 0 ) {
                    Toast.makeText(MyCarsNew.this, "Please enter the valid cars details.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    // calling a method to save our course.
                    saveCar(mark, model, fuel, year, engine);
                    finish();
                }
            }
        });
    }

    private void saveCar(String mark, String model, String fuel, String year, String engine) {
        // inside this method we are passing all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_MARK, mark);
        data.putExtra(EXTRA_MODEL, model);
        data.putExtra(EXTRA_FUEL, fuel);
        data.putExtra(EXTRA_YEAR, year);
        data.putExtra(EXTRA_ENGINE, engine);


        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        Toast.makeText(this, "Car has been saved to Database. ", Toast.LENGTH_SHORT).show();
//        finish();
    }
}