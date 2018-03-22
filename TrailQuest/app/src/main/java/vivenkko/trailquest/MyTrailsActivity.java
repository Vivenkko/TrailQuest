package vivenkko.trailquest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vivenkko.trailquest.models.Trail;
import vivenkko.trailquest.retrofit.TrailQuestController;
import vivenkko.trailquest.retrofit.TrailQuestServiceGenerator;

public class MyTrailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_trails);

        //String token = getArguments().getString("token");

        TrailQuestController api = TrailQuestServiceGenerator.createService(TrailQuestController.class);

        Call<List<Trail>> call = api.listMyTrails("2231");

        call.enqueue(new Callback<List<Trail>>() {
            @Override
            public void onResponse(Call<List<Trail>> call, Response<List<Trail>> response) {
                if (response.isSuccessful()){
                    List<Trail> trails = response.body();

                    Toast.makeText(getApplication(), "Cargando tus rutas", Toast.LENGTH_SHORT).show();

                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(getApplication(), MyTrailsActivity.class);
                    bundle.putString("key", trails.get(0).toString());//OJO
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<List<Trail>> call, Throwable t) {
                Log.d("problema", "error");
            }
        });

    }
}
