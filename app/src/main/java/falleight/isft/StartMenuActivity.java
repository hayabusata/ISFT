package falleight.isft;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.sql.SQLException;

import static falleight.isft.R.string.title_activity_login;
import static falleight.isft.R.string.title_activity_startmenu;
import static falleight.isft.R.string.title_activity_studentmenu;

public class StartMenuActivity extends AppCompatActivity {
    String email, password, type, status, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_menu);
        setTitle(title_activity_startmenu);

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

        Button throughLoginButton = (Button)findViewById(R.id.ThroughLoginButton);
        throughLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
                email = data.getString("loginEmail", null);
                password = data.getString("loginPassword", null);
                type = data.getString("loginType", null);

                if (email.equals(null) || password.equals(null) || type.equals(null)) {
                    //make Toast
                    Toast.makeText(StartMenuActivity.this, "ログインしてください", Toast.LENGTH_SHORT).show();
                } else if (type.equals("teacher")) {
                    new loginTask().execute();

                } else if (type.equals("student")) {
                    Intent intent = new Intent(StartMenuActivity.this, ListTeacher.class);
                    startActivity(intent);
                }
            }
        });
    }

    class loginTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            ConnectionISFT test = new ConnectionISFT();
            test.connectDatabase();

            try {
                status = test.getStatusFromDatabase(email, password);
                word = test.getWordFromDatabase(email, password);

                Intent intent = new Intent(StartMenuActivity.this, TeacherMenuActivity.class);
                intent.putExtra("email", email);
                intent.putExtra("pass", password);
                intent.putExtra("type", type);
                intent.putExtra("status", status);
                intent.putExtra("word", word);

                startActivity(intent);

            } catch (SQLException e) {
                return false;
            }

            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }
    }
}
