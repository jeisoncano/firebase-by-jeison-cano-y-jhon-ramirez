package com.example.firebaseventas;

import android.media.Image;
import android.os.Bundle;

import com.example.firebaseventas.models.VentasModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.firestore.DocumentReference;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class CreateActivity extends BaseActivity {

    FloatingActionButton fab_create_save,fab_create_clear,fab_create_back;
    ImageView iv_create_image;
    TextView tv_create_click_image;
    EditText et_create_serial,et_create_brand,et_create_description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        super.init();
        init();

        fab_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToList();
            }
        });

        fab_create_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        fab_create_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String serial,product,brand;
                boolean active;

                serial = et_create_serial.getText().toString();
                product = et_create_description.getText().toString();
                brand = et_create_brand.getText().toString();

                if(serial.isEmpty() || product.isEmpty() || brand.isEmpty()){
                    makeSimpleAlertDialog("info","please fill all fields");

                }else{
                    model = new VentasModel();
                    model.setActive(true);
                    model.setBrand(brand);
                    model.setProduct(product);
                    model.setSerial(serial);

                    save(model);
                }
            }
        });
    }

    private void save(VentasModel model) {
        if (collectionReference!=null){
            collectionReference.add(model).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                @Override
                public void onComplete(@NonNull Task<DocumentReference> task) {
                    if (task.isSuccessful()){
                        if (task.getResult() !=null){
                            makeSimpleAlertDialog("succes","sale done");
                        }else{
                            makeSimpleAlertDialog("warning","sale not made");
                        }

                    }else{
                        makeSimpleAlertDialog("error",task.getException().getMessage());
                    }
                }
            });
        }else{makeSimpleAlertDialog("error","not databse connection");

        }
    }

    protected void  init(){
        fab_create_back = findViewById(R.id.fab_create_back);
        fab_create_save = findViewById(R.id.fab_create_save);
        fab_create_clear = findViewById(R.id.fab_create_clear);
        iv_create_image = findViewById(R.id.iv_create_image);
        tv_create_click_image = findViewById(R.id.tv_create_click_image);
        et_create_brand = findViewById(R.id.et_create_brand);
        et_create_description = findViewById(R.id.et_create_description);
        et_create_serial = findViewById(R.id.et_create_serial);
    }

    private void  clear(){
        et_create_brand.setText("");
        et_create_description.setText("");
        et_create_serial.setText("");

        et_create_serial.requestFocus();

        iv_create_image.setImageResource(R.drawable.ic_shopping_cart_black_18dp);
    }
}