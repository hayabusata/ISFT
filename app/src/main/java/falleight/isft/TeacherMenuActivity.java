package falleight.isft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

public class TeacherMenuActivity extends AppCompatActivity {
    protected String email;
    protected String password;
    protected String type;
    protected String newStatus;
    ConnectionISFT test;

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

        initTabs();
    }

    protected void initTabs() {
        try {
            TabHost tabHost = (TabHost)findViewById(R.id.tabHost);
            tabHost.setup();
            TabHost.TabSpec spec;

            //Tab1
            spec = tabHost.newTabSpec("Tab1")
                    .setIndicator("Home")
                    .setContent(R.id.tab1);
            tabHost.addTab(spec);

            //Tab2
            spec = tabHost.newTabSpec("Tab2")
                    .setIndicator("Event")
                    .setContent(R.id.tab2);
            tabHost.addTab(spec);

            tabHost.setCurrentTab(0);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public String getType() {
        return this.type;
    }

    public String getNewStatus() {
        return this.newStatus;
    }


}
