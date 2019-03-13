package br.com.qfaz.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import br.com.qfaz.R;
import br.com.qfaz.adapters.ListLocalAdapter;
import br.com.qfaz.adapters.LocalAdapter;
import br.com.qfaz.adapters.VisitaAdapter;
import br.com.qfaz.domain.model.Local;
import br.com.qfaz.domain.model.Visita;

public class AgendamentoVisitaFragments extends Fragment {
    //a list to store all the visitas
    List<Visita> visitaList;

    //the recyclerview
    RecyclerView recyclerView;

    EditText editTextVisita, editTextHorario, editTextData;

    String visita, horario, data;

    private FirebaseAuth mAuth;

    Button btnDatePicker, btnTimePicker;
    EditText txtDate, txtTime;
    private int mYear, mMonth, mDay, mHour, mMinute;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_agendamento_visita, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        String nome = null;
        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                nome = getArguments().getString("nome");
            }
        }

        editTextVisita = view.findViewById(R.id.editTextLocalVisita);

        editTextVisita.setText(nome);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        FirebaseFirestore db = FirebaseFirestore.getInstance();

        Query query = db.collection("visitas");

        btnDatePicker = view.findViewById(R.id.btn_date);
        btnTimePicker = view.findViewById(R.id.btn_time);
        txtDate = view.findViewById(R.id.in_date);
        txtTime = view.findViewById(R.id.in_time);

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                txtDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        btnTimePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Time
                final Calendar c = Calendar.getInstance();
                mHour = c.get(Calendar.HOUR_OF_DAY);
                mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {

                                txtTime.setText(hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
            }
        });


        //getting the recyclerview from xml
       /* recyclerView = (RecyclerView) getActivity().findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        //initializing the visitalist
        visitaList = new ArrayList<>();

        visitaList.add(
                new Visita(
                        "1",
                        null,
                        "05/02/2019",
                        "22:15"));


        //creating recyclerview adapter
        VisitaAdapter adapter = new VisitaAdapter(getContext(), visitaList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);*/

       /* Button btnBuscaLocais = view.findViewById(R.id.btnVisitaBuscarLocais);
        btnBuscaLocais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });*/


        Button btnSalvarVisita = view.findViewById(R.id.btnSalvarVisita);
        btnSalvarVisita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextVisita = v.findViewById(R.id.editTextLocalVisita);
                editTextHorario = v.findViewById(R.id.editTextHorarioVisita);
                editTextData = v.findViewById(R.id.editTextDataVisita);

                visita = editTextVisita.getText().toString();
                horario = editTextHorario.getText().toString();
                data = editTextData.getText().toString();

                FirebaseUser user = mAuth.getCurrentUser();

                Visita visita = new Visita(user.getUid(), null, data, horario, "");

                HashMap<String, Object> resultVisita = (HashMap<String, Object>) visita.toMap();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Add a new document with a generated ID
                db.collection(user.getUid())
                        .document("visitas")
                        .collection(data)
                        .document(horario)
                        .set(resultVisita)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Log.d("Sucesso", "DocumentSnapshot successfully written!");
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Log.w("Erro", "Error adding document", e);
                            }
                        });
            }
        });
    }
}
