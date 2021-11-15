package mx.com.TheThree.TlesStore.ui.ApiRest;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommitRetrofit {

    public void Prueba(RetrofitResponseListener retrofitResponseListener, ApiRest api) {
        Call<ResponseBody> call = api.preuba();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {
                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }


    public void RegistrarUsuario(RetrofitResponseListener retrofitResponseListener, ApiRest api, String nombre, String password, String apellido, String correoOTelefono) {
        Call<ResponseBody> call = api.RegistrarUsuario(nombre,apellido,correoOTelefono,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {
                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void IniciarSesion(RetrofitResponseListener retrofitResponseListener, ApiRest api, String password, String correoOTelefono) {
        Call<ResponseBody> call = api.IniciarSesion(correoOTelefono,password);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {
                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void ConsultaUsuarioInicio(RetrofitResponseListener retrofitResponseListener, ApiRest api, int usuarioId) {
        Call<ResponseBody> call = api.ConsultaUsuarioInicio(usuarioId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {
                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void VerificarPasosRegistroTienda(RetrofitResponseListener retrofitResponseListener, ApiRest api, int usuarioId) {
        Call<ResponseBody> call = api.VerificarPasosRegistroTienda(usuarioId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {
                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                }
                else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void GuardarNombreStoreRegistrar(RetrofitResponseListener retrofitResponseListener, ApiRest api, int usuarioId, String nombre) {
        Call<ResponseBody> call = api.GuardarNombreStoreRegistrar(usuarioId,nombre);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void GuardarNombreTiendaStoreRegistrar(RetrofitResponseListener retrofitResponseListener, ApiRest api, int usuarioId, String nombreTienda) {
        Call<ResponseBody> call = api.GuardarNombreTiendaStoreRegistrar(usuarioId,nombreTienda);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void AceptarAcuerdoLegal(RetrofitResponseListener retrofitResponseListener, ApiRest api, int usuarioId) {
        Call<ResponseBody> call = api.AceptarAcuerdoLegal(usuarioId);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void TraerAcuerdoLegarStore(RetrofitResponseListener retrofitResponseListener, ApiRest api) {
        Call<ResponseBody> call = api.TraerAcuerdoLegarStore();
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void GuardarImagenIne(RetrofitResponseListener retrofitResponseListener, ApiRest api, Map<String,String> mapString) {
        Call<ResponseBody> call = api.GuardarImagenIne(mapString);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }

    public void GuardarImagenFotoPerfil(RetrofitResponseListener retrofitResponseListener, ApiRest api, Map<String, String> mapString) {
        Call<ResponseBody> call = api.GuardarImagenFotoPerfil(mapString);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful() && response.body()!=null )
                {

                    try {
                        retrofitResponseListener.onSuccess(response.body().string());
                    } catch (JSONException | IOException e) {
                        e.printStackTrace();
                    }
                } else
                {
                    String e="Cayó conexión Tarjeta Saldo Cuenta";
                    retrofitResponseListener.onFailure(e);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                retrofitResponseListener.onFailure(t);
            }
        });
    }
}
