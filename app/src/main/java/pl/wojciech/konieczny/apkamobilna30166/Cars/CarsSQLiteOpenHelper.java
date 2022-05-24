package pl.wojciech.konieczny.apkamobilna30166.Cars;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class CarsSQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "apkawojtek";
    private static final String TABLE_NAME = "cars";
    private static final int DB_VERSION = 1;

    public CarsSQLiteOpenHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        updateMyDatabase( db, 0, DB_VERSION );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        updateMyDatabase( db, oldVersion, newVersion );
    }

    private static void wstawPojazd ( SQLiteDatabase db, String marka, String model, int rok, String paliwo, int moc ) {
        ContentValues obiektValues = new ContentValues();
        obiektValues.put( "MARKA", marka );
        obiektValues.put( "MODEL", model );
        obiektValues.put( "ROK", rok );
        obiektValues.put( "MOC SILNIKA", moc);
        obiektValues.put( "PALIWO", paliwo );
        db.insert( TABLE_NAME, null, obiektValues );
    }

    public void updateMyDatabase( SQLiteDatabase db, int oldVersion, int newVersion ) {

        if( oldVersion < 1 ) {
            // utworzenie bazy
            db.execSQL("CREATE TABLE ORGANIZACJA (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "MARKA TEXT, " +
                    "MODEL TEXT," +
                    "ROK INT," +
                    "PALIWO TEXT," +
                    " MOC INT);");

            // wstawienie rekordow
            wstawPojazd(db, "Citroen", "C4", 2006, "PB95", 110);
            wstawPojazd(db, "Mercedes", "AMG", 2020, "PB95", 400);
        }
    }
}
