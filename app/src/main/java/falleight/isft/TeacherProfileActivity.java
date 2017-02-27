package falleight.isft;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import static falleight.isft.R.string.title_activity_startmenu;
import static falleight.isft.R.string.title_activity_teacherprofile;

public class TeacherProfileActivity extends AppCompatActivity implements View.OnClickListener{
    String name, status, email, roomNumber, word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        TextView nameView, statusView, emailView, roomNumberView, wordView;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.teacher_profile);
        setTitle(title_activity_teacherprofile);

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
        //emailView = (TextView)findViewById(R.id.EmailView);
        roomNumberView = (TextView)findViewById(R.id.RoomNumberView);
        wordView = (TextView)findViewById(R.id.WordView);

        ((BootstrapButton)findViewById(R.id.sendEmail)).setOnClickListener(this);

        nameView.setText(name);
        statusView.setText(status);
        //emailView.setText(email);
        roomNumberView.setText(roomNumber);
        wordView.setText(word);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sendEmail:
                Uri uri = Uri.parse("mailto:" + email);

                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, uri);
                //emailIntent.setAction(Intent.ACTION_SENDTO, uri);

                //emailIntent.setType("text/plain");
                //emailIntent.setData(Uri.parse("mail:to" + email));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "");

                //startActivity(Intent.createChooser(emailIntent, null));
                startActivity(emailIntent);
                break;
            default:
                break;
        }
    }
}
