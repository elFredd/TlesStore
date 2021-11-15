package mx.com.TheThree.TlesStore.ui.ApiRest;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiRest {

    @GET("ApiTlees/Acciones/PuebaUsuario")
    Call<ResponseBody> preuba();

    @POST("api/login/RegistrarUsuario")
    Call<ResponseBody> RegistrarUsuario(@Query("Nombre") String nombre,@Query("Apellido") String apellido,@Query("CorreoOTelefono") String correoOTelefono,@Query("Password") String password);

    @POST("api/login/IniciarSesion")
    Call<ResponseBody> IniciarSesion(@Query("CorreoOTelefono")String correoOTelefono,@Query("Password") String password);

    @POST("api/usuario/ConsultaUsuarioInicio")
    Call<ResponseBody> ConsultaUsuarioInicio(@Query("usuarioId")int usuarioId);

    @POST("api/store/VerificarPasosRegistroTienda")
    Call<ResponseBody> VerificarPasosRegistroTienda(@Query("usuarioId")int usuarioId);

    @POST("api/store/GuardarNombreStoreRegistrar")
    Call<ResponseBody> GuardarNombreStoreRegistrar(@Query("usuarioId")int usuarioId,@Query("Nombre")String nombre);

    @POST("api/store/GuardarNombreTiendaStoreRegistrar")
    Call<ResponseBody> GuardarNombreTiendaStoreRegistrar(@Query("usuarioId")int usuarioId,@Query("NombreTienda")String nombre);

    @POST("api/store/AceptarAcuerdoLegal")
    Call<ResponseBody> AceptarAcuerdoLegal(@Query("usuarioId") int usuarioId);

    @POST("api/store/TraerAcuerdoLegarStore")
    Call<ResponseBody> TraerAcuerdoLegarStore();

    //@POST("api/store/GuardarImagenIne")
    //Call<ResponseBody> GuardarImagenIne(@Query("usuarioId")int usuarioId,@Query("dataUrlImagen") String dataUrlImagen);

    @POST("http://192.168.0.65:8080/Acciones/GuardarImagenIne")
    @FormUrlEncoded
    Call<ResponseBody> GuardarImagenIne(@FieldMap Map<String,String> map);

    @POST("http://192.168.0.65:8080/Acciones/GuardarImagenFotoPerfil")
    @FormUrlEncoded
    Call<ResponseBody> GuardarImagenFotoPerfil(@FieldMap Map<String, String> mapString);
}
