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

public class RegistrarseActivity extends AppCompatActivity {

    private EditText etCorreoTelefono;
    private EditText etPassword;
    private EditText etNombre;
    private EditText etApellido;
    private Button btnIniciar;
    private TextView tvOlvidarPassoword;
    private TextView tvIniciar;
    private ApiRest api;
    private Retrofit retrofit;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrarse);
        context=this;
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);
        inicializarWidget();

        tvIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(RegistrarseActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validar()){
                    RegistrarUsuario();
                }
            }
        });

    }

    private void RegistrarUsuario() {
        new CommitRetrofit().RegistrarUsuario(new RetrofitResponseListener() {

            @Override
            public void onSuccess(Object object) throws JSONException {
                String cadena=object.toString();
                JSONObject obj=new JSONObject(cadena);
                if(!obj.isNull("Error")){
                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getResources().getString(R.string.ya_existe_un_correo_o_un_telefono))
                            .show();

                    new MiComplemento().focusEditText(etCorreoTelefono,context);
                }else{
                    new MiComplemento().setDatos(context,"usuarioId",obj.getString("ID"));
                    //Mandar a Verificar correo y telefono
                    Intent intent = new Intent(context, PrincipalActivity.class);
                    startActivity(intent);
                    finish();
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
        }, api,etNombre.getText().toString(),etPassword.getText().toString(),etApellido.getText().toString(),etCorreoTelefono.getText().toString());
    }

    private boolean validar() {
        boolean valido=false;
        if(!etNombre.getText().toString().equals("")){
            if(!etApellido.getText().toString().equals("")){
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
            }else{
                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                        .setTitleText("Oops...")
                        .setContentText(getResources().getString(R.string.el_apellido_no_debe_quedar_vacio))
                        .show();
            }
        }else{

            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(getResources().getString(R.string.el_nombre_no_debe_quedar_vacio))
                    .show();
        }
        return  valido;
    }

    private void inicializarWidget() {
        etCorreoTelefono=findViewById(R.id.etCorreoTelefono);
        etPassword=findViewById(R.id.etPassword);
        etNombre=findViewById(R.id.etNombre);
        etApellido=findViewById(R.id.etApellido);
        btnIniciar=findViewById(R.id.btnIniciar);
        tvOlvidarPassoword=findViewById(R.id.tvOlvidarPassoword);
        tvIniciar=findViewById(R.id.tvIniciar);
    }
}