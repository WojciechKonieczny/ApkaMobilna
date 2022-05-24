package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyCarsViewModal extends AndroidViewModel {

    private MyCarsRepository repository;
    private LiveData<List<MyCarsModal>> allCars;

    public MyCarsViewModal(@NonNull Application application) {
        super(application);
        repository = new MyCarsRepository(application);
        allCars = repository.getAllCars();
    }

    // below method is use to insert the data to our repository.
    public void insert(MyCarsModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(MyCarsModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(MyCarsModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCars() {
        repository.deleteAllCars();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<MyCarsModal>> getAllCars() {
        return allCars;
    }

}
