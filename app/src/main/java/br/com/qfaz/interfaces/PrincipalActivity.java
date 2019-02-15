package br.com.qfaz.interfaces;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import br.com.qfaz.R;

public class PrincipalActivity extends AppCompatActivity {

    private Toolbar mTopToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);

        mTopToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(mTopToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.visitas) {
            Intent intentVisita = new Intent(this, VisitaActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.locais) {
            Intent intentVisita = new Intent(this, LocalActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.tickets) {
            Intent intentVisita = new Intent(this, TicketActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }
        else if (id == R.id.cadastro) {
            Intent intentVisita = new Intent(this, CadastroActivity.class);
            startActivity(intentVisita);
            Toast.makeText(PrincipalActivity.this, "Action clicked", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
