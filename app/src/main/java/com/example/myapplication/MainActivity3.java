package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity3 extends AppCompatActivity  {
    List<Vacance> vacances = new ArrayList<Vacance>();
    private ListView mListView;
    private VacanceAdapter adapter;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        mListView = findViewById(R.id.list);
        adapter = new VacanceAdapter(MainActivity3.this, vacances);
        mListView.setAdapter(adapter);




        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity3.this, MainActivity4.class);
                Vacance item = vacances.get(position);
                String note = item.description;
                String time = item.time;
                intent.putExtra("note", note);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });


        mListView.setOnItemLongClickListener((adapterView, view, position, id) -> {

            AlertDialog.Builder confirm = new AlertDialog.Builder(this);
            confirm.setTitle("Suppression");
            confirm.setIcon(android.R.drawable.ic_dialog_alert);
            confirm.setMessage("Vous confirmez la suppression ?");
            confirm.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int idBtn) {
                    vacances.remove(position);
                    adapter.notifyDataSetChanged();
                }
            });
            confirm.setNegativeButton(android.R.string.cancel, null);
            confirm.show();
            return true;
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu:
                final Dialog dialog = new Dialog(this);
                dialog.setContentView(R.layout.dialogue);
                dialog.setTitle("Prendre Note");
                Button btnValider = (Button) dialog.findViewById(R.id.dialog_btn_valider);
                dialog.show();

                btnValider.setOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {

                        EditText edit=(EditText)dialog.findViewById(R.id.dialog_libelle);
                        String text=edit.getText().toString();
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
                        String currentTime = sdf.format(new Date());
                        vacances.add(new Vacance("BENNACER Nor Farah",text, R.drawable.olaf,currentTime));
                        adapter.notifyDataSetChanged();
                        dialog.dismiss();


                    }  });

                return true;
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("vacances", new ArrayList<>(vacances));
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        vacances = (List<Vacance>) savedInstanceState.getSerializable("vacances");
        VacanceAdapter adapter = new VacanceAdapter(MainActivity3.this, vacances);
        mListView.setAdapter(adapter);

    }
}
