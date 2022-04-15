package com.example.restaurante;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Date;

import Entidades.Pedido;
import Utilidades.Utilidades;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Consultar_facturas extends AppCompatActivity {
    ListView aListview ;
    ArrayList <String> listainformacion;
    ArrayList <Pedido> listapedidos;
    ConexionSQLiteHelper conn;
    TextView Recau;
    @SuppressLint({"SetTextI18n", "Range"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_facturas);



        Recau  = findViewById(R.id.Total_ganancia);

        aListview = findViewById(R.id.Listviewpedidos);

        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_pedidos",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        Consultar_pedidos();

        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listainformacion);

        aListview.setAdapter(adaptador);

        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT SUM (precio_total) FROM pedidos ",null);

        c.moveToFirst();

        if (c.moveToFirst() ) {
            Recau.setText(""+c.getString(0)+"$$");
        } else {
            Recau.setText("Ooops no data extracted");
        }


    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Consultar_pedidos() {

        SQLiteDatabase db = conn.getWritableDatabase();

        Pedido pedidos;

        listapedidos = new ArrayList<>();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("Select * From "+ Utilidades.TABLA_Pedidos,null);

        while (cursor.moveToNext()){

            pedidos = new Pedido();
            pedidos.setCod(cursor.getInt(0));
            pedidos.setPlatos_pedidos(cursor.getString(1));
            pedidos.setHora_pedido(cursor.getString(2));
            pedidos.setPrecio_Total(cursor.getInt(3));

            listapedidos.add(pedidos);
        }

        Obtenertabala();

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Obtenertabala() {

        listainformacion = new ArrayList<>();

        for (int i= 0;i<listapedidos.size();i++){

            listainformacion.add(listapedidos.get(i).getCod()+"-"+listapedidos.get(i).getPlatos_pedidos()
                    +"-"+listapedidos.get(i).getHora_pedido()
                    +"-"+listapedidos.get(i).getPrecio_Total()+"$$");

        }

    }


}