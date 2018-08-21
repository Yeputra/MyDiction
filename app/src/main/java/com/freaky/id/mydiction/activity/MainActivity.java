package com.freaky.id.mydiction.activity;

import android.database.SQLException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.freaky.id.mydiction.R;
import com.freaky.id.mydiction.adapter.KamusAdapter;
import com.freaky.id.mydiction.data.model.Kamus;
import com.freaky.id.mydiction.data.KamusContract;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private KamusAdapter adapter;
    private RecyclerView rv;
    private KamusContract contract;
    private ArrayList<Kamus> kamuslist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv_kamus);
        contract = new KamusContract(this);
        getSupportActionBar().setTitle(String.format(getResources().getString(R.string.eng)));
        setupRecycler();
        loadDataEnglish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.eng_idn:
                getSupportActionBar().setTitle(String.format(getResources().getString(R.string.eng)));
                setupRecycler();
                loadDataEnglish();
                break;
            case R.id.idn_eng:
                getSupportActionBar().setTitle(String.format(getResources().getString(R.string.idn)));
                setupRecycler();
                loadDataIndo();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupRecycler() {
        adapter = new KamusAdapter();
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }

    private void loadDataEnglish() {
        try {
            contract.open();
            kamuslist = contract.getAllData();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            contract.close();
        }
        adapter.setData(kamuslist);
    }

    private void loadDataIndo() {
        try {
            contract.open();
            kamuslist = contract.getAllDataIndo();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            contract.close();
        }
        adapter.setData(kamuslist);
    }
}
