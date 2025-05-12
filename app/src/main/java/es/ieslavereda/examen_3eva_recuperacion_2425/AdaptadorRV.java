package es.ieslavereda.examen_3eva_recuperacion_2425;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.ieslavereda.examen_3eva_recuperacion_2425.model.Personaje;

public class AdaptadorRV extends RecyclerView.Adapter<AdaptadorRV.ViewHolder> {

    private List<Personaje> personajes;
    private Context context;
    private View.OnClickListener onClickListener;
    private LayoutInflater inflater;

    public AdaptadorRV(Context context, List<Personaje> personajes, View.OnClickListener onClickListener){
        this.context=context;
        this.personajes=personajes;
        this.onClickListener=onClickListener;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.recycler_item,parent,false);
        view.setOnClickListener(onClickListener);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Personaje personaje = personajes.get(position);
        holder.tvCasa.setText(personaje.getCasa().getCasa());
        holder.tvNombre.setText(personaje.getNombre());
        holder.ivEscudo.setImageResource(personaje.getCasa().getEscudo());
    }

    @Override
    public int getItemCount() {
        return personajes.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivEscudo;
        private TextView tvNombre, tvCasa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.ivEscudo = itemView.findViewById(R.id.ivEscudo);
            this.tvNombre = itemView.findViewById(R.id.tvNombre);
            this.tvCasa = itemView.findViewById(R.id.tvCasa);
        }
    }
}
