package pl.wojciech.konieczny.apkamobilna30166.MyCars;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import androidx.room.*;

import java.util.List;

// annotation to DAO
@Dao
public interface MyCarsDao {

    // add data to database
    @Insert
    void insert(MyCarsModal model);

    // update data in database
    @Update
    void update(MyCarsModal model);

    // delete specified car in database
    @Delete
    void delete(MyCarsModal model);

    // delete all cars from table
    @Query("DELETE FROM cars_table")
    void deleteAllCars();

    // read all cars from table
    @Query("SELECT * FROM cars_table ORDER BY mark ASC")
    LiveData<List<MyCarsModal>> getAllCars();
}
