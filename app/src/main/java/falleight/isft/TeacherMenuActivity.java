package falleight.isft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import static falleight.isft.R.string.title_activity_startmenu;
import static falleight.isft.R.string.title_activity_teachermenu;

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

            Intent intent = new Intent(this, OccupancyStatusActivity.class);
            spec = tabHost.newTabSpec("Tab1")
                    .setIndicator("在室状況")
                    .setContent(new Intent(this, OccupancyStatusActivity.class));
            tabHost.addTab(spec);

            intent = new Intent(this, EditProfileActivity.class);
            spec = tabHost.newTabSpec("Tab2")
                    .setIndicator("プロフィール")
                    .setContent(new Intent(this, EditProfileActivity.class));
            tabHost.addTab(spec);

            tabHost.setCurrentTab(0);

            /*FragmentTabHost tabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
            tabHost.setup(this, getSupportFragmentManager(), R.id.content);

            TabHost.TabSpec mTabSpec1 = tabHost.newTabSpec("tab1")
                    .setIndicator("在室状況");
            tabHost.addTab(mTabSpec1, OccupancyStatusActivity.class, null);

            TabHost.TabSpec mTabSpec2 = tabHost.newTabSpec("tab2")
                    .setIndicator("プロフィール");
            tabHost.addTab(mTabSpec2, EditProfileActivity.class, null);*/

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
