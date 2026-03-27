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
    TextView czas, punkty;
    ImageView j1, j2, j3, j4, j5, j6, j7, j8, j9;
    private int ileSekund;
    private CountDownTimer countDownTimer;
    private boolean czyGraTrwa = false;

    public void rozpocznijGre(){
        ileSekund = 5;
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
            }

            @Override
            public void onTick(long l) {
                ileSekund = (int) (l/1000);
                czas.setText((ileSekund+1)+"");
            }
        };
        countDownTimer.start();
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

        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        j1.setImageResource(R.drawable.brakjapuszek);
                        j2.setImageResource(R.drawable.brakjapuszek);
                        j3.setImageResource(R.drawable.brakjapuszek);
                        j4.setImageResource(R.drawable.brakjapuszek);
                        j5.setImageResource(R.drawable.brakjapuszek);
                        j6.setImageResource(R.drawable.brakjapuszek);
                        j7.setImageResource(R.drawable.brakjapuszek);
                        j8.setImageResource(R.drawable.brakjapuszek);
                        j9.setImageResource(R.drawable.brakjapuszek);
                        rozpocznijGre();
                    }
                }
        );

    }
}