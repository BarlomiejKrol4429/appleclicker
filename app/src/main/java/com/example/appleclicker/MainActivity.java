package com.example.appleclicker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button start;
    TextView czas, punktacja;
    ImageView j1, j2, j3, j4, j5, j6, j7, j8, j9;
    private int ileSekund;
    private int ileBezKlikania;
    private CountDownTimer countDownTimer;
    private CountDownTimer inactivityTimer;
    int poprzednie_czerfone = -1;
    int poprzednie_sielone = -1;
    int punkty = 0;
    boolean clicked = false;
    private ImageView[] jablka;

    public void rozpocznijGre(){
        punktacja = findViewById(R.id.punkty);

        punkty = 0;
        punktacja.setText(punkty+"");
        ileSekund = 30;
        uruchomZegar();
        start.setEnabled(false);
        losujJapka();
    }

    private void uruchomZegar(){
        czas = findViewById(R.id.czas);

        countDownTimer = new CountDownTimer(ileSekund * 1000, 1000) {
            @Override
            public void onFinish() {
                start.setEnabled(true);
                czas.setText(0+"");
                for(int i = 0; i < jablka.length; i++){
                    jablka[i].setOnClickListener(null);
                    jablka[i].setImageResource(R.drawable.brakjapuszek);
                }
            }

            @Override
            public void onTick(long l) {
                ileSekund = (int) (l/1000);
                czas.setText((ileSekund+1)+"");
            }
        };
        countDownTimer.start();
    }

    private void nieaktywnoscTimer(){
        inactivityTimer = new CountDownTimer(ileBezKlikania * 1000, 1000) {
            @Override
            public void onFinish() {
                if(!clicked && !start.isEnabled()){
                    losujJapka();
                }
            }

            @Override
            public void onTick(long l) {
                if(clicked){
                    inactivityTimer.cancel();
                }
            }
        };
        inactivityTimer.start();
    }

    private void losujJapka(){
        clicked = false;
        ileBezKlikania = 1;
        nieaktywnoscTimer();
        int czerfone = -2;

        int troll = new Random().nextInt(10);

        punktacja = findViewById(R.id.punkty);
        if (troll != 0) {
            czerfone = new Random().nextInt(9);
            while(czerfone == poprzednie_czerfone) {
                czerfone = new Random().nextInt(9);
            }
        }


        int sielone = new Random().nextInt(9);
        while(sielone == czerfone || sielone == poprzednie_sielone) {
            sielone = new Random().nextInt(9);
        }



        for(int i = 0; i < jablka.length; i++){
            jablka[i].setOnClickListener(null);
            jablka[i].setImageResource(R.drawable.brakjapuszek);
        }

        if(troll != 0) {
            ImageView czerfoneJapko = jablka[czerfone];
            czerfoneJapko.setImageResource(R.drawable.czerfonejapuszko);
            czerfoneJapko.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            punkty += 1;
                            punktacja.setText(punkty+"");
                            clicked = true;
                            inactivityTimer.cancel();
                            losujJapka();
                        }
                    }
            );
        }
        ImageView sieloneJapko = jablka[sielone];


        sieloneJapko.setImageResource(R.drawable.sielonejapuszko);
        sieloneJapko.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        punkty -= 1;
                        punktacja.setText(punkty+"");
                        clicked = true;
                        inactivityTimer.cancel();
                        losujJapka();
                    }
                }
        );
        poprzednie_czerfone = czerfone;
        poprzednie_sielone = sielone;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        start = findViewById(R.id.start);
        j1 = findViewById(R.id.japuszko1);
        j2 = findViewById(R.id.japuszko2);
        j3 = findViewById(R.id.japuszko3);
        j4 = findViewById(R.id.japuszko4);
        j5 = findViewById(R.id.japuszko5);
        j6 = findViewById(R.id.japuszko6);
        j7 = findViewById(R.id.japuszko7);
        j8 = findViewById(R.id.japuszko8);
        j9 = findViewById(R.id.japuszko9);

        jablka = new ImageView[]{
                j1, j2, j3, j4, j5, j6, j7, j8, j9
        };

        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rozpocznijGre();
                    }
                }
        );

    }
}