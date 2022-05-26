package pl.wojciech.konieczny.apkamobilna30166;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import pl.wojciech.konieczny.apkamobilna30166.MyCars.MyCars;
import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanks;
import pl.wojciech.konieczny.apkamobilna30166.MyTanks.MyTanksNew;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageViewLogoAplikacji;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mainToolbar);
        // zadeklarowanie szuflady nawigacyjnej
        // zadeklarowanie stworzonego toolbar_main

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, mainToolbar, R.string.nav_open_hamburger, R.string.nav_close_hamburger);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch( item.getItemId() ) {

            // identyfikatory przedmiotów z /res/menu/menu_main.xml
            case R.id.item_cars:
                Toast.makeText(this, "My cars", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch( id ) {
            case R.id.item_cars:
                Toast.makeText(this, R.string.nav_cars, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(this, MyCars.class);
                startActivity(intent);

                return true;
            case R.id.item_tanks:
                Toast.makeText(this, R.string.nav_tanks, Toast.LENGTH_LONG).show();

                Intent intentTank = new Intent(this, MyTanks.class);
                startActivity(intentTank);

                return true;
        }

        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}