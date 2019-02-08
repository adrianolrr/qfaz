package br.com.qfaz;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

public class VisitaActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    Intent intentHome = new Intent(VisitaActivity.this, HomeActivity.class);
                    startActivity(intentHome);
                    return true;
                case R.id.navigation_dashboard:
                    Intent agendaIntent = new Intent(VisitaActivity.this, ComprovanteActivity.class);
                    startActivity(agendaIntent);
                    return true;
                case R.id.navigation_notifications:
                    Intent visitaIntent = new Intent(VisitaActivity.this, VisitaActivity.class);
                    startActivity(visitaIntent);
                    return true;
            }
            return false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visita);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }
}
