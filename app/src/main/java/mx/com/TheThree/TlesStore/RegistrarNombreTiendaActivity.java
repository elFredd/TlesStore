package mx.com.TheThree.TlesStore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mx.com.TheThree.TlesStore.R;
import mx.com.TheThree.TlesStore.ui.ApiRest.AdaptadorRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.ApiRest;
import mx.com.TheThree.TlesStore.ui.ApiRest.CommitRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.RetrofitResponseListener;
import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;
import retrofit2.Retrofit;

public class RegistrarNombreTiendaActivity extends AppCompatActivity {

    private EditText etNombre;
    private ApiRest api;
    private Retrofit retrofit;
    private Button btnGuardar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_nombre_tienda);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        context=this;
        etNombre=findViewById(R.id.etNombre);
        btnGuardar=findViewById(R.id.btnGuardar);
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);


        etNombre.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent event) {
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    GuardarNombreTiendaStoreRegistrar();

                    return true;
                }
                return false;
            }
        });

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarNombreTiendaStoreRegistrar();
            }
        });
    }

    private void GuardarNombreTiendaStoreRegistrar() {
        if(!etNombre.getText().toString().equals("")){
            new CommitRetrofit().GuardarNombreTiendaStoreRegistrar(new RetrofitResponseListener() {

                @Override
                public void onSuccess(Object object) {
                    finish();
                }

                @Override
                public void onSuccess(List listElements) {
                    Log.e("TAG", "Entro al list");
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Log.e("TAG", "Entro al onFailure " + throwable.getMessage());
                }

                @Override
                public void onFailure(String cadena) {
                    Log.e("TAG", "Entro al onFailure "+cadena);

                }
            }, api,Integer.parseInt(new MiComplemento().getDatos(context,"usuarioId")),etNombre.getText().toString());

        }else{
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(getResources().getString(R.string.el_nombre_no_debe_quedar_vacio))
                    .show();
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return false;
    }
}