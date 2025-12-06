package com.adetravel.ui;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.adetravel.api.ApiService;
import com.adetravel.api.RetrofitClient;
import com.adetravel.models.PackageDetail;
import com.adetravel.utils.SessionManager;
import com.adetravel.client.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.HashMap;

public class PackageDetailActivity extends AppCompatActivity {
    TextView tvTitle, tvDesc, tvPrice;
    ProgressBar progress;
    Button btnReserve;
    int packageId;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_detail);
        tvTitle = findViewById(R.id.tvTitle);
        tvDesc = findViewById(R.id.tvDesc);
        tvPrice = findViewById(R.id.tvPrice);
        progress = findViewById(R.id.progressBar);
        btnReserve = findViewById(R.id.btnReserve);
        session = new SessionManager(this);
        packageId = getIntent().getIntExtra("package_id", -1);
        if (packageId == -1) {
            Toast.makeText(this, "Paquete no válido", Toast.LENGTH_SHORT).show();
            finish();
            return;
        }
        load();
        btnReserve.setOnClickListener(v -> {
            if (session.getToken()==null) {
                Toast.makeText(this, "Debe iniciar sesión para reservar", Toast.LENGTH_SHORT).show();
                return;
            }
            // open ReservationActivity with package id
            android.content.Intent i = new android.content.Intent(this, ReservationActivity.class);
            i.putExtra("package_id", packageId);
            startActivity(i);
        });
    }

    private void load() {
        progress.setVisibility(View.VISIBLE);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        api.getPackageDetail(packageId).enqueue(new Callback<PackageDetail>() {
            @Override
            public void onResponse(Call<PackageDetail> call, Response<PackageDetail> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body()!=null) {
                    PackageDetail p = response.body();
                    tvTitle.setText(p.titulo);
                    tvDesc.setText(p.descripcion);
                    tvPrice.setText(String.valueOf(p.precio));
                } else {
                    Toast.makeText(PackageDetailActivity.this, "Error al cargar detalle", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PackageDetail> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(PackageDetailActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
