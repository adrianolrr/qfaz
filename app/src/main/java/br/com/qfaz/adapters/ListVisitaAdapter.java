package br.com.qfaz.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Visita;

public class ListVisitaAdapter extends ArrayAdapter<Visita> {
    private final Context context;
    private List<Visita> visitaList;

    TextView textViewLocal, textViewData, textViewHorario;

    public ListVisitaAdapter(Context context, List<Visita> visita) {
        super(context, R.layout.layout_visitas, visita);
        this.context = context;
        this.visitaList = visita;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_visitas, parent, false);
        textViewLocal = (TextView) rowView.findViewById(R.id.textViewLocal);
        textViewData = (TextView) rowView.findViewById(R.id.textViewData);
        textViewHorario = (TextView) rowView.findViewById(R.id.textViewHorario);

        Visita visitas = visitaList.get(position);

        textViewLocal.setText("ID:" + " " +visitas.getUid());
        textViewData.setText("Data:" + " " + visitas.getData());
        textViewHorario.setText("Horario:" +  visitas.getHorario());

        return rowView;
    }

    @Override
    public int getCount() {
        return visitaList.size();
    }

    @Override
    public Visita getItem(int position) {
        // POSITION_NONE makes it possible to reload the PagerAdapter
        return visitaList.get(position);
    }
}