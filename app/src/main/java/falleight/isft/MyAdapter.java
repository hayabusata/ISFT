package falleight.isft;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapAlert;
import com.beardedhen.androidbootstrap.BootstrapButton;

import java.util.ArrayList;
import java.util.List;

import falleight.isft.R;
import falleight.isft.StockData;

public class MyAdapter extends BaseAdapter {
    Context context;
    LayoutInflater layoutInflater = null;
    ArrayList<StockData> teacherList;

    public MyAdapter(Context context) {
        this.context = context;
        this.layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setTeacherList(ArrayList<StockData> teacherList) {
        this.teacherList = teacherList;
    }

    @Override
    public int getCount() {
        return teacherList.size();
    }

    @Override
    public Object getItem(int position) {
        return teacherList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return teacherList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.list_layout, parent, false);

        ListView listView = (ListView)convertView.findViewById((R.id.teacherList));
        TextView view = (TextView) convertView.findViewById(R.id.status);

        ((TextView)convertView.findViewById(R.id.name)).setText(teacherList.get(position).getName());
        view.setText(teacherList.get(position).getStatus());
        //System.out.println("はあーい");
        if (view.getText().equals("在室")) {
            view.setBackgroundColor(Color.GREEN);
        } else if (view.getText().equals("退室")) {
            view.setBackgroundColor(Color.RED);
        } else {
            view.setBackgroundColor(Color.GRAY);
        }


        return convertView;
    }
}