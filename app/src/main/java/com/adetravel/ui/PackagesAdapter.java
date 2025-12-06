package com.adetravel.ui;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.adetravel.models.PackageSummary;
import com.example.myapplication.R;
import java.util.List;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.VH> {
    public interface OnItem { void open(PackageSummary item); }
    List<PackageSummary> data;
    OnItem onItem;
    public PackagesAdapter(List<PackageSummary> d, OnItem o) { data = d; onItem = o; }
    @Override public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_package, parent, false);
        return new VH(v);
    }
    @Override public void onBindViewHolder(VH holder, int position) {
        PackageSummary it = data.get(position);
        holder.tvTitle.setText(it.titulo);
        holder.tvPrice.setText(String.valueOf(it.precio));
        holder.itemView.setOnClickListener(v -> onItem.open(it));
    }
    @Override public int getItemCount() { return data.size(); }
    static class VH extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice;
        VH(View v){ super(v); tvTitle=v.findViewById(R.id.tvTitle); tvPrice=v.findViewById(R.id.tvPrice); }
    }
}
