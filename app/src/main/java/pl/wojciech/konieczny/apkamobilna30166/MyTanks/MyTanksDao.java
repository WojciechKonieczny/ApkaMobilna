package pl.wojciech.konieczny.apkamobilna30166.MyTanks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface MyTanksDao {

    // add data to database
    @Insert
    void insert(MyTanksModal model);

    // update data in database
    @Update
    void update(MyTanksModal model);

    // delete specified record in database
    @Delete
    void delete(MyTanksModal model);

    // delete all cars from table
    @Query("DELETE FROM tanks_table")
    void deleteAllTanks();

    // read all cars from table
    @Query("SELECT * FROM tanks_table ORDER BY date ASC")
    LiveData<List<MyTanksModal>> getAllTanks();
}
