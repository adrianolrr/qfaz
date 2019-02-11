package br.com.qfaz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import br.com.qfaz.domain.model.Local;

public class LocalActivity extends AppCompatActivity {

    String cnpj, nome, razao, cep, endereco, numero;

    EditText editTextCnpj, editTextRazao, editTextNome, editTextCep, editTextNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local);

        Button btnSalvarLocal = findViewById(R.id.btnSalvarLocal);
        btnSalvarLocal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextCnpj = findViewById(R.id.editTextLocalCnpj);
                editTextNome = findViewById(R.id.editTextLocalNome);
                editTextRazao = findViewById(R.id.editTextLocalRazao);
                editTextCep = findViewById(R.id.editTextLocalCep);
                editTextNumero = findViewById(R.id.editTextLocalNumero);

                cnpj = editTextCnpj.getText().toString();
                razao = editTextRazao.getText().toString();
                nome = editTextNome.getText().toString();
                cep = editTextCep.getText().toString();
                numero = editTextNumero.getText().toString();

                Local local = new Local(cnpj, razao, nome, cep, endereco, numero);

                HashMap<String, Object> resultLocal = (HashMap<String, Object>) local.toMap();

                //myRefEmpresa.setValue(resultLocal);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Add a new document with a generated ID
                db.collection("locais")
                        .document(cnpj)
                        .set(resultLocal)
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
