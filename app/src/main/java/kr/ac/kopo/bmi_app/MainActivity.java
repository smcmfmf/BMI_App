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
    TextView result, result_title;
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
        result = findViewById(R.id.result);
        result_title = findViewById(R.id.result_title);

        btn_result.setOnTouchListener(OnTouchListener);

    }
    View.OnTouchListener OnTouchListener = new View.OnTouchListener()
    {
        @Override
        public boolean onTouch(View v, MotionEvent event) {

            String Kg_value = Kg.getText().toString();
            String Cm_value = Cm.getText().toString();
            String Name_value = Name.getText().toString();
            String Err_value = "";

            if (Name_value.equals("") || Kg_value.equals("") || Cm_value.equals(""))
            {
                if(Kg_value.equals(""))
                {
                    Err_value = "체중";
                    Kg.setText("");
                    Kg.setFocusable(true);
                }
                else if(Cm_value.equals(""))
                {
                    Err_value = "신장";
                    Cm.setText("");
                    Cm.setFocusable(true);
                }
                else
                {
                    Err_value = "성명";
                    Name.setFocusable(true);
                }
                Toast.makeText(getApplicationContext(),Err_value + "의 값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
                Name.setFocusable(true);
            }
            else
            {
                double Kg = Double.parseDouble(Kg_value);
                double Cm = Double.parseDouble(Cm_value);
                double Result = 0;

                double Kg_2 = Kg * Kg; // 몸무게 제곱
                String State = "";

                if(Kg_2 != 0 && Cm != 0)
                {
                    Result = Kg_2 / Cm;
                }
                else if(Kg_2 == 0)
                {
                    Err_value = "체중"; /* 값이 0일 때 다시 입력 문구를 토스트로 출력 안됨 */
                }
                else
                {
                    Err_value = "신장";
                }
                Toast.makeText(getApplicationContext(),Err_value + "의 값에는 0을 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();

                Button btnEvent = (Button) v;
                if(v == btn_result)
                {
                    if(Result <= 18.5 && Result >= 0)
                    {
                        /* 저체중 */
                        State = "저체중";
                    }
                    else if(Result <= 22.9)
                    {
                        /* 정상체중 */
                        State = "정상체중";
                    }
                    else if(Result <= 25)
                    {
                        /* 과체중 */
                        State = "과체중";
                    }
                    else if(Result <= 29.9)
                    {
                        /* 경도비만 */
                        State = "경도비만";
                    }
                    else if(Result >= 30)
                    {
                        /* 고도비만 */
                        State = "고도비만";
                    }
                    else
                    {
                        /* 체중이나 신장에 값이 0일 때 */
                        State = "오류";
                        return true;
                    }
                    result_title.setText(String.format("%s!", State)); /* BMI 결과 표시 */
                    result.setText(String.format("%s님의 체중은 %.2fKg이고 신장은 %.2fCm이므로 BMI 지수는 %.2f 입니다.", Name_value, Kg, Cm, Result));
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