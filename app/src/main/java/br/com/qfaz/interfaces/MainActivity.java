package br.com.qfaz.interfaces;

import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import br.com.qfaz.LocalActivity;
import br.com.qfaz.R;
import br.com.qfaz.TicketActivity;
import br.com.qfaz.VisitaActivity;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open,R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        /*if(mToggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);*/
        switch (item.getItemId()) {
            case R.id.visitas:
                Intent intentVisita = new Intent(this, VisitaActivity.class);
                startActivity(intentVisita);
                return true;
            case R.id.locais:
                Intent intentLocais = new Intent(this, LocalActivity.class);
                startActivity(intentLocais);
                return true;
            case R.id.cadastro:
                Intent intentCadastro = new Intent(this, CadastroActivity.class);
                startActivity(intentCadastro);
                return true;
            case R.id.tickets:
                Intent intentConfiguracao = new Intent(this, TicketActivity.class);
                startActivity(intentConfiguracao);
                return true;
            case R.id.empresa:
                Intent intentEmpresa = new Intent(this, EmpresaActivity.class);
                startActivity(intentEmpresa);
                return true;
            case R.id.relatorios:
                Intent intentReltorio = new Intent(this, RelatorioActivity.class);
                startActivity(intentReltorio);
                return true;
            case R.id.sair:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
