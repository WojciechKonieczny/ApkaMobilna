package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

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

import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksModal;
import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksNew;
import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksRVAdapter;
import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksViewModal;
import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyTanks extends AppCompatActivity {

    // creating a variables for our recycler view.
    private RecyclerView tanksRV;
    private static final int ADD_TANK_REQUEST = 1;
    private static final int EDIT_TANK_REQUEST = 2;
    private MyTanksViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_tanks);

        // initializing our variable for our recycler view and fab.
        tanksRV = findViewById(R.id.idRVTanks);
        FloatingActionButton fab = findViewById(R.id.idFABAddTank);

        // adding on click listener for floating action button.
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // starting a new activity for adding a new course
                // and passing a constant value in it.
                Intent intent = new Intent(MyTanks.this, MyTanksNew.class);
                startActivityForResult(intent, ADD_TANK_REQUEST);
            }
        });


        // setting layout manager to our adapter class.
        tanksRV.setLayoutManager(new LinearLayoutManager(this));
        tanksRV.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final MyTanksRVAdapter adapter = new MyTanksRVAdapter();

        // setting adapter class for recycler view.
        tanksRV.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(MyTanksViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllTanks().observe(this, new Observer<List<MyTanksModal>>() {
            @Override
            public void onChanged(List<MyTanksModal> models) {
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
                viewmodal.delete(adapter.getTankAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MyTanks.this, "Tank deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(tanksRV);

        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new MyTanksRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(MyTanksModal model) {
                // after clicking on item of recycler view we are opening a new activity and passing a data to our activity.
                Intent intent = new Intent(MyTanks.this, MyTanksNew.class);
                intent.putExtra(MyTanksNew.EXTRA_ID, model.getId());
                intent.putExtra(MyTanksNew.EXTRA_STATION, model.getGasStation());
                intent.putExtra(MyTanksNew.EXTRA_DATE, model.getDate());
                intent.putExtra(MyTanksNew.EXTRA_MILEAGE, model.getMileage());
                intent.putExtra(MyTanksNew.EXTRA_SUM_COST, model.getCostSum());
                intent.putExtra(MyTanksNew.EXTRA_LITER_COST, model.getCostPerLiter());
                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_TANK_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_TANK_REQUEST && resultCode == RESULT_OK) {
            String date = data.getStringExtra(MyTanksNew.EXTRA_DATE);
            String mileage = data.getStringExtra(MyTanksNew.EXTRA_MILEAGE);
            String costPerLiter = data.getStringExtra(MyTanksNew.EXTRA_LITER_COST);
            String costSum = data.getStringExtra(MyTanksNew.EXTRA_SUM_COST);
            String gasStations = data.getStringExtra(MyTanksNew.EXTRA_STATION);
            float litersFuel = Float.parseFloat(costSum) / Float.parseFloat(costPerLiter);

//            MyTanksModal tank = new MyTanksModal("2022", 111, 1f, 1f, 1f, "ORLEN");

            MyTanksModal tank = new MyTanksModal(date, mileage,litersFuel, costPerLiter, costSum, gasStations );
            viewmodal.insert(tank);

            Toast.makeText(this, "Tank saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_TANK_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(MyTanksNew.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Tank can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = data.getStringExtra(MyTanksNew.EXTRA_DATE);
            String mileage = data.getStringExtra(MyTanksNew.EXTRA_MILEAGE);
            String costPerLiter = data.getStringExtra(MyTanksNew.EXTRA_LITER_COST);
            String costSum = data.getStringExtra(MyTanksNew.EXTRA_SUM_COST);
            String gasStations = data.getStringExtra(MyTanksNew.EXTRA_STATION);
            float litersFuel = Float.parseFloat(costSum) / Float.parseFloat(costPerLiter);

            MyTanksModal tank = new MyTanksModal(date, mileage,litersFuel, costPerLiter, costSum, gasStations );

            tank.setId(id);
            viewmodal.update(tank);

            Toast.makeText(this, "Tank updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Tank not saved", Toast.LENGTH_SHORT).show();
        }
    }

}