package br.com.qfaz.interfaces;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

import br.com.qfaz.R;
import br.com.qfaz.domain.model.Empresa;
import br.com.qfaz.domain.model.Usuario;

public class SignupActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;

    String email, password, cnpj, nome, razao, cep, endereco, numero, codigoempresa;

    EditText editTextEmail, editTextPassword, editTextCnpj, editTextRazao, editTextNome, editTextCep, editTextNumero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mAuth = FirebaseAuth.getInstance();


        Button btnCadastrar = findViewById(R.id.btnSignupCadastrar);
        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarEmpresa();
            }
        });


    }

    private void cadastrarEmpresa() {



        editTextEmail = findViewById(R.id.editTextSignupEmail);
        editTextPassword = findViewById(R.id.editTextSignupSenha);
        editTextCnpj = findViewById(R.id.editTextSignupCnpj);
        editTextNome = findViewById(R.id.editTextSignupNome);
        //editTextRazao = findViewById(R.id.editTextSignupRazao);
        editTextCep = findViewById(R.id.editTextSignupCep);
        editTextNumero = findViewById(R.id.editTextSignupNumero);

        email = editTextEmail.getText().toString();
        password = editTextPassword.getText().toString();
        //cnpj = editTextCnpj.getText().toString();
        //razao = editTextRazao.getText().toString();
        //nome = editTextNome.getText().toString();
        //cep = editTextCep.getText().toString();
        //numero = editTextNumero.getText().toString();

        email = "adriano.lrr@gmail.com";
        password = "8133l@c@w";
        cnpj = "1111111";
        razao = "Ramos Rosa";
        nome = "Adriano Teste";
        cep = "18103120";
        numero = "108";
        codigoempresa = "XXXYYY";


        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();

                            FirebaseFirestore db = FirebaseFirestore.getInstance();

                            Empresa empresa = new Empresa(cnpj, razao, nome, cep, endereco, numero, codigoempresa );

                            HashMap<String, Object> resultEmpresa = (HashMap<String, Object>) empresa.toMap();

                            db.collection("empresas")
                                    .document(codigoempresa)
                                    .set(resultEmpresa)
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

                            Usuario usuario = new Usuario(codigoempresa, nome, email, null, null, null, null);

                            HashMap<String, Object> resultUsuario = (HashMap<String, Object>) usuario.toMap();

                            db.collection("usuarios")
                                    .document(codigoempresa)
                                    .collection(user.getUid())
                                    .document("cadastrousuario")
                                    .set(resultUsuario)
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

                            Toast.makeText(SignupActivity.this, "Cadastro efetivado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            finish();

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("Erro", "createUserWithEmail:failure", task.getException());

                            Toast.makeText(SignupActivity.this, "Falha no cadastro" +  task.getException(),
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
