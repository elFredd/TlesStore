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
import android.widget.TextView;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mx.com.TheThree.TlesStore.ui.ApiRest.AdaptadorRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.ApiRest;
import mx.com.TheThree.TlesStore.ui.ApiRest.CommitRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.RetrofitResponseListener;
import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;
import retrofit2.Retrofit;

public class AcuerdosLegalesActivity extends AppCompatActivity {

    private ApiRest api;
    private Retrofit retrofit;
    private TextView tvAcuerdoLegal;
    private Button btnAceptar;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acuerdos_legales);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        context=this;
        tvAcuerdoLegal=findViewById(R.id.tvAcuerdoLegal);
        btnAceptar=findViewById(R.id.btnAceptar);
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);

        TraerAcuerdoLegarStore();




        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AceptarAcuerdoLegal();
            }
        });
    }

    private void TraerAcuerdoLegarStore() {
        new CommitRetrofit().TraerAcuerdoLegarStore(new RetrofitResponseListener() {

            @Override
            public void onSuccess(Object object) {

                tvAcuerdoLegal.setText(object.toString());
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
        }, api);

    }

    private void AceptarAcuerdoLegal() {
        new CommitRetrofit().AceptarAcuerdoLegal(new RetrofitResponseListener() {

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
            }, api,Integer.parseInt(new MiComplemento().getDatos(context,"usuarioId")));


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return false;
    }
}