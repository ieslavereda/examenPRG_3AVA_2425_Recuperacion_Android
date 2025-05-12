package es.ieslavereda.examen_3eva_recuperacion_2425;

import static android.view.View.INVISIBLE;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import es.ieslavereda.examen_3eva_recuperacion_2425.model.Casa;
import es.ieslavereda.examen_3eva_recuperacion_2425.model.Personaje;

public class DetailActivity extends AppCompatActivity {

    private ImageView ivDetailEscudo;
    private Button buttonAceptar, buttonCancelar;
    private EditText etNombre, etCasa;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.detail_layout);

        ivDetailEscudo = findViewById(R.id.ivDetailEscudo);
        buttonAceptar = findViewById(R.id.buttonAceptar);
        buttonCancelar = findViewById(R.id.buttonCancelar);
        etNombre = findViewById(R.id.etNombre);
        etCasa = findViewById(R.id.etCasa);
        spinner = findViewById(R.id.spinner);
        etCasa.setEnabled(false);

        Personaje personaje = null;
        if(getIntent().getExtras()!=null)
            personaje = (Personaje) getIntent().getExtras().getSerializable("personaje");

        ArrayAdapter<Casa> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,Casa.values());
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                etCasa.setText(Casa.values()[i].getCasa());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        if(personaje!=null){
            ivDetailEscudo.setImageResource(personaje.getCasa().getEscudo());
            spinner.setSelection(personaje.getCasa().ordinal());
            etNombre.setEnabled(false);
            etNombre.setText(personaje.getNombre());
            buttonAceptar.setVisibility(INVISIBLE);
            spinner.setEnabled(false);
            spinner.setVisibility(INVISIBLE);
        }

        buttonCancelar.setOnClickListener(view -> {
            Intent intent = new Intent();
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        buttonAceptar.setOnClickListener(view -> {
            Intent intent = new Intent();

            Personaje personaje1 = new Personaje(etNombre.getText().toString(), Casa.devolverCasa(etCasa.getText().toString()));
            intent.putExtra("personaje",personaje1);
            setResult(RESULT_OK, intent);
            finish();
        });



    }


}