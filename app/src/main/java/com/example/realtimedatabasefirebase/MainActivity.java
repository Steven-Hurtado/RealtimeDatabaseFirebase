package com.example.realtimedatabasefirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.realtimedatabasefirebase.adapters.MensajeAdapter;
import com.example.realtimedatabasefirebase.models.Mensaje;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private EditText mEditextMensaje;
    private Button mBtnCrearDatos;

    private DatabaseReference mDatabase;

    private MensajeAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private ArrayList<Mensaje>mMensajesList= new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEditextMensaje= (EditText)findViewById(R.id.editTextMensaje);
        mBtnCrearDatos = (Button)findViewById(R.id.btnCrearDatos);
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerViewMensajes);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDatabase= FirebaseDatabase.getInstance().getReference();

        mBtnCrearDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mensaje = mEditextMensaje.getText().toString();
                mDatabase.child("Mensajes").push().child("texto").setValue(mensaje);
            }
        });
        getMensajesFromFirebase();
    }

    private void getMensajesFromFirebase(){
        mDatabase.child("Mensajes").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    mMensajesList.clear();
                    for(DataSnapshot ds: dataSnapshot.getChildren()){
                        String texto = ds.child("texto").getValue().toString();
                        mMensajesList.add(new Mensaje(texto));
                    }
                    mAdapter = new MensajeAdapter(mMensajesList, R.layout.mensaje_view);
                    mRecyclerView.setAdapter(mAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
