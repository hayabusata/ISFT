package falleight.isft;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static falleight.isft.R.string.title_activity_studentmenu;

public class ListTeacher extends AppCompatActivity {
    ConnectionISFT test;
    List stockList;
    ArrayList<StockData> list;
    MyAdapter myAdapter;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_teacher);
        setTitle(title_activity_studentmenu);

        listView = (ListView)findViewById(R.id.teacherList);

        list = new ArrayList<>();
        myAdapter = new MyAdapter(ListTeacher.this);

        new getListTask().execute();
    }

    public void setAdapterToList() {
        myAdapter.setTeacherList(list);
        listView.setAdapter(myAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                if (Util.netWorkCheck(view.getContext()) == false){
                    Toast.makeText(view.getContext(), "ネットワーク接続がありません", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    ListView listView = (ListView) parent;
                    StockData item = (StockData) listView.getItemAtPosition(pos);

                    Intent intent = new Intent(ListTeacher.this, TeacherProfileActivity.class);
                    intent.putExtra("name", item.getName());
                    intent.putExtra("roomnumber", item.getRoomNumber());
                    intent.putExtra("email", item.getEmail());
                    intent.putExtra("status", item.getStatus());
                    intent.putExtra("word", item.getWord());
                    startActivity(intent);
                }
            }
        });
    }

    class getListTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            test = new ConnectionISFT();
            test.connectDatabase();

            try {
                stockList = test.getAllTeachersData();

                for (int i = 0; i < stockList.size(); i++) {
                    //System.out.println("processing");
                    test.data = (StockData) stockList.get(i);

                    list.add(test.data);
                }

                return true;
            } catch (SQLException e) {
                System.out.println("error");
            }

            return false;
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            setAdapterToList();
        }
    }
}
