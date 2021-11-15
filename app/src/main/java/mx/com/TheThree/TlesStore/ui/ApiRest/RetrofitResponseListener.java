package mx.com.TheThree.TlesStore.ui.ApiRest;

import org.json.JSONException;

import java.util.List;

public interface RetrofitResponseListener {

    void onSuccess(Object object) throws JSONException;

    void onSuccess(List listElements);

    void onFailure(Throwable throwable);

    void onFailure(String cadena);
}
