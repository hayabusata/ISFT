package falleight.isft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import static falleight.isft.R.string.title_activity_startmenu;
import static falleight.isft.R.string.title_activity_teachermenu;

public class TeacherMenuActivity extends AppCompatActivity {
    String email;
    String password;
    String type;
    String newStatus;
    ConnectionISFT test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_menu);
        setTitle(title_activity_teachermenu);

        Intent intent = getIntent();
        if (intent != null) {
            email = intent.getStringExtra("email");
            password = intent.getStringExtra("pass");
            type = intent.getStringExtra("type");
        }

        initTabs();
    }

    protected void initTabs() {
        try {
            TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
            tabHost.setup();
            TabHost.TabSpec spec;

            //Tab1
            spec = tabHost.newTabSpec("Tab1")
                    .setIndicator("在室状況")
                    .setContent(R.id.tab1);
            tabHost.addTab(spec);

            //Tab2
            spec = tabHost.newTabSpec("Tab2")
                    .setIndicator("プロフィール")
                    .setContent(R.id.tab2);
            tabHost.addTab(spec);

            tabHost.setCurrentTab(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }
}
