package mx.com.TheThree.TlesStore.ui.FragmentApp;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import mx.com.TheThree.TlesStore.R;

/**
 * @created 12/11/2021 - 08:11 p. m.
 * @project TlesStore
 * @autor alfre
 */
public class ProductosFragment extends Fragment {
    private View root;
    private LocationManager locationManager;


    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_productos, container, false);
        locationManager = (LocationManager) root.getContext().getSystemService(Context.LOCATION_SERVICE);
        return root;
    }
}
