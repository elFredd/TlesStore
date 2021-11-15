package mx.com.TheThree.TlesStore;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

/**
 * @created 12/11/2021 - 11:16 p. m.
 * @project TlesStore
 * @autor alfre
 */
public class RegistrarStoreActivity extends AppCompatActivity {

    private LinearLayout llSinCompletar;
    private LinearLayout llEnEspera;
    private LinearLayout llCompletar;
    private Context context;
    private TextView tvMensajeIniciar;
    private TextView tvMensajeBienvenida;
    private TextView tvCompletar;
    private TextView tvEnEspera;
    private TextView tvSinCompletar;
    private ApiRest api;
    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_store_activity);
        getSupportActionBar().hide();
        tvCompletar=findViewById(R.id.tvCompletar);
        tvEnEspera=findViewById(R.id.tvEnEspera);
        tvSinCompletar=findViewById(R.id.tvSinCompletar);
        llSinCompletar=findViewById(R.id.llSinCompletar);
        tvMensajeIniciar=findViewById(R.id.tvMensajeIniciar);
        tvMensajeBienvenida=findViewById(R.id.tvMensajeBienvenida);
        llEnEspera=findViewById(R.id.llEnEspera);
        llCompletar=findViewById(R.id.llCompletar);
        llEnEspera.setEnabled(true);
        context=this;
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);

        VerificarPasosRegistroTienda();


    }

    private void VerificarPasosRegistroTienda() {
        new CommitRetrofit().VerificarPasosRegistroTienda(new RetrofitResponseListener() {

            @Override
            public void onSuccess(Object object) throws JSONException {
                String cadena = object.toString();
                JSONObject obj = new JSONObject(cadena);
                if(obj.isNull("IDTienda")) {

                    //Ine
                    ObjectRequerimientos objIne = new ObjectRequerimientos();
                    objIne.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent=new Intent(context,RegistrarINEActivity.class);
                            startActivity(intent);
                        }
                    });
                    objIne.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_card_id));
                    objIne.getTvNomRequerimiento().setText(getResources().getString(R.string.identificacion_oficial_con_curp_ine_o_ife));
                    ajustarPosicion(objIne, obj, "StatusIne");

                    //Nombre
                    ObjectRequerimientos objNombre = new ObjectRequerimientos();
                    objNombre.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, RegistrarNombreActivity.class);
                            startActivity(intent);
                        }
                    });
                    objNombre.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_user));
                    objNombre.getTvNomRequerimiento().setText(getResources().getString(R.string.nombre_completo));
                    ajustarPosicion(objNombre, obj, "StatusNombre");

                    //Nombre de la tienda
                    ObjectRequerimientos objNombreTienda = new ObjectRequerimientos();
                    objNombreTienda.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, RegistrarNombreTiendaActivity.class);
                            startActivity(intent);
                        }
                    });
                    objNombreTienda.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_store));
                    objNombreTienda.getTvNomRequerimiento().setText(getResources().getString(R.string.nombre_tienda));
                    ajustarPosicion(objNombreTienda, obj, "StatusNombreTienda");

                    //Acuerdo legal
                    ObjectRequerimientos objLey = new ObjectRequerimientos();
                    objLey.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(context, AcuerdosLegalesActivity.class);
                            startActivity(intent);
                        }
                    });
                    objLey.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_ley));
                    objLey.getTvNomRequerimiento().setText(getResources().getString(R.string.acuerdo_legal));
                    ajustarPosicion(objLey, obj, "StatusAcuerdoLegal");

                    //FotoPerfil
                    ObjectRequerimientos objFotoPerfil = new ObjectRequerimientos();
                    objFotoPerfil.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent =new Intent(context,RegistrarFotoPerfilActivity.class);
                            startActivity(intent);
                        }
                    });
                    objFotoPerfil.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_foto));
                    objFotoPerfil.getTvNomRequerimiento().setText(getResources().getString(R.string.foto_perfil));
                    ajustarPosicion(objFotoPerfil, obj, "StatusFotoPerfil");

                    //Ciudad
                    ObjectRequerimientos objCiudad = new ObjectRequerimientos();
                    objCiudad.getLl().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //Toast.makeText(context,"Dio click el mensaje",Toast.LENGTH_SHORT).show();
                        }
                    });
                    objCiudad.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_ciudad));
                    objCiudad.getTvNomRequerimiento().setText(getResources().getString(R.string.ciudad));
                    ajustarPosicion(objCiudad, obj, "StatusCiudad");

                    tvMensajeIniciar.setText(getResources().getString(R.string.bienvenido) + " " + obj.getString("NombreUsuario"));
                    tvMensajeBienvenida.setVisibility(View.VISIBLE);


                    if (llEnEspera.getChildCount() > 0) {
                        tvEnEspera.setVisibility(View.VISIBLE);
                    }
                    if (llSinCompletar.getChildCount() > 0) {
                        tvSinCompletar.setVisibility(View.VISIBLE);
                    }
                    if (llCompletar.getChildCount() > 0) {
                        tvCompletar.setVisibility(View.VISIBLE);
                    }
                }else{
                    new SweetAlertDialog(context, SweetAlertDialog.SUCCESS_TYPE)
                            .setTitleText(getResources().getString(R.string.felicidades))
                            .setContentText(getResources().getString(R.string.mensaje_registro_termiando))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    try {
                                        new MiComplemento().setDatos(context,"storeId",obj.getInt("IDTienda")+"");
                                        Intent intent=new Intent(context,MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            })
                            .show();
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
        }, api,Integer.parseInt(new MiComplemento().getDatos(context,"usuarioId")));

    }

    @SuppressLint("ResourceAsColor")
    private void ajustarPosicion(ObjectRequerimientos objRequerimientos, JSONObject obj, String nomObjectJSON) throws JSONException {
        if (obj.getInt(nomObjectJSON) == -1) {
            objRequerimientos.getTvRequerimiento().setText(getResources().getString(R.string.faltante));
            llSinCompletar.addView(objRequerimientos.getLl());
        } else if (obj.getInt(nomObjectJSON) == 0){
            objRequerimientos.getTvRequerimiento().setTextColor(getResources().getColor(R.color.gray));
            objRequerimientos.getIvIcono().setColorFilter(getResources().getColor(R.color.gray));
            objRequerimientos.getIvSiguiente().setVisibility(View.INVISIBLE);
            objRequerimientos.getTvRequerimiento().setText(getResources().getString(R.string.en_espera));
            objRequerimientos.getLl().setOnClickListener(null);
            objRequerimientos.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_pause));
            llEnEspera.addView(objRequerimientos.getLl());
        }else {
            objRequerimientos.getIvSiguiente().setVisibility(View.INVISIBLE);
            objRequerimientos.getTvRequerimiento().setTextColor(getResources().getColor(R.color.green));
            objRequerimientos.getIvIcono().setColorFilter(getResources().getColor(R.color.green));
            objRequerimientos.getTvRequerimiento().setText(getResources().getString(R.string.aprovado));
            objRequerimientos.getIvIcono().setImageDrawable(context.getDrawable(R.drawable.ic_verificado));
            objRequerimientos.getLl().setOnClickListener(null);
            llCompletar.addView(objRequerimientos.getLl());
        }
    }

    private class ObjectRequerimientos{
        private final ImageView ivIcono;
        private final ImageView ivSiguiente;
        private final TextView tvNomRequerimiento;
        private final TextView tvRequerimiento;
        private final LinearLayout ll;

        public ObjectRequerimientos() {
            ll=(LinearLayout)  getLayoutInflater().inflate(R.layout.ll_pasos_verificacion,null);
            ivIcono=ll.findViewById(R.id.ivIcono);
            ivSiguiente=ll.findViewById(R.id.ivSiguiente);
            tvNomRequerimiento=ll.findViewById(R.id.tvNomRequerimiento);
            tvRequerimiento=ll.findViewById(R.id.tvRequerimiento);
        }

        public ImageView getIvSiguiente() {
            return ivSiguiente;
        }

        public TextView getTvRequerimiento() {
            return tvRequerimiento;
        }

        public TextView getTvNomRequerimiento() {
            return tvNomRequerimiento;
        }

        public ImageView getIvIcono() {
            return ivIcono;
        }

        public LinearLayout getLl() {
            return ll;
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        tvEnEspera.setVisibility(View.GONE);
        tvSinCompletar.setVisibility(View.GONE);
        tvCompletar.setVisibility(View.GONE);

        llSinCompletar.removeAllViews();
        llEnEspera.removeAllViews();
        llCompletar.removeAllViews();

        VerificarPasosRegistroTienda();

    }
}
