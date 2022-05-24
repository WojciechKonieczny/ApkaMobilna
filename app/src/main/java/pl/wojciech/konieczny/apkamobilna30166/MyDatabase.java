package pl.wojciech.konieczny.apkamobilna30166;

//Database: It is an abstract class where we will be storing all our database entries which we can call Entities.

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Dao;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import pl.wojciech.konieczny.apkamobilna30166.MyCars.MyCarsDao;
import pl.wojciech.konieczny.apkamobilna30166.MyCars.MyCarsModal;

// adding annotation for our database entities and db version.
@androidx.room.Database(entities = {MyCarsModal.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    // instance of our database class
    private static MyDatabase instance;

    // abstract variable for Dao
    public abstract MyCarsDao Dao();

    // on below line we are getting instance for our database.
    public static synchronized MyDatabase getInstance(Context context) {

        // check if our instance of database is null
        if (instance == null) {

            // if instance is null - create new instance
            instance =
                    // for creating a instance for our database we are creating a database builder and passing our database class with our database name.
                    Room.databaseBuilder(context.getApplicationContext(),
                            MyDatabase.class, "apkamobilna_database")
                            // below line is use to add fall back to destructive migration to our database.
                            .fallbackToDestructiveMigration()
                            .addCallback(roomCallback)
                            // below line is to build our database.
                            .build();
        }

        return instance;
    }

    // below line is to create a callback for our room database.
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // this method is called when database is created and below line is to populate our data.
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    // we are creating an async task class to perform task in background.
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        PopulateDbAsyncTask(MyDatabase instance) {
            Dao dao = (Dao) instance.Dao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }

    @Override
    public void close() {
        super.close();
    }

}
