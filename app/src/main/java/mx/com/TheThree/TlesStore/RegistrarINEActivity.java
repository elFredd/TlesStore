package mx.com.TheThree.TlesStore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mx.com.TheThree.TlesStore.ui.ApiRest.AdaptadorRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.ApiRest;
import mx.com.TheThree.TlesStore.ui.ApiRest.CommitRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.RetrofitResponseListener;
import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;
import retrofit2.Retrofit;

public class RegistrarINEActivity extends AppCompatActivity {

    private ApiRest api;
    private Retrofit retrofit;
    private Button btnGuardar;
    private Button btnCamara;
    private Button btnGaleria;
    private Context context;
    private LinearLayout llBtnRotate;
    private ImageButton btnRotacionleft;
    private ImageButton btnRotacionRidth;
    private ImageView ivImagen;
    private boolean isCamara;
    private String dataUrlImagen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("");
        setContentView(R.layout.activity_registrar_ine);
        //getSupportActionBar().hide();
        context=this;
        btnRotacionleft=findViewById(R.id.btnRotacionleft);
        btnRotacionRidth=findViewById(R.id.btnRotacionRidth);
        btnCamara=findViewById(R.id.btnCamara);
        btnGaleria=findViewById(R.id.btnGaleria);
        btnGuardar=findViewById(R.id.btnGuardar);
        llBtnRotate=findViewById(R.id.llBtnRotate);
        ivImagen=findViewById(R.id.ivImagen);
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);
        btnRotacionleft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImagen.setRotation(ivImagen.getRotation()-90);
            }
        });

        btnRotacionRidth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ivImagen.setRotation(ivImagen.getRotation()+90);
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GuardarImagenIne();
            }
        });

        btnCamara.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                isCamara=true;
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    activarCamara();

                }else{
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, 1);
                }
            }
        });

        btnGaleria.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                isCamara=false;
                if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
                    abrirGaleria();

                }else{
                    requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }
            }
        });
    }

    private void abrirGaleria() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 100);
    }


    private void activarCamara(){
        Intent openCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //openCamera.putExtra(MediaStore.EXTRA_SCREEN_ORIENTATION, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        startActivityForResult(openCamera, 10);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(isCamara)
            activarCamara();
        else
            abrirGaleria();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        System.out.println(requestCode + " -----------------");

        if (data != null) {
            ivImagen.getLayoutParams().width = 400;
            ivImagen.getLayoutParams().height = 700;
            llBtnRotate.setVisibility(View.VISIBLE);
            Bitmap bitmap;
            ivImagen.setRotation(0);
            if (isCamara) {
                bitmap = (Bitmap) data.getExtras().get("data");
                ivImagen.setImageBitmap(bitmap);
                dataUrlImagen=new MiComplemento().traerByteImagenesFromIV(ivImagen,context);
            } else {
                Uri selectedImage = data.getData();
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImage);
                    ivImagen.setImageBitmap(bitmap);
                    dataUrlImagen=new MiComplemento().traerByteImagenesFromIV(ivImagen,context);
                } catch (IOException e) {
                }
            }
        }
    }




    private void GuardarImagenIne() {
        if (dataUrlImagen != null) {
            System.out.println("sajdbasdbjasd");
            btnGuardar.setEnabled(false);
            Map<String,String>mapString=new HashMap<String, String>() ;
            mapString.put("usuarioId",new MiComplemento().getDatos(context, "usuarioId")+"");
            mapString.put("dataUrlImagen",dataUrlImagen);

            new CommitRetrofit().GuardarImagenIne(new RetrofitResponseListener() {

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
                    Log.e("TAG", "Entro al onFailure " + cadena);

                }
            }, api,mapString);

        } else {
            new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Oops...")
                    .setContentText(getResources().getString(R.string.no_se_ha_seleccionado_imagen))
                    .show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        onBackPressed();
        return false;
    }

}
