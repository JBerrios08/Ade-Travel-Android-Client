package com.adetravel.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.adetravel.models.Destination;
import com.example.myapplication.R;
import java.util.List;

public class DestinationsAdapter extends RecyclerView.Adapter<DestinationsAdapter.VH> {
    List<Destination> data;
    public DestinationsAdapter(List<Destination> d) { data = d; }
    @Override public VH onCreateViewHolder(android.view.ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_destination, parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(VH holder, int position) {
        Destination it = data.get(position);
        holder.tvName.setText(it.nombre);
        holder.tvDesc.setText(it.descripcion);
    }
    @Override public int getItemCount() { return data.size(); }
    static class VH extends RecyclerView.ViewHolder {
        TextView tvName, tvDesc;
        VH(View v){ super(v); tvName=v.findViewById(R.id.tvName); tvDesc=v.findViewById(R.id.tvDesc); }
    }
}
