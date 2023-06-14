package com.example.cocostream_v3;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class Home extends AppCompatActivity {

    AutoCompleteTextView autoCompleteTextViewTW, autoCompleteTextViewYT;
    ArrayAdapter<String> adapterItems;
    static String selectedYT;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_home);

        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();

        String[] item_tw = getResources().getStringArray(R.array.list_tw);
        String[] item_yt = getResources().getStringArray(R.array.list_yt);
        autoCompleteTextViewTW = findViewById(R.id.autoComplete_tw);
        autoCompleteTextViewYT = findViewById(R.id.autoComplete_yt);

        initializeList(item_tw, autoCompleteTextViewTW);
        initializeList(item_yt, autoCompleteTextViewYT);

        Button botonAceptar = findViewById(R.id.button);

        TextWatcher listTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String ytchannel = autoCompleteTextViewYT.getText().toString();
                String twchannel = autoCompleteTextViewTW.getText().toString();
                botonAceptar.setEnabled(!ytchannel.isEmpty() && !twchannel.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        autoCompleteTextViewTW.addTextChangedListener(listTextWatcher);
        autoCompleteTextViewYT.addTextChangedListener(listTextWatcher);
    }

    private void initializeList(String[] item_tw, AutoCompleteTextView tv) {
        adapterItems = new ArrayAdapter<String>(this, R.layout.list_items, item_tw);
        tv.setAdapter(adapterItems);
        tv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                if (tv.equals(autoCompleteTextViewYT)) {
                    switch (item) {
                        case "LoLEsports":
                            selectedYT = "UCvqRdlKsE5Q8mf8YXbdIJLw";
                            break;
                        case "LEC":
                            selectedYT = "UCWWZjhmokTbezUQr1kbbEYQ";
                            break;
                        case "LCS":
                            selectedYT = "UCSF_aFGIIIoWY30GVV19TKA";
                            break;
                        case "LCK":
                            selectedYT = "UCKVlixycWmapnGQ_wht4cHQ";
                            break;
                        case "LPL":
                            selectedYT = "UCaFMdq6QrAAEx5k2cLlZNPA";
                            break;
                        default:
                            selectedYT = item;

                    }
                }
            }
        });
    }

    public void onClickAceptar(View v) {
        Intent i = new Intent(this, StreamPlayer.class);
        i.putExtra("videoYoutube", selectedYT);
        i.putExtra("canalTwitch", autoCompleteTextViewTW.getText().toString());
        startActivity(i);
    }
}