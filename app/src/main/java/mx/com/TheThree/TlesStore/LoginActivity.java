package mx.com.TheThree.TlesStore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mx.com.TheThree.TlesStore.ui.ApiRest.AdaptadorRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.ApiRest;
import mx.com.TheThree.TlesStore.ui.ApiRest.CommitRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.RetrofitResponseListener;
import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;
import retrofit2.Retrofit;


public class LoginActivity extends AppCompatActivity {

    private EditText etCorreoTelefono;
    private EditText etPassword;
    private Button btnIniciar;
    private ApiRest api;
    private Retrofit retrofit;
    private TextView tvOlvidarPassoword;
    private TextView tvRegistrate;

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);
        setContentView(R.layout.activity_login);
        context=this;
        inicializarWidget();

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    IniciarSesion();
                }
            }
        });

        tvRegistrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(LoginActivity.this,RegistrarseActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }

    private void IniciarSesion() {
        new CommitRetrofit().IniciarSesion(new RetrofitResponseListener() {

            @Override
            public void onSuccess(Object object) throws JSONException {
                String cadena=object.toString();
                JSONObject obj=new JSONObject(cadena);
                if(!obj.isNull("Error")){
                    if(obj.getInt("Error")==1){
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText(getResources().getString(R.string.no_existen_un_usuario_con_estas_credenciales))
                                .show();
                    }else if(obj.getInt("Error")==2){
                        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                                .setTitleText("Oops...")
                                .setContentText(getResources().getString(R.string.el_usuario_fue_dado_de_baja_comunicate_con_nosotros_para_resolver_el_problema))
                                .show();
                    }
                }else{
                    if(!obj.isNull("Validar")){
                        //Mandar a validar correo o telefono
                        new MiComplemento().setDatos(context, "usuarioId", obj.getString("ID"));
                        Intent intent = new Intent(context, PrincipalActivity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        new MiComplemento().setDatos(context, "usuarioId", obj.getString("ID"));
                        Intent intent = new Intent(context, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
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
        }, api,etPassword.getText().toString(),etCorreoTelefono.getText().toString());

    }

    private boolean validar() {
        boolean valido=false;
        if(!etCorreoTelefono.getText().toString().equals("")){
            if(new MiComplemento().isCorreo(etCorreoTelefono.getText().toString())|| new MiComplemento().isNumero(etCorreoTelefono.getText().toString())) {
                if (!etPassword.getText().toString().equals("")) {
                    valido=true;
                } else {
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getResources().getString(R.string.el_pass_no_debe_quedar_vacio))
                            .show();
                }
            }else {
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText(getResources().getString(R.string.el_correo_o_el_telefono_no_son_validos))
                        .show();
            }
        }else{
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(getResources().getString(R.string.el_correo_no_debe_quedar_vacio))
                    .show();
        }
        return valido;
    }

    private void inicializarWidget() {
        etCorreoTelefono=findViewById(R.id.etCorreoTelefono);
        etPassword=findViewById(R.id.etPassword);
        btnIniciar=findViewById(R.id.btnIniciar);
        tvOlvidarPassoword=findViewById(R.id.tvOlvidarPassoword);
        tvRegistrate=findViewById(R.id.tvRegistrate);
    }
}