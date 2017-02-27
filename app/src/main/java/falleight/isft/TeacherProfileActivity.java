package falleight.isft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class TeacherProfileActivity extends AppCompatActivity {
    TextView nameView, statusView, emailView, roomNumberView, wordView;
    String name, status, email, roomNumber, word;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile);

        intent = getIntent();
        if (intent != null) {
            name = intent.getStringExtra("name");
            roomNumber = intent.getStringExtra("roomnumber");
            email = intent.getStringExtra("email");
            status = intent.getStringExtra("status");
            word = intent.getStringExtra("word");
        }

        nameView = (TextView)findViewById(R.id.TeacherName);
        statusView = (TextView)findViewById(R.id.StatusView);
        emailView = (TextView)findViewById(R.id.EmailView);
        roomNumberView = (TextView)findViewById(R.id.RoomNumberView);
        wordView = (TextView)findViewById(R.id.WordView);

        nameView.setText(name);
        statusView.setText(status);
        emailView.setText(email);
        roomNumberView.setText(roomNumber);
        wordView.setText(word);
    }
}
