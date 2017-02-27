package falleight.isft;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherProfileActivity extends AppCompatActivity implements View.OnClickListener{
    TextView nameView, statusView, emailView, roomNumberView, wordView;
    String name, status, email, roomNumber, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile);

        Intent intent = getIntent();
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

        ((Button)findViewById(R.id.sendEmail)).setOnClickListener(this);

        nameView.setText(name);
        statusView.setText(status);
        emailView.setText(email);
        roomNumberView.setText(roomNumber);
        wordView.setText(word);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendEmail:
                Intent emailIntent = new Intent();
                emailIntent.setAction(Intent.ACTION_SENDTO);

                emailIntent.setType("text/plain");
                emailIntent.setData(Uri.parse("mail:to" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "タイトル");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "本文");

                startActivity(Intent.createChooser(emailIntent, null));
                break;
            default:
                break;
        }
    }
}
