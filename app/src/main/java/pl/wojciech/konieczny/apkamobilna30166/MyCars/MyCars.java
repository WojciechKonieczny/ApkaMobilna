package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyCars extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView carsRV;
    private static final int ADD_CAR_REQUEST = 1;
    private static final int EDIT_CAR_REQUEST = 2;
    private MyCarsViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_cars2);

        // initializing our variable for our recycler view and fab.
        carsRV = findViewById(R.id.idRVCars);
        FloatingActionButton fab = findViewById(R.id.idFABAddCar);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MyCars.this, MyCarsNew.class);
                startActivityForResult(intent, ADD_CAR_REQUEST);
            }
        });


        // setting layout manager to our adapter class.
        carsRV.setLayoutManager(new LinearLayoutManager(this));
        carsRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final MyCarsRVAdapter adapter = new MyCarsRVAdapter();

        // setting adapter class for recycler view.
        carsRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(MyCarsViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCars().observe(this, new Observer<List<MyCarsModal>>() {
            @Override
            public void onChanged(List<MyCarsModal> models) {
                // when the data is changed in our models we are adding that list to our adapter class.
                adapter.submitList(models);
            }
        });

        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getCarAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MyCars.this, "Car deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(carsRV);

        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new MyCarsRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyCarsModal model) {
                // after clicking on item of recycler view we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(MyCars.this, MyCarsNew.class);
                intent.putExtra(MyCarsNew.EXTRA_ID, model.getId());
                intent.putExtra(MyCarsNew.EXTRA_MARK, model.getMark());
                intent.putExtra(MyCarsNew.EXTRA_MODEL, model.getModel());
                intent.putExtra(MyCarsNew.EXTRA_FUEL, model.getFuelType());
                intent.putExtra(MyCarsNew.EXTRA_YEAR, model.getYear());
                intent.putExtra(MyCarsNew.EXTRA_ENGINE, model.getEngine());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_CAR_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CAR_REQUEST && resultCode == RESULT_OK) {
            String mark = data.getStringExtra(MyCarsNew.EXTRA_MARK);
            String model = data.getStringExtra(MyCarsNew.EXTRA_MODEL);
            String fuel = data.getStringExtra(MyCarsNew.EXTRA_FUEL);
            String year = data.getStringExtra(MyCarsNew.EXTRA_YEAR);
            String engine = data.getStringExtra(MyCarsNew.EXTRA_ENGINE);

            MyCarsModal car = new MyCarsModal(mark, model, fuel, year, engine);
            viewmodal.insert(car);

            Toast.makeText(this, "Car saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_CAR_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(MyCarsNew.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Car can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String mark = data.getStringExtra(MyCarsNew.EXTRA_MARK);
            String model = data.getStringExtra(MyCarsNew.EXTRA_MODEL);
            String fuel = data.getStringExtra(MyCarsNew.EXTRA_FUEL);
            String year = data.getStringExtra(MyCarsNew.EXTRA_YEAR);
            String engine = data.getStringExtra(MyCarsNew.EXTRA_ENGINE);

            MyCarsModal car = new MyCarsModal(mark, model, fuel, year, engine);
            car.setId(id);
            viewmodal.update(car);

            Toast.makeText(this, "Car updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Car not saved", Toast.LENGTH_SHORT).show();
        }
    }

}