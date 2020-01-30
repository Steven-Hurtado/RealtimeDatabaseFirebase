package com.example.realtimedatabasefirebase.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimedatabasefirebase.R;
import com.example.realtimedatabasefirebase.models.Mensaje;

import java.util.ArrayList;

public class MensajeAdapter extends RecyclerView.Adapter<MensajeAdapter.ViewHolder> {

    private int resource;
    //Lista para retornar todos los mensajes de la bd
    private ArrayList<Mensaje> mensajesList;

    public MensajeAdapter(ArrayList<Mensaje> mensajesList, int resource){
        this.mensajesList = mensajesList;
        this.resource = resource;
    }

    @NonNull
    @Override
    //Se creara la vista
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(resource,parent,false);
        return new ViewHolder(view);
    }

    @Override
    //los datos que se crearan en la vista
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        Mensaje mensaje = mensajesList.get(index);
        holder.textViewMensaje.setText(mensaje.getTexto());
    }

    @Override
    //retornar el numero de vista
    public int getItemCount() {
        return mensajesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewMensaje;
        public View view;

        public ViewHolder(View view)
        {
            super(view);

            this.view = view;
            this.textViewMensaje = (TextView) view.findViewById(R.id.textViewMensaje);
        }
    }
}
