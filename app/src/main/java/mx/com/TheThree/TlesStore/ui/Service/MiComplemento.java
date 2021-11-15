package mx.com.TheThree.TlesStore.ui.Service;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MiComplemento {

    public void setDatos(Context context, String nombre, String dato) {
        SharedPreferences preferencias=context.getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferencias.edit();
        editor.putString(nombre, dato);
        editor.commit();
    }

    public String traerByteImagenesFromIV(ImageView iv, Context context) {
        String imagen="";

        try {

            //Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), myUri);
            Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();

            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                imagen = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imagen;
    }


    public String traerByteImagenes(Uri myUri, Context context) {
        String imagen="";

        try {
            //vImagenEscondida.setImageURI(myUri);
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(context.getContentResolver(), myUri);
            //Bitmap bitmap = ((BitmapDrawable) ivImagenEscondida.getDrawable()).getBitmap();

            if (bitmap != null) {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                imagen = Base64.encodeToString(byteArray, Base64.DEFAULT);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return imagen;
    }

    public String getDatos(Context context,String nombre) {
        SharedPreferences sharedPref = context.getSharedPreferences("Credenciales",Context.MODE_PRIVATE);
        return sharedPref.getString(nombre, "");
    }

    public void focusEditText(EditText editText, Context context) {
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
    }

    public boolean isCorreo(String email){
        // Patrón para validar el email
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(email);
        return mather.find();
    }

    public boolean isNumero(String telefono){
        return telefono.matches("^[0-9]{10}$");
    }
}
