package sg.edu.rp.c346.id22015127.billplease;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    TextView result;
    TextView result2;
    EditText amt;
    EditText pax;
    ToggleButton svs;
    ToggleButton gst;
    EditText dsc;
    RadioGroup pay;
    Button split;
    Button reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.fDisplay);
        result2 = findViewById(R.id.fDisplay2);
        amt = findViewById(R.id.etAmt);
        pax = findViewById(R.id.pax);
        svs = findViewById(R.id.tbsvs);
        gst = findViewById(R.id.tbgst);
        dsc = findViewById(R.id.etdc);
        pay = findViewById(R.id.rgpay);
        split = findViewById(R.id.buttonSplit);
        reset = findViewById(R.id.buttonReset);

        split.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String total = amt.getText().toString();
                String discount = dsc.getText().toString();
                String paxNo = pax.getText().toString();

                double calcpax = Integer.parseInt(paxNo);
                double calctotal = Integer.parseInt(total);
                int calcdisc = Integer.parseInt(discount);
                double aftdsc = calctotal/100*(100-calcdisc);
                if(svs.isChecked()==true){
                    aftdsc = aftdsc * 1.1;
                }
                if(gst.isChecked()==true){
                    aftdsc = aftdsc * 1.07;
                }

                result.setText("Total Bill: $" + aftdsc);
                double splitamt = aftdsc/calcpax;
                int checkedRadioID = pay.getCheckedRadioButtonId();
                String rslt2 = result2.getText().toString();
                if(checkedRadioID == R.id.rbc){
                    rslt2 = "Each Pays: $" + splitamt + " in cash";
                }else{
                    rslt2 = "Each Pays: $" + splitamt + " via PayNow to 42069420";
                }
                result2.setText(rslt2);

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText("");
                result2.setText("");
                amt.getText().clear();
                pax.getText().clear();
                dsc.getText().clear();
                svs.setChecked(true);
                gst.setChecked(true);
                pay.clearCheck();

            }
        });

    }

}