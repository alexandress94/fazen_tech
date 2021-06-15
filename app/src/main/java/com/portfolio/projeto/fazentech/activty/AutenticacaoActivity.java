package com.portfolio.projeto.fazentech.activty;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;
import com.portfolio.projeto.fazentech.R;
import com.portfolio.projeto.fazentech.helper.ConfiguracaoFirebase;

public class AutenticacaoActivity extends AppCompatActivity {
    private Button      btnAcesso;
    private EditText    edtEmail, edtSenha;
    private Switch      swtTipoAcesso;
    //Atributo para criar objeto autenticacao
    private FirebaseAuth frbAutenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);

        /*
        Esconder a ActionBar
        getSupportActionBar().hide();
        Não será utilizado no projeto, será criado uma ActionBar customizada
         */


        //Chamando o metodo no onCreate
        incializarComponentes();

        //Referencia um objeto para autenticar usuario logado da classe ConfiguracaoFirebase do pacote Helper

        frbAutenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //frbAutenticacao.signOut();


        //Verifica se o usuario está logado
        verificaUsuarioLogado();

        //Acao do botao ao ser clicado
        btnAcesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Recuperando o que o usuario digitou campo Email e Senha
                String strEmail = edtEmail.getText().toString();
                String strSenha = edtSenha.getText().toString();

                //Verifica se o campo E-mail e Senha são nulos
                if(! strEmail.isEmpty() ){
                    if (! strSenha.isEmpty()){
                        //Verifica o estado do Switch
                        if(swtTipoAcesso.isChecked()){      //Se for habilitado realiza o Cadastro
                            //Autenticao do Email e Senha
                            frbAutenticacao.createUserWithEmailAndPassword(strEmail, strSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()){   //Direciona o usuario para outra tela caso o login seja realizado com sucesso
                                        Toast.makeText(AutenticacaoActivity.this, "Cadastro realizado com sucesso!",Toast.LENGTH_SHORT).show();
                                        iniciaHomeActivity();
                                    }else{
                                        String strErroExecucao = "";
                                        try {
                                            throw task.getException();
                                        }catch (FirebaseAuthWeakPasswordException e){
                                            strErroExecucao = "Digite uma senha mais forte!";
                                        }catch (FirebaseAuthInvalidCredentialsException e){
                                            strErroExecucao = "Digite um E-mail válido!";
                                        }catch (FirebaseAuthUserCollisionException e){
                                            strErroExecucao = "Está conta já existe!";
                                        }catch (Exception e){
                                            strErroExecucao = "Erro ao cadastrar usuário: " + e.getMessage();
                                            e.printStackTrace();
                                        }
                                        Toast.makeText(AutenticacaoActivity.this, "Erro: "+ strErroExecucao, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }else{      //Se não for habilitado realiza o Login
                            frbAutenticacao.signInWithEmailAndPassword(strEmail, strSenha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful() ){
                                        Toast.makeText(AutenticacaoActivity.this, "Login realizado com sucesso!", Toast.LENGTH_SHORT).show();
                                        iniciaHomeActivity();
                                    }else{
                                        Toast.makeText(AutenticacaoActivity.this, "Erro ao realizar login: "+ task.getException(),Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        }

                    }else{
                        Toast.makeText(AutenticacaoActivity.this, "Informe sua senha", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(AutenticacaoActivity.this, "Informe seu E-mail",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metodo para verificar se o usuario esta logado no app
    private void verificaUsuarioLogado(){
        FirebaseUser usuarioLogado = frbAutenticacao.getCurrentUser();
        if(usuarioLogado != null){
            iniciaHomeActivity();
        }
    }

    //Inicia a HomeActivity (Tela principal do app) caso o usuario seja validado no login
    private void iniciaHomeActivity(){
        startActivity(new Intent(getApplicationContext(), HomeActivity.class));
    }

    //Metodo que irá iniciar os componentes referenciados no layout
    private void incializarComponentes(){
        //Adicionando os campos do layout
        btnAcesso       = findViewById(R.id.btn_Acesso);
        edtEmail        = findViewById(R.id.editT_CadastroEmail);
        edtSenha        = findViewById(R.id.editT_CadastroSenha);
        swtTipoAcesso   = findViewById(R.id.swh_Acesso);
    }
}