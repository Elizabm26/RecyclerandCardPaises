package Adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.recyclerandcard.R;

import java.util.List;

import Model.Pais;

public class PaisAdapter extends RecyclerView.Adapter<PaisAdapter.PaisViewHolder>{
    private Context ctx;
    private List<Pais> lstPaises;

    public PaisAdapter(Context mctx, List<Pais> paises){
        this.lstPaises = paises;
        ctx = mctx;
    }
    @Override
    public PaisAdapter.PaisViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(ctx);
        View view = inflater.inflate(R.layout.lyt_pais, null);
        return new PaisAdapter.PaisViewHolder(view);
    }

    @Override
    public void onBindViewHolder(PaisAdapter.PaisViewHolder holder, int position) {
        Pais paises = lstPaises.get(position);
        holder.txtTituloPais.setText(paises.getTitulo_Pais());
        holder.txtCapital.setText(paises.getCapital());
        holder.txtPrefijo.setText(paises.getPrefijo());
        Glide.with(ctx)
                .load(paises.getUrlBandera())
                .into(holder.imgUrlBandera);
    }

    @Override
    public int getItemCount() {
        return lstPaises.size();
    }



    class PaisViewHolder extends RecyclerView.ViewHolder{
        TextView txtTituloPais, txtCapital, txtPrefijo;
        ImageView imgUrlBandera;
        public PaisViewHolder(View itemView) {
            super(itemView);
            txtTituloPais = itemView.findViewById(R.id.txtPais);
            txtCapital = itemView.findViewById(R.id.txtCapital);
            txtPrefijo = itemView.findViewById(R.id.txtPrefijo);
            imgUrlBandera = itemView.findViewById(R.id.imgBandera);
        }
    }
}
