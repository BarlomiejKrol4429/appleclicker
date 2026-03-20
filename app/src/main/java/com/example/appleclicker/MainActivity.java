package com.example.appleclicker;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button start;
    TextView czas, punkty;
    ImageView japuszko1, japuszko2, japuszko3, japuszko4, japuszko5, japuszko6, japuszko7, japuszko8, japuszko9;
    private int ileSekund = 10;
    private CountDownTimer countDownTimer;
    private boolean czyGraTrwa = false;

    public void rozpocznijGre(){
        if(!czyGraTrwa){

            uruchomZegar();
            czyGraTrwa = true;
        }
    }

    private void uruchomZegar(){
        countDownTimer = new CountDownTimer(ileSekund * 1000, 1000) {
            @Override
            public void onFinish() {

            }

            @Override
            public void onTick(long l) {
                ileSekund = (int) (l/1000);
                czas.setText(ileSekund+"");
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

        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        rozpocznijGre();
                        japuszko1.setImageIcon();
                    }
                }
        );

    }
}