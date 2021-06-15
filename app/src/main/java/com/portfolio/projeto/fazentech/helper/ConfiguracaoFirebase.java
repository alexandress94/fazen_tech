package com.portfolio.projeto.fazentech.helper;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

//Classe criada para referencia as configurações do Firebase
public class ConfiguracaoFirebase {

    private static DatabaseReference referenciaFirebase;
    private static FirebaseAuth referencuiaAutenticacao;
    private static StorageReference referenciaStorage;

    //Consturtor para identificao do usuario
    public static String getIdUsuario(){
        FirebaseAuth autenticacao = getFirebaseAutenticacao();
        return autenticacao.getCurrentUser().getUid();
    }

    //Configura a instancia do database
    public static DatabaseReference getFirebase(){
        if (referenciaFirebase == null){
            referenciaFirebase = FirebaseDatabase.getInstance().getReference();
        }
        return referenciaFirebase;
    }

    //Retorna a instancia do FirebaseAuth (autenticacao Firebase)
    public static FirebaseAuth getFirebaseAutenticacao(){
        if (referencuiaAutenticacao == null){
            referencuiaAutenticacao = FirebaseAuth.getInstance();
        }
        return referencuiaAutenticacao;
    }

    //Configura a instancia do FirebaseStorage
    public static StorageReference getFirebaseStorage(){
        if (referenciaStorage == null){
            referenciaStorage = FirebaseStorage.getInstance().getReference();
        }
        return referenciaStorage;
    }

}
