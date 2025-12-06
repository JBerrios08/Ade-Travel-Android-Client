package com.adetravel.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.adetravel.api.ApiService;
import com.adetravel.api.RetrofitClient;
import com.adetravel.models.Reservation;
import com.adetravel.utils.SessionManager;
import com.adetravel.client.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyReservationsFragment extends Fragment {
    RecyclerView rv;
    ProgressBar progress;
    SessionManager session;
    public MyReservationsFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_reservations, container, false);
        rv = v.findViewById(R.id.rvMyReservations);
        progress = v.findViewById(R.id.progressBar);
        session = new SessionManager(getContext());
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        load();
        return v;
    }
    private void load() {
        progress.setVisibility(View.VISIBLE);
        String token = session.getToken();
        if (token == null) {
            progress.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Debe iniciar sesión", Toast.LENGTH_SHORT).show();
            return;
        }
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        api.getMyReservations("Token "+token).enqueue(new Callback<List<Reservation>>() {
            @Override
            public void onResponse(Call<List<Reservation>> call, Response<List<Reservation>> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body()!=null) {
                    rv.setAdapter(new ReservationsAdapter(response.body()));
                } else {
                    Toast.makeText(getContext(), "Error al cargar sus reservas", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<Reservation>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
