package com.mhasancmt.tipcalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    public static final int INITIAL_TIP_PERCENT = 15;
    private SeekBar seekbarTip;
    private TextView tvTipPercent, tvTipAmount, tvTotalAmount;
    private EditText etBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbarTip = findViewById(R.id.seekbarTip);
        tvTipPercent = findViewById(R.id.tvTipPerchantage);
        etBase = findViewById(R.id.etBase);
        tvTipAmount = findViewById(R.id.tvTipAmount);
        tvTotalAmount = findViewById(R.id.tvTotalAmount);

        seekbarTip.setProgress(INITIAL_TIP_PERCENT);
        tvTipPercent.setText("15%");

        seekbarTip.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                Log.i("onProgress change", progress + "");
                tvTipPercent.setText(progress + "%");
                computeTipAndTotal();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //Base EditText Listener
        etBase.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Log.i("afterTextChanged", editable + "");
                computeTipAndTotal();
            }
        });
    }

    private void computeTipAndTotal() {
        //Get the base and tip percent
        if (etBase.getText().toString().isEmpty()) {
            tvTipAmount.setText("");
            tvTotalAmount.setText("");
            return;
        }
        double baseAmount = Double.parseDouble(etBase.getText().toString());
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        int tipPercent = seekbarTip.getProgress();
        double tipAmount = baseAmount * tipPercent / 100;
        double total = baseAmount + tipAmount;
        tvTipAmount.setText(df.format(tipAmount) + "");
        tvTotalAmount.setText(df.format(total) + "");
    }
}