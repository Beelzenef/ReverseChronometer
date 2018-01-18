package com.example.cronometroasynctask;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

// Suprime los warnigns
@SuppressWarnings("AppCompatCustomView")
public class ReverseChronometer extends TextView implements Runnable {

    private long overallDuration;
    private long warningTime;
    private long startTime;

    public ReverseChronometer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        // Recogiendo los atributos definidos (attrs) en una lista, desde el fichero attrs bajo
        // el nombre de
        TypedArray atributos = getContext().obtainStyledAttributes(attrs, R.styleable.ReverseChronometer);

        overallDuration = atributos.getInteger(R.styleable.ReverseChronometer_overallduration, 60);
        warningTime = atributos.getInteger(R.styleable.ReverseChronometer_warningtime, 10);
        // El último valor que se queda es en este constructor, ignorando el XML o cualquier otro
        //setText("Valor inicial: " + overallDuration);
        // Si se modifica en la Activity, sustituye cualquier valor otorgado

        // Iniciando temporizador:
        reset();
    }

    public void setOverallDuration(long oD) {
        this.overallDuration = oD;
    }

    public void setWarningTime(long wT) {
        this.warningTime = wT;
    }

    @Override
    public void run() {
        long elapsedTime = (SystemClock.elapsedRealtime() - startTime) / 1000;

        if (elapsedTime < overallDuration) {
            // Actualizar tiempos:
            long remainingSeconds = overallDuration - elapsedTime;
            long minutes = remainingSeconds / 60;
            long seconds = remainingSeconds % 60;
            setText(String.format("%02d:%02d", minutes, seconds));
            // Si estamos en tiempo warning, cambiamos color:
            if (remainingSeconds < warningTime)
            {
                setTextColor(Color.RED);
            }

            // Goteo de tiempo en un segundo:
            postDelayed(this, 1000);
        } else {
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }

    public void stop() {
        //removeCallbacks(this);
    }

    public void reset() {
        startTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
        run();
    }
}
