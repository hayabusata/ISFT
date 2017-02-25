package falleight.isft;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.sql.SQLException;

public class OccupancyStatusActivity extends AppCompatActivity implements View.OnClickListener {
    String email;
    String password;
    String type;
    String status, newStatus;
    ConnectionISFT test;
    BootstrapButton occupancyButton, exitButton, returnButton;
    TextView statusText;

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.occupation_status);

        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("pass");
            type = intent.getStringExtra("type");
            status = intent.getStringExtra("status");
        }

        System.out.println(status);

        /*test = new ConnectionISFT();
        test.connectDatabase();
        System.out.println("process");*/
        
        occupancyButton = (BootstrapButton)findViewById(R.id.OccupancyButton);
        occupancyButton.setOnClickListener(this);
        exitButton = (BootstrapButton)findViewById(R.id.ExitButton);
        exitButton.setOnClickListener(this);
        returnButton = (BootstrapButton)findViewById(R.id.ReturnButton);
        returnButton.setOnClickListener(this);

        statusText = (TextView)findViewById(R.id.StatusText);
        statusText.setText(status);
    }

    @Override
    public void onClick(View v) {
        /*newStatus = occupancyButton.getText().toString();*/
        System.out.println("test");
        switch (v.getId()) {
            case R.id.OccupancyButton:
                newStatus = occupancyButton.getText().toString();
                statusText.setText(newStatus);
                break;
            case R.id.ExitButton:
                newStatus = exitButton.getText().toString();
                statusText.setText(newStatus);
                break;
            case R.id.ReturnButton:
                newStatus = returnButton.getText().toString();
                statusText.setText(newStatus);
                break;
            default:
                break;
        }

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