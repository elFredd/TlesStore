package mx.com.TheThree.TlesStore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Timer;
import java.util.TimerTask;

import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;


public class PrincipalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Context context=this ;


        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Intent intent;
                if(!new MiComplemento().getDatos(context,"usuarioId").equals(""))
                {
                    if(new MiComplemento().getDatos(context,"storeId").equals("")){
                        intent = new Intent(PrincipalActivity.this, RegistrarStoreActivity.class);
                    }else {
                        intent = new Intent(PrincipalActivity.this, MainActivity.class);
                    }
                }else{
                    intent = new Intent(PrincipalActivity.this, LoginActivity.class);

                }
                startActivity(intent);
                finish();

            }
        };
        Timer tiempo = new Timer();
        tiempo.schedule(tarea, 1000);
    }
}