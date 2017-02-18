package falleight.isft;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.beardedhen.androidbootstrap.BootstrapButton;

public class StartMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        BootstrapButton teachersbutton = (BootstrapButton)findViewById(R.id.TeachersButton);
        teachersbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartMenuActivity.this, LoginActivity.class);
                intent.putExtra("type", "teacher");
                startActivity(intent);
            }
        });
        BootstrapButton studentsbutton = (BootstrapButton)findViewById(R.id.StudentsButton);
        studentsbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartMenuActivity.this, LoginActivity.class);
                intent.putExtra("type", "student");
                startActivity(intent);
            }
        });
    }
}
