package es.ieslavereda.examen_3eva_recuperacion_2425;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import es.ieslavereda.examen_3eva_recuperacion_2425.model.Casa;
import es.ieslavereda.examen_3eva_recuperacion_2425.model.Personaje;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerView;
    private List<Personaje> personajes;
    private FloatingActionButton addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerView);
        addButton = findViewById(R.id.addButton);

        personajes = new ArrayList<>();
        personajes.add(new Personaje("Tyrion Lannister", Casa.LANNISTER));
        personajes.add(new Personaje("Cersei Lannister", Casa.LANNISTER));
        personajes.add(new Personaje("Daenerys Targaryen", Casa.TARGARYEN));
        personajes.add(new Personaje("Jon Nieve", Casa.STARK));
        personajes.add(new Personaje("Sansa Stark", Casa.STARK));
        personajes.add(new Personaje("Arya Stark", Casa.STARK));
        personajes.add(new Personaje("Jaime Lannister", Casa.LANNISTER));
        personajes.add(new Personaje("Theon Greyjoy", Casa.GREYJOY));
        personajes.add(new Personaje("Yara Greyjoy", Casa.GREYJOY));
        personajes.add(new Personaje("Euron Greyjoy", Casa.GREYJOY));
        personajes.add(new Personaje("Robert Arryn", Casa.ARRYN));
        personajes.add(new Personaje("Lisa Arryn", Casa.ARRYN));
        personajes.add(new Personaje("Edmure Tully", Casa.TULLY));
        personajes.add(new Personaje("Ser Brynden Tully", Casa.TULLY));
        personajes.add(new Personaje("Tommen Baratheon", Casa.BARATHEON));
        personajes.add(new Personaje("Renly Baratheon", Casa.BARATHEON));
        personajes.add(new Personaje("Myrcella Baratheon", Casa.BARATHEON));
        personajes.add(new Personaje("Brandon Stark", Casa.STARK));
        personajes.add(new Personaje("Catelyn Stark", Casa.STARK));
        personajes.add(new Personaje("Robb Stark", Casa.STARK));

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        AdaptadorRV adaptadorRV = new AdaptadorRV(this,personajes,this);
        recyclerView.setAdapter(adaptadorRV);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,
                ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                int posIni = viewHolder.getAdapterPosition();
                int posFin = target.getAdapterPosition();
                Personaje personaje = personajes.remove(posIni);
                personajes.add(posFin,personaje);
                recyclerView.getAdapter().notifyItemMoved(posIni,posFin);
                return true;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int posicion = viewHolder.getAdapterPosition();
                Personaje personaje = personajes.remove(posicion);
                recyclerView.getAdapter().notifyItemRemoved(posicion);
                Snackbar.make(recyclerView,"Borrado: " + personaje.getNombre(),
                                Snackbar.LENGTH_LONG)
                        .setAction("Deshacer", view -> {
                            //volvais a poner el país borrado
                            personajes.add(posicion,personaje);
                            recyclerView.getAdapter().notifyItemInserted(posicion);
                        })
                        .show();
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if(result.getResultCode()==RESULT_CANCELED){
                        Toast.makeText(this,"Cancelado por el usuario",Toast.LENGTH_LONG).show();
                    } else if (result.getResultCode()==RESULT_OK){
                        Personaje personaje = (Personaje) result.getData().getExtras().getSerializable("personaje");
                        Toast.makeText(this,"Personaje añadido: " + personaje.getNombre(),Toast.LENGTH_LONG).show();
                        personajes.add(personaje);
                        adaptadorRV.notifyItemInserted(personajes.size()-1);
                    }
                }
        );


        addButton.setOnClickListener(view -> {
            Intent intent = new Intent(this, DetailActivity.class);
            activityResultLauncher.launch(intent);
        });


    }

    @Override
    public void onClick(View view) {
        int posicion = recyclerView.getChildAdapterPosition(view);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("personaje", personajes.get(posicion));
        startActivity(intent);
    }
}