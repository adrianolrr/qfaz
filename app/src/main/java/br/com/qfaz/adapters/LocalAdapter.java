package br.com.qfaz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import androidx.annotation.NonNull;
import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;

public class LocalAdapter extends ArrayAdapter<Local> {
    private final Context context;
    private List<Local> LocalList;

    TextView txtramo, txtdatahora, txtvalor, txtapelido;


    public LocalAdapter( Context context, List<Local> locais) {
        super(context, R.layout.layout_locais, locais);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_locais, parent, false);
        txtramo = (TextView) rowView.findViewById(R.id.textViewRamo);
        txtdatahora = (TextView) rowView.findViewById(R.id.textViewDataHora);
        txtvalor = (TextView) rowView.findViewById(R.id.textViewValorNF);
        txtapelido = (TextView) rowView.findViewById(R.id.textViewApelido);

        Local local = LocalList.get(position);

       /* txtramo.setText("Ramo:" + " " +local.getRamo());
        txtdatahora.setText("Dia:" + " " + local.getDatahora());
        txtvalor.setText("Valor:" + " R$ " + local.getValor_nf().replace(".", ","));
        txtapelido.setText("Apelido:" + " " +local.getApelido());*/

        return rowView;
    }

    @Override
    public int getCount() {
        return LocalList.size();
    }

    @Override
    public Local getItem(int position) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return LocalList.get(position);
    }
}
