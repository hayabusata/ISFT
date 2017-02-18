package falleight.isft;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.SQLException;

public class TeacherMenuActivity extends AppCompatActivity implements View.OnClickListener {
    String email;
    String password;
    String type;
    String newStatus;
    ConnectionISFT test;
    Button occupancyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);

        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("pass");
            type = intent.getStringExtra("type");
        }

        /*test = new ConnectionISFT();
        test.connectDatabase();
        System.out.println("process");*/
        occupancyButton = (Button)findViewById(R.id.OccupancyInformationButton);
        occupancyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        newStatus = occupancyButton.getText().toString();
        new AsyncAppTask().execute();
    }

    class AsyncAppTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            test = new ConnectionISFT();
            test.connectDatabase();
            try {
                if (test.loginISFT(email, password, type)) {
                    System.out.println(newStatus);
                    test.updateStatus(newStatus);
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("error");
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {

        }
    }
}
