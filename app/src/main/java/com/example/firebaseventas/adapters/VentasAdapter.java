package com.example.firebaseventas.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.firebaseventas.R;
import com.example.firebaseventas.models.VentasModel;

import java.util.ArrayList;

public class VentasAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<VentasModel>modelArrayList;

    public VentasAdapter(Context context, ArrayList<VentasModel> modelArrayList) {
        this.context = context;
        this.modelArrayList = modelArrayList;
    }

    @Override
    public int getCount() {
        return modelArrayList.size();
    }

    @Override
    public VentasModel getItem(int position) {
        return modelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        if (view==null){
            LayoutInflater layoutInflater = LayoutInflater.from(this.context);
            view = layoutInflater.inflate(R.layout.ventas_list_item,viewGroup,false);
        }

        TextView tv_Ventas_list_item_description = view.findViewById(R.id.tv_Ventas_list_item_description);
        TextView tv_Ventas_list_item_serial = view.findViewById(R.id.tv_Ventas_list_item_serial);

        tv_Ventas_list_item_description.setText(getItem(position).getProduct());
        tv_Ventas_list_item_serial.setText(getItem(position).getSerial());

        return view;
    }
}
