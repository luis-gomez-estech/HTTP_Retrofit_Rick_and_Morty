package com.luisgomez.http_retrofit_rick_and_morty;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    //URL de donde va a leer la API

    private final String baseUrl ="https://rickandmortyapi.com/";

    RMAdapter rmAdapter;


    // para a tarea de obtener Post, usé ArrayList, pero aqui he tenid que usar List
    ArrayList<Result> listaRM = new ArrayList<>();
    RecyclerView rvResult;
    private ProgressBar mProgressBar; //  Esta vez se me ha  ocurrido añadirle una barra de progreso circulas que se va a activar antes de abrir datos


    Button recibirDatosGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mProgressBar=(ProgressBar)findViewById(R.id.progress_bar);


        recibirDatosGet = findViewById(R.id.btnRecibirDatosGet);
        recibirDatosGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                loadJSON(v);
            }
        });
    }



    private void loadJSON(View v) {

        //Al pulsar el boton recibir datos, pasamos al metodo loadJson,
        // y ahora se activa la barra de progreso a cual va a estar visible el tiempo que tarde en verse los datos de la API

        mProgressBar.setVisibility(View.VISIBLE);


        rvResult = findViewById(R.id.rvCharacters);

        final RMAdapter[] rmAdapter = {new RMAdapter(v.getContext(), listaRM)};

        rvResult.setAdapter(rmAdapter[0]);


        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());

        rvResult.setLayoutManager(llm);


        DividerItemDecoration itemDecoration = new DividerItemDecoration(rvResult.getContext(),llm.getOrientation());
        rvResult.addItemDecoration(itemDecoration);

        RestClient restClient = RetrofitClient.getClient(baseUrl).create(RestClient.class);

        Call<Example> listCall = restClient.getData();

        listCall.enqueue(new Callback<Example>() {

              @Override
              public void onResponse(Call<Example> call, Response<Example> response) {
                  
                  if (response.isSuccessful()  && response.body()!=null){

                         Example example = response.body();

                         listaRM.addAll(example.getResults());

                         rmAdapter[0].notifyDataSetChanged();
                  }


              }

              @Override
              public void onFailure(Call<Example> call, Throwable t) {

              //Desactivo la barra
               mProgressBar.setVisibility(View.GONE);

                    Toast.makeText(MainActivity.this,"Ha surgido un problema y no es posible acceder a la API!",Toast.LENGTH_SHORT).show();
               }
        });
    }


}
