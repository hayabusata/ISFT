package falleight.isft;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.widget.TabHost;

import static falleight.isft.R.string.title_activity_startmenu;
import static falleight.isft.R.string.title_activity_teachermenu;

public class TeacherMenuActivity extends FragmentActivity {
    protected String email;
    protected String password;
    protected String type;
    protected String status;
    protected String word;
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
            status = intent.getStringExtra("status");
            word = intent.getStringExtra("word");
            //System.out.println(status + "だよ");
        }

        initTabs();

    }

    private void initTabs() {
        FragmentTabHost tabHost = (FragmentTabHost)findViewById(R.id.tabHost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.content);

        TabHost.TabSpec tabSpec1 = tabHost.newTabSpec("Tab1").setIndicator("在室状況");
        tabHost.addTab(tabSpec1, OccupancyStatusActivity.newInstance(email, password, type, status).getClass(), OccupancyStatusActivity.newInstance(email, password, type, status).getArguments());

        TabHost.TabSpec tabSpec2 = tabHost.newTabSpec("Tab2").setIndicator("プロフィール");
        tabHost.addTab(tabSpec2, EditProfileActivity.newInstance(email, password, type, word).getClass(), EditProfileActivity.newInstance(email, password, type, word).getArguments());
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

    public String getStatus() {
        return this.status;
    }


}
