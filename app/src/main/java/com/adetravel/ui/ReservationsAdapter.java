package com.adetravel.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.adetravel.models.Reservation;
import com.example.myapplication.R;
import java.util.List;

public class ReservationsAdapter extends RecyclerView.Adapter<ReservationsAdapter.VH> {
    List<Reservation> data;
    public ReservationsAdapter(List<Reservation> d) { data = d; }
    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_reservation, parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(VH holder, int position) {
        Reservation it = data.get(position);
        holder.tvTitle.setText("Paquete: "+it.paquete+" - "+it.cliente_nombre);
        holder.tvDate.setText(it.fecha);
    }
    @Override public int getItemCount() { return data.size(); }
    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate;
        VH(View v){ super(v); tvTitle=v.findViewById(R.id.tvTitle); tvDate=v.findViewById(R.id.tvDate); }
    }
}
