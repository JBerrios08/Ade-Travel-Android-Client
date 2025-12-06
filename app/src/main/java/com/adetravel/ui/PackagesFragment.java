package com.adetravel.ui;
import android.content.Intent;
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
import com.adetravel.models.PackageSummary;
import com.example.myapplication.R;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PackagesFragment extends Fragment {
    RecyclerView rv;
    ProgressBar progress;
    public PackagesFragment() {}
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_packages, container, false);
        rv = v.findViewById(R.id.rvPackages);
        progress = v.findViewById(R.id.progressBar);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        load();
        return v;
    }
    private void load() {
        progress.setVisibility(View.VISIBLE);
        ApiService api = RetrofitClient.getClient().create(ApiService.class);
        api.getPackages().enqueue(new Callback<List<PackageSummary>>() {
            @Override
            public void onResponse(Call<List<PackageSummary>> call, Response<List<PackageSummary>> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful() && response.body()!=null) {
                    rv.setAdapter(new PackagesAdapter(response.body(), item -> {
                        // open detail activity
                        Intent i = new Intent(getContext(), PackageDetailActivity.class);
                        i.putExtra("package_id", item.id);
                        startActivity(i);
                    }));
                } else {
                    Toast.makeText(getContext(), "Error al cargar paquetes", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<List<PackageSummary>> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(getContext(), "Error: "+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
