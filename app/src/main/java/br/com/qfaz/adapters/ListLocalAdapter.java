package br.com.qfaz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Local;

public class ListLocalAdapter extends ArrayAdapter<Local> {
    private final Context context;
    private List<Local> LocalList;

    TextView txtapelido, txtrazao, txtcidade, txtendereco;

    public ListLocalAdapter(Context context, List<Local> local) {
        super(context, R.layout.layout_locais, local);
        this.context = context;
        this.LocalList = local;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_locais, parent, false);
        txtendereco = (TextView) rowView.findViewById(R.id.textViewEndereco);
        txtcidade = (TextView) rowView.findViewById(R.id.textViewCidade);
        txtrazao = (TextView) rowView.findViewById( R.id.textViewRazao);
        txtapelido = (TextView) rowView.findViewById(R.id.textViewApelido);

        Local local = LocalList.get(position);

        txtendereco.setText("End:" + " " +local.getEndereco());
        txtcidade.setText("Cidade:" + " " + local.getCidade());
        txtrazao.setText("Segmento:" + " R$ " + local.getRazao());
        txtapelido.setText("Nome:" + " " +local.getNome());

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