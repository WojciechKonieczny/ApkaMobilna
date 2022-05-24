package pl.wojciech.konieczny.apkamobilna30166.Cars;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import pl.wojciech.konieczny.apkamobilna30166.R;

public class MyCars extends AppCompatActivity {

    private SQLiteOpenHelper carsSqLiteOpenHelper;
    private SQLiteDatabase db;
    private Cursor cursor;
    private ListView listViewCars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView( R.layout.activity_my_cars );

        // dodanie przycisku wstecz do nawigacji
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled( true );

        listViewCars = (ListView) findViewById(R.id.listViewCars);

        // podpiecie bazy danych do aktywnosci
        try {
            carsSqLiteOpenHelper = new CarsSQLiteOpenHelper( this );
            db = carsSqLiteOpenHelper.getWritableDatabase();
        } catch( SQLException e ) {
            Toast toastError = Toast.makeText( this, "Baza danych jest niedostępna", Toast.LENGTH_LONG );
            toastError.show();
            finish();
        }

        // stworzenie adaptera do formularza edycji / usuniecia
        // Adapter jest odpowiedzialny za pobieranie danych z zestawu i generowanie obiektów View opartych o te dane. Wygenerowane obiekty View są następnie wykorzystywane do zamieszczania widoku adaptera przywiązanego do adaptera.
        AdapterView.OnItemClickListener itemClickListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> listViewOrganizacje, View view, int position, long id) {

                Intent intent = new Intent(MyCars.this, MyCarsAktualizacja.class);
                intent.putExtra(MyCarsAktualizacja.EXTRA_CAR_ID, (int) id);
                startActivity(intent);
            }
        };
        listViewCars.setOnItemClickListener(itemClickListener);

        // przycisk dodawania
        FloatingActionButton button = findViewById(R.id.fab);
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intentButton = new Intent( MyCars.this, MyCarsDopisz.class );
//                startActivity( intentButton );
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        return true;
    }
}