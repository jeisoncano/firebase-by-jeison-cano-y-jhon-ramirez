package com.example.firebaseventas;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.firebaseventas.adapters.VentasAdapter;
import com.example.firebaseventas.connection.FireBaseConnection;
import com.example.firebaseventas.models.VentasModel;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    protected VentasModel model;
    protected ArrayList<VentasModel> modelArrayList;
    protected VentasAdapter adapter;

    protected FirebaseFirestore db;
    protected FirebaseAuth mAuth;
    protected FirebaseStorage mFirebaseStorage;
    //protected StorageReference mStorageRef;

    protected Query query;
    protected CollectionReference collectionReference;
    protected StorageReference mStorageReference, fileReference;

    protected final String COLLECTION_NAME = "ventas";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    protected void init(){
        model = new VentasModel();
        db = FireBaseConnection.ConnectionFirestore();
        mAuth = FireBaseConnection.ConnectionAuth();
        mFirebaseStorage = FireBaseConnection.ConnectionStorage();
        collectionReference = db.collection(COLLECTION_NAME);
    }

    protected void makeSimpleToast(String message, int duration){
        Toast.makeText(this, message,duration).show();
    }

    protected void makeSimpleAlertDialog(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message);
        builder.setTitle(title);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    protected void goToList(){
        Intent intent = new Intent(this,ListActivity.class);
        startActivity(intent);
    }

    protected void goToCreate(){
        Intent intent = new Intent(this,CreateActivity.class);
        startActivity(intent);
    }

    protected void goToEdit(){
        Intent intent = new Intent(this,BaseActivity.class);
        startActivity(intent);
    }

    protected void goToSearch(){
        Intent intent = new Intent(this,BaseActivity.class);
        startActivity(intent);
    }

    protected void goToDetail(VentasModel model){
        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtra("model",model);
        startActivity(intent);
    }
}