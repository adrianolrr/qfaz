package br.com.qfaz;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.qfaz.adapters.PerfilAdapter;
import br.com.qfaz.domain.model.Perfil;

public class UsuarioActivity extends AppCompatActivity {


    //a list to store all the perfils
    List<Perfil> perfilList;

    //the recyclerview
    RecyclerView recyclerView;

    EditText editTextNome, editTextEmail, editTextTelefone, editTextSenha, editTextCodigoEmpresa, editTextStatus;

    String email, nome, telefone, senha, status, codigoempresa;

    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Button btnSalvarPerfil = findViewById(R.id.btnSalvarPerfil);
        btnSalvarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                editTextNome = findViewById(R.id.editTextNomePerfil);
                editTextEmail = findViewById(R.id.editTextEmailPerfil);
                editTextTelefone = findViewById(R.id.editTextTelefonePerfil);
                editTextSenha = findViewById(R.id.editTextSenhaPerfil);
                editTextCodigoEmpresa = findViewById(R.id.editTextCodigoEmpresa);

                telefone = editTextTelefone.getText().toString();
                email = editTextEmail.getText().toString();
                nome = editTextNome.getText().toString();
                senha = editTextSenha.getText().toString();
                codigoempresa = editTextCodigoEmpresa.getText().toString();

                FirebaseUser user = mAuth.getCurrentUser();

                Perfil perfil = new Perfil(codigoempresa, nome, email, telefone, status);

                HashMap<String, Object> resultPerfil = (HashMap<String, Object>) perfil.toMap();

                //myRefEmpresa.setValue(resultPerfil);

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                // Add a new document with a generated ID
                db.collection("usuarios")
                        .document(codigoempresa)
                        .collection(email)
                        .document("cadastrousuario")
                        .set(resultPerfil)
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


        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the perfillist
        perfilList = new ArrayList<>();

        perfilList.add(
                new Perfil(
                        "XXX",
                        "Adriano Luiz Ramos Rosa",
                        "adriano.lrr@gmail.com",
                        "15981754694",
                        "Liberado"));

        perfilList.add(
                new Perfil(
                        "XXX",
                        "Adriano Luiz Ramos Rosa",
                        "adriano.lrr@gmail.com",
                        "15981754694",
                        "Liberado"));

        perfilList.add(
                new Perfil(
                        "XXX",
                        "Adriano Luiz Ramos Rosa",
                        "adriano.lrr@gmail.com",
                        "15981754694",
                        "Bloqueado"));

        perfilList.add(
                new Perfil(
                        "XXX",
                        "Adriano Luiz Ramos Rosa",
                        "adriano.lrr@gmail.com",
                        "15981754694",
                        "Bloqueado"));


        //creating recyclerview adapter
        PerfilAdapter adapter = new PerfilAdapter(this, perfilList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);
    }
}
