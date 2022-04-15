package com.example.restaurante;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Date;

import Utilidades.Utilidades;

@RequiresApi(api = Build.VERSION_CODES.N)
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    CheckBox op1,op2,op3,op4,op5,op6,op7,op8,op9,op10,op11,op12,op13,op14,op15,op16;
    Button aFacturas;
    TextView total;
    Spinner aSpinner;
    @SuppressLint("SimpleDateFormat")
    SimpleDateFormat sdf = new SimpleDateFormat(" HH:mm ");
    String hora_actual = sdf.format(new Date());
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_pedidos",null,1);

        aSpinner = findViewById(R.id.spinner);
        aSpinner.setOnItemSelectedListener(this);

        aFacturas = findViewById(R.id.Facturas);
        total = findViewById(R.id.coste_total);
        op1 = findViewById(R.id.huevos_revueltos);
        op2 = findViewById(R.id.bacon_huevos);
        op3 = findViewById(R.id.salchicha_hojaldre);
        op4 = findViewById(R.id.panckakes);
        op5 = findViewById(R.id.arroz_carne);
        op6 = findViewById(R.id.arroz_pollo);
        op7 = findViewById(R.id.sancocho);
        op8 = findViewById(R.id.Hamburguesa);
        op9 = findViewById(R.id.tamales);
        op10 = findViewById(R.id.sandwich);
        op11 = findViewById(R.id.arroz_huevo_frito);
        op12 = findViewById(R.id.patacon_carne);
        op13 = findViewById(R.id.Chicha);
        op14 = findViewById(R.id.Chicheme);
        op15 = findViewById(R.id.cerveza);
        op16 = findViewById(R.id.agua);

        aFacturas.setOnClickListener(view -> {
            Intent i = new Intent(MainActivity.this,Consultar_facturas.class);
            startActivity(i);
        });

    }


    @SuppressLint("SetTextI18n")
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void  Calcular (View v){
        ConexionSQLiteHelper conn= new ConexionSQLiteHelper(this,"bd_pedidos",null,1);

        double Ttotal = 0.00;
        String Prueba = "";
        if (op1.isChecked()){
            Ttotal += 3;
            Prueba += "[huevos revueltos]";}

        if (op2.isChecked()){
            Ttotal += 5;
            Prueba += " [bacon y huevos]";}

        if (op3.isChecked()){
            Ttotal += 3;
            Prueba += " [Salchicha hojaldre]";}


        if (op4.isChecked()){
            Ttotal += 4;
            Prueba += " [Panckakes]";}

        if (op5.isChecked()){
            Ttotal += 5;
            Prueba += " [Arroz y carne]";}


        if (op6.isChecked()){
            Ttotal += 4;
            Prueba += " [Arroz y pollo]";}

        if (op7.isChecked()){
            Ttotal += 3;
            Prueba += " [Sancocho]";}

        if (op8.isChecked()){
            Ttotal += 7;
            Prueba += " [Hamburguesa]";}

        if (op9.isChecked()){
            Ttotal += 2;
            Prueba += " [Tamales]";}

        if (op10.isChecked()){
            Ttotal += 2.50;
            Prueba += " [Sandwich]";}

        if (op11.isChecked()){
            Ttotal += 3;
            Prueba += " [Arroz y Huevo frito]";}

        if (op12.isChecked()){
            Ttotal += 4.50;
            Prueba += " [Patacon con carne]";}

        if (op13.isChecked()){
            Ttotal += 2;
            Prueba += " [Chicha]";}

        if (op14.isChecked()){
            Ttotal += 2;
            Prueba += " [Chicheme]";}


        if (op15.isChecked()){
            Ttotal += 3;
            Prueba += " [Cerveza]";}


        if (op16.isChecked()){
            Ttotal += 1;
            Prueba += " [Agua]";}


        total.setText("Coste Total : "+Ttotal+" $");


        String Eleccion = aSpinner.getSelectedItem().toString();

        if(Eleccion.equals("Facturar")){

            SQLiteDatabase db = conn.getWritableDatabase();

            String insert = "INSERT INTO "+Utilidades.TABLA_Pedidos+"("+Utilidades.Campo_platos_pedidos+","+Utilidades.Campo_hora+","+Utilidades.Campo_total+")" +
                    "Values ('"+Prueba+"','"+hora_actual+"',"+Ttotal+")";

            db.execSQL(insert);

            db.close();

            notoficacion();
        }


    }

    @SuppressLint("SetTextI18n")
    public void Ce (View v){

        op1.setChecked(false);

        op2.setChecked(false);

        op3.setChecked(false);

        op4.setChecked(false);

        op5.setChecked(false);

        op6.setChecked(false);

        op7.setChecked(false);

        op8.setChecked(false);

        op9.setChecked(false);

        op10.setChecked(false);

        op11.setChecked(false);

        op12.setChecked(false);

        op13.setChecked(false);

        op14.setChecked(false);

        op15.setChecked(false);

        op16.setChecked(false);

        total.setText("Coste Total:   ");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(this,adapterView.getSelectedItem().toString(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public   void notoficacion(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel canal = new NotificationChannel("Notificacion","Mi notificacion",NotificationManager.IMPORTANCE_DEFAULT);

            NotificationManager manager = getSystemService(NotificationManager.class);

            manager.createNotificationChannel(canal);

        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this, "Notificacion");

        builder.setContentTitle("El programa");

        builder.setContentText("Se ha agregado la factura");

        builder.setSmallIcon(R.drawable.ic_launcher_background);

        builder.setAutoCancel(true);

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(MainActivity.this);

        managerCompat.notify(1,builder.build());


    }

}