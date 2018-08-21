package com.freaky.id.mydiction.activity;

import android.content.Intent;
import android.content.res.Resources;
import android.database.SQLException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.freaky.id.mydiction.AppPref;
import com.freaky.id.mydiction.R;
import com.freaky.id.mydiction.data.model.Kamus;
import com.freaky.id.mydiction.data.KamusContract;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class SplashScreenActivity extends AppCompatActivity {

    ProgressBar pb;
    TextView desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        pb = findViewById(R.id.progressBar);
        desc = findViewById(R.id.desc);
        new LoadData().execute();
    }

    private class LoadData extends AsyncTask<Void, Integer, Void> {
        KamusContract kamusContract;
        AppPref appPreference;

        @Override
        protected void onPreExecute() {
            kamusContract = new KamusContract(SplashScreenActivity.this);
            appPreference = new AppPref(SplashScreenActivity.this);
        }

        @Override
        protected Void doInBackground(Void... params) {
            Boolean firstRun = appPreference.getFirstRun();
            if (firstRun) {
                ArrayList<Kamus> kamusEnglish = preLoadRaw(R.raw.english_indonesia);
                ArrayList<Kamus> kamusIndonesia = preLoadRaw(R.raw.indonesia_english);


                try {
                    kamusContract.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                kamusContract.insertTransaction(kamusEnglish);

                kamusContract.insertTransactionIndo(kamusIndonesia);

                kamusContract.close();
                appPreference.setFirstRun(false);

            } else {
                    desc.setVisibility(View.INVISIBLE);
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            Intent i = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(i);

            finish();
        }
    }

    public ArrayList<Kamus> preLoadRaw(int data) {
        ArrayList<Kamus> kamuss = new ArrayList<>();
        BufferedReader reader;
        try {
            Resources res = getResources();
            InputStream raw_dict = res.openRawResource(data);

            reader = new BufferedReader(new InputStreamReader(raw_dict));
            String line = null;
            do {
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                Kamus kamus;
                kamus = new Kamus(splitstr[0], splitstr[1]);
                kamuss.add(kamus);
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kamuss;
    }

}


