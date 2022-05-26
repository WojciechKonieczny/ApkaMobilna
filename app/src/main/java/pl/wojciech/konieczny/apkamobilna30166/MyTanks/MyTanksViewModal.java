package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MyTanksViewModal extends AndroidViewModel {

    private MyTanksRepository repository;
    private LiveData<List<MyTanksModal>> allTanks;

    public MyTanksViewModal(Application application) {
        super(application);
        repository = new MyTanksRepository(application);
        allTanks = repository.getAllTanks();
    }

    // below method is use to insert the data to our repository.
    public void insert(MyTanksModal model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(MyTanksModal model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(MyTanksModal model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCars() {
        repository.deleteAllTanks();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<MyTanksModal>> getAllTanks() {
        return allTanks;
    }

}
