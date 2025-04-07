package kr.ac.kopo.bmi_app;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText Name, Kg, Cm;
    TextView result;
    Button btn_result;

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
        Name = findViewById(R.id.Name);
        Kg = findViewById(R.id.Kg);
        Cm = findViewById(R.id.Cm);
        btn_result = findViewById(R.id.btn_result);

        btn_result.setOnTouchListener(OnTouchListener);

    }
    View.OnTouchListener OnTouchListener = new View.OnTouchListener()
    { /* 왜인지 값을 입력 후 버튼을 터치하면 앱이 종료됨 */
        public boolean onTouch(View v, MotionEvent event) {

            String Kg_value = Kg.getText().toString();
            String Cm_value = Cm.getText().toString();

            if (Name.equals("") || Kg.equals("") || Cm.equals(""))
            {
                if (Name.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"성명이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    Name.setFocusable(true);
                }
                else if(Kg.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"성명이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    Kg.setFocusable(true);
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"성명이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                    Cm.setFocusable(true);
                }
            }
            else
            {
                double Kg = Double.parseDouble(Kg_value);
                double Cm = Double.parseDouble(Cm_value);
                double Result = 0;

                Button btnEvent = (Button) v;
                if(v == btn_result)
                {
                    Result = Kg / Cm;
                    if(Result <= 18.5)
                    {
                        /* 저체중 */
                        result.setText(String.format("%.s님의 체중은 %.f Kg이고, 신장은 %.f Cm이므로 BMI 지수는 %.3f 입니다.", Name, Kg, Cm, result));
                    }
                    else if(Result <= 22.9)
                    {
                        /* 정상체중 */
                        result.setText(String.format("%.s님의 체중은 %.f Kg이고, 신장은 %.f Cm이므로 BMI 지수는 %.3f 입니다.", Name, Kg, Cm, result));
                    }
                    else if(Result <= 25)
                    {
                        /* 과체중 */
                        result.setText(String.format("%.s님의 체중은 %.f Kg이고, 신장은 %.f Cm이므로 BMI 지수는 %.3f 입니다.", Name, Kg, Cm, result));
                    }
                    else if(Result <= 29)
                    {
                        /* 경도비만 */
                        result.setText(String.format("%.s님의 체중은 %.f Kg이고, 신장은 %.f Cm이므로 BMI 지수는 %.3f 입니다.", Name, Kg, Cm, result));
                    }
                    else
                    {
                        /* 고도비만 */
                        result.setText(String.format("%.s님의 체중은 %.f Kg이고, 신장은 %.f Cm이므로 BMI 지수는 %.3f 입니다.", Name, Kg, Cm, result));
                    }
                }
                else
                {
                    Name.setText("");
                    Name.setFocusable(true);
                }
            }


            return true;
        }
    };
}