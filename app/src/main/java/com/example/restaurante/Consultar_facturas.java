package com.example.restaurante;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Entidades.Pedido;
import Utilidades.Utilidades;

@RequiresApi(api = Build.VERSION_CODES.O)
public class Consultar_facturas extends AppCompatActivity {
    ListView aListview ;

    ArrayList <String> listainformacion;

    ArrayList <Pedido> listapedidos;

    ConexionSQLiteHelper conn;

    TextView Recau,Prueba;

    EditText Pl1,PL2,PL3;

    Button Buscador,clear,Advance;


    @SuppressLint({"SetTextI18n", "Range"})
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_facturas);

        Prueba = findViewById(R.id.Test);

        Pl1 = findViewById(R.id.PLDIA);

        PL2 = findViewById(R.id.PLMES);

        PL3 = findViewById(R.id.PLAÑO);

        Buscador = findViewById(R.id.Filtro);

        Advance = findViewById(R.id.ADS);

        clear = findViewById(R.id.RE);

        Recau  = findViewById(R.id.Total_ganancia);

        aListview = findViewById(R.id.Listviewpedidos);


        conn = new ConexionSQLiteHelper(getApplicationContext(),"bd_pedidos",null,1);

        SQLiteDatabase db = conn.getWritableDatabase();

        Consultar_pedidos();

        //Adaptador de de la tabla completa
        ArrayAdapter<String> adaptador = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listainformacion);



        aListview.setAdapter(adaptador);

        //Ganancia total

        @SuppressLint("Recycle") Cursor c = db.rawQuery("SELECT SUM (precio_total) FROM pedidos ",null);

        c.moveToFirst();

        if (c.moveToFirst() ) {
            Recau.setText(""+c.getString(0)+"$$");
        } else {
            Recau.setText("Ooops no data extracted");
        }


        // Ejecucion para el boton buscar por fecha las facturas
        Buscador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pld = Pl1.getText().toString();

                String plm = PL2.getText().toString();

                String pla = PL3.getText().toString();

                String Fecha_filtro;

                Fecha_filtro = pld + "/" + plm+ "/" + pla;

                Prueba.setText(Fecha_filtro);

                f_con();

                @SuppressLint("Recycle") Cursor c2 = db.rawQuery("SELECT SUM (precio_total) FROM pedidos Where Fecha_pedido Like '%"+Fecha_filtro+"%'",null);

                c2.moveToFirst();

                if (c2.moveToFirst() ) {
                    Recau.setText(""+c2.getString(0)+"$$");
                } else {
                    Recau.setText("Ooops no data extracted");
                }

            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aListview.setAdapter(adaptador);

                c.moveToFirst();

                if (c.moveToFirst() ) {
                    Recau.setText(""+c.getString(0)+"$$");
                } else {
                    Recau.setText("Ooops no data extracted");
                }
            }
        });


        Advance.setOnClickListener(view -> {
            Intent i = new Intent(Consultar_facturas.this,Busqueda_avanzada.class);
            startActivity(i);
        });

    }

    //Metodo que contiene la consulta y rellena  la tabla del list view
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
            pedidos.setFecha_pedido(cursor.getString(2));
            pedidos.setHora_pedido(cursor.getString(3));
            pedidos.setPrecio_Total(cursor.getInt(4));

            listapedidos.add(pedidos);
        }

        Obtenertabala();

    }

        // creacion del cursor para llamada de consulta por fechas asigandas a traves del plain text
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Consultar_por_fecha() {

        SQLiteDatabase db = conn.getWritableDatabase();

        Pedido pedidos;

        String pld = Pl1.getText().toString();

        String plm = PL2.getText().toString();

        String pla = PL3.getText().toString();

        String Fecha_filtro;

        Fecha_filtro = pld + "/" + plm+ "/" + pla;;


        listapedidos = new ArrayList<>();

        @SuppressLint("Recycle") Cursor cursor = db.rawQuery("SELECT *  FROM " +
                Utilidades.TABLA_Pedidos + " where " +Utilidades.Campo_Fecha_pedido+ " like '%"+Fecha_filtro+"%'" , null);

        while (cursor.moveToNext()){

            pedidos = new Pedido();
            pedidos.setCod(cursor.getInt(0));
            pedidos.setPlatos_pedidos(cursor.getString(1));
            pedidos.setFecha_pedido(cursor.getString(2));
            pedidos.setHora_pedido(cursor.getString(3));
            pedidos.setPrecio_Total(cursor.getInt(4));

            listapedidos.add(pedidos);


        }

        Obtenertabala();

    }


        //Metodo para esoger los campos de la tabla a obtener
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void Obtenertabala() {

        listainformacion = new ArrayList<>();

        for (int i= 0;i<listapedidos.size();i++){

            listainformacion.add(listapedidos.get(i).getCod()
                    +"-"+listapedidos.get(i).getPlatos_pedidos()
                    +"-"+listapedidos.get(i).getFecha_pedido()
                    +"-"+listapedidos.get(i).getHora_pedido()
                    +"-"+listapedidos.get(i).getPrecio_Total()+"$$");

        }

    }


    //Metodo para filtrar por fecha
    public void f_con (){
        ArrayAdapter<String> adaptador2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listainformacion);
        Consultar_por_fecha();
        aListview.setAdapter(adaptador2);
    }


}