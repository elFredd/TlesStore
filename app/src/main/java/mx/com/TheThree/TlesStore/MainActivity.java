package mx.com.TheThree.TlesStore;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mx.com.TheThree.TlesStore.databinding.ActivityMainBinding;
import mx.com.TheThree.TlesStore.ui.ApiRest.AdaptadorRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.ApiRest;
import mx.com.TheThree.TlesStore.ui.ApiRest.CommitRetrofit;
import mx.com.TheThree.TlesStore.ui.ApiRest.RetrofitResponseListener;
import mx.com.TheThree.TlesStore.ui.FragmentApp.InicioFragment;
import mx.com.TheThree.TlesStore.ui.FragmentApp.ProductosFragment;
import mx.com.TheThree.TlesStore.ui.Service.MiComplemento;
import mx.com.TheThree.TlesStore.ui.home.HomeFragment;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;

    //InicioFragment inicioFragment;
    //ComidaFragment comidaFragment;
    //SupermercadoFragment supermercadoFragment;
    ProductosFragment productosFragment;
    InicioFragment inicioFragment;
    HomeFragment homeFragment;



    private Context context;
    private ApiRest api;
    private Retrofit retrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        context=this;
        retrofit = new AdaptadorRetrofit().getAdaptadorLocal();
        api = retrofit.create(ApiRest.class);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);
        binding.appBarMain.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;





        ConsultaUsuarioInicio(navigationView);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_inicio,R.id.nav_producto, R.id.nav_gallery/*, R.id.nav_supermercado,R.id.nav_farmacia*/)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    private void ConsultaUsuarioInicio(NavigationView navigationView) {
        new CommitRetrofit().ConsultaUsuarioInicio(new RetrofitResponseListener() {

            @Override
            public void onSuccess(Object object) throws JSONException {
                String cadena=object.toString();
                JSONObject obj=new JSONObject(cadena);
                if(obj.isNull("Error")){
                    View view=navigationView.getHeaderView(0);
                    TextView tvNombreUsuario= view.findViewById(R.id.tvNombreUsuario);
                    TextView tvPuntos= view.findViewById(R.id.tvPuntos);
                    tvNombreUsuario.setText(getResources().getString(R.string.hola)+"! "+obj.getString("Nombre"));
                    tvPuntos.setText(obj.getString("Puntos")+" "+getResources().getString(R.string.puntos));
                }else{
                    new MiComplemento().setDatos(context,"usuarioId","");

                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText(getResources().getString(R.string.el_usuario_fue_dado_de_baja_comunicate_con_nosotros_para_resolver_el_problema))
                            .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                @Override
                                public void onClick(SweetAlertDialog sweetAlertDialog) {
                                    Intent intent=new Intent(context, PrincipalActivity.class);
                                    startActivity(intent);
                                    finish();
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener onNavigationItemSelectedListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull @NotNull MenuItem item) {
                    switch (item.getItemId())
                    {
                        case R.id.nav_producto:
                            productosFragment = new ProductosFragment();
                            loadFragment(productosFragment);
                            return true;
                        case R.id.nav_home:
                            homeFragment=new HomeFragment();
                            loadFragment(homeFragment);
                            return true;
                        case R.id.nav_inicio:
                            inicioFragment=new InicioFragment();
                            loadFragment(inicioFragment);
                            return true;
                       /* case R.id.nav_supermercado:
                            supermercadoFragment=new SupermercadoFragment();
                            loadFragment(supermercadoFragment);
                            return true;
                        case R.id.nav_farmacia:
                            farmaciaFragment=new FarmaciaFragment();
                            loadFragment(farmaciaFragment);
                            return true;*/

                    }
                    return false;
                }
            };

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public void loadFragment(Fragment fragment)
    {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.nav_host_fragment_container, fragment);
        transaction.commit();
    }


}