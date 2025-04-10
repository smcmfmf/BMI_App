package kr.ac.kopo.bmi_app;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    ImageView bmi_image;
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
        bmi_image = findViewById(R.id.bmi_image);
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
                if(Name_value.equals(""))
                {
                    Err_value = "성명";
                    Name.setFocusable(true);
                }
                else if(Kg_value.equals(""))
                {
                    Err_value = "체중";
                    Kg.setFocusable(true);
                }
                else
                {
                    Err_value = "신장";
                    Cm.setFocusable(true);
                }
                Toast.makeText(getApplicationContext(),Err_value + "의 값이 입력되지 않았습니다.", Toast.LENGTH_SHORT).show();
            }
            else
            {
                double Kg = Double.parseDouble(Kg_value);
                double Cm = Double.parseDouble(Cm_value);
                if(Kg == 0)
                {
                    Err_value = "체중";
                    Toast.makeText(getApplicationContext(), Err_value + "의 값에는 0을 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else if(Cm == 0)
                {
                    Err_value = "신장";
                    Toast.makeText(getApplicationContext(), Err_value + "의 값에는 0을 입력할 수 없습니다.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    double Result;

                    double Cm_2 = Cm / 100; // 신장의 단위 (Cm -> m) 변환
                    Cm_2 = Cm_2 * Cm_2; // 신장의 제곱

                    String State = "";
                    Character Per = '%';

                    Result = Kg / Cm_2;

                    Button btnEvent = (Button) v;
                    if(v == btn_result)
                    {
                        if(Result <= 18.5 && Result >= 0)
                        {
                            /* 저체중 */
                            bmi_image.setImageResource(R.drawable.low);
                            State = "저체중";
                        }
                        else if(Result <= 22.9)
                        {
                            /* 정상체중 */
                            bmi_image.setImageResource(R.drawable.mid);
                            State = "정상체중";
                        }
                        else if(Result <= 25)
                        {
                            /* 과체중 */
                            bmi_image.setImageResource(R.drawable.shigh);
                            State = "과체중";
                        }
                        else if(Result <= 29.9)
                        {
                            /* 경도비만 */
                            bmi_image.setImageResource(R.drawable.high);
                            State = "경도비만";
                        }
                        else if(Result >= 30)
                        {
                            /* 고도비만 */
                            bmi_image.setImageResource(R.drawable.xhigh);
                            State = "고도비만";
                        }
                        result_title.setText(String.format("%s!", State)); /* BMI 결과 표시 */
                        result.setText(String.format("%s 님의 체중은 %.2fKg 이고 신장은 %.2fCm 이므로 BMI 지수는 %.2f%c 입니다.", Name_value, Kg, Cm, Result, Per));
                    }
                }
            }
            return true;
        }
    };
}