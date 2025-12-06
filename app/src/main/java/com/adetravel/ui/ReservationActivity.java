package com.adetravel.ui;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.adetravel.api.ApiService;
import com.adetravel.api.RetrofitClient;
import com.adetravel.models.Reservation;
import com.adetravel.utils.SessionManager;
import com.example.myapplication.R;
import java.util.HashMap;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ReservationActivity extends AppCompatActivity {
    EditText etName, etPax, etDate;
    Button btnSend;
    ProgressBar progress;
    int packageId;
    SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);
        etName = findViewById(R.id.etName);
        etPax = findViewById(R.id.etPax);
        etDate = findViewById(R.id.etDate);
        btnSend = findViewById(R.id.btnSend);
        progress = findViewById(R.id.progressBar);
        packageId = getIntent().getIntExtra("package_id", -1);
        session = new SessionManager(this);

        btnSend.setOnClickListener(v -> submit());
    }

    private void submit() {
        String name = etName.getText().toString().trim();
        String pax = etPax.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        if (name.isEmpty() || pax.isEmpty() || date.isEmpty()) {
            Toast.makeText(this, "Complete los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        progress.setVisibility(View.VISIBLE);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        HashMap<String, Object> body = new HashMap<>();
        body.put("paquete", packageId);
        body.put("cliente_nombre", name);
        body.put("pax", Integer.parseInt(pax));
        body.put("fecha", date);
        String token = session.getToken();
        api.createReservation("Token "+token, body).enqueue(new Callback<Reservation>() {
            @Override
            public void onResponse(Call<Reservation> call, Response<Reservation> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    Toast.makeText(ReservationActivity.this, "Reserva creada", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(ReservationActivity.this, "Error al crear reserva", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Reservation> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(ReservationActivity.this, "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
