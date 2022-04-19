package com.example.restaurante;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import Utilidades.Utilidades;

public class Busqueda_avanzada extends AppCompatActivity {
    ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_pedidos",null,1);

    TextView TV1,TV2,TV3,TV4;

    EditText Cod;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_avanzada);
        TV1 = findViewById(R.id.Tx1);

        TV2 = findViewById(R.id.Tx2);

        TV3 = findViewById(R.id.Tx3);

        TV4 = findViewById(R.id.Tx4);

        Cod = findViewById(R.id.PLCOD);


    }


    @SuppressLint("NonConstantResourceId")
    public void onClick (View view){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_pedidos",null,1);

        switch (view.getId()){

            case R.id.Filtrador:
                consultar();
                break;


            case R.id.Borrador:

                Eliminar_Pedido();
                break;
        }


    }


    @SuppressLint("SetTextI18n")
    private void consultar() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {Cod.getText().toString()};
        String [] campos = {Utilidades.Campo_platos_pedidos,Utilidades.Campo_Fecha_pedido,Utilidades.Campo_hora,Utilidades.Campo_total};


        try{
            Cursor cu = db.query(Utilidades.TABLA_Pedidos,campos,Utilidades.Campo_cod+"=?",parametros,null,null,null);

            cu.moveToFirst();

            TV1.setText(cu.getString(0));
            TV2.setText(cu.getString(1));
            TV3.setText(cu.getString(2));
            TV4.setText(cu.getString(3)+"$$");
            cu.close();
        }

        catch (Exception e){

            Toast.makeText(getApplicationContext(),"El documento no existe",Toast.LENGTH_LONG).show();
            limpiar();


        }

    }

    private void Eliminar_Pedido() {
        SQLiteDatabase db = conn.getWritableDatabase();
        String [] parametros = {Cod.getText().toString()};

        db.delete(Utilidades.TABLA_Pedidos,Utilidades.Campo_cod+"=?",parametros);
        Toast.makeText(getApplicationContext(),"Se elimino la factura seleccionada",Toast.LENGTH_LONG).show();

    }

    private void limpiar() {

        TV1.setText("");
        TV2.setText("");
        TV3.setText("");
        TV4.setText("");
    }
}