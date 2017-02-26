package falleight.isft;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.beardedhen.androidbootstrap.BootstrapButton;

import java.sql.SQLException;

public class OccupancyStatusActivity extends Fragment implements View.OnClickListener {

    private static final String ARG_PARAM1 = "email";
    private static final String ARG_PARAM2 = "pass";
    private static final String ARG_PARAM3 = "type";
    private static final String ARG_PARAM4 = "status";

    String email;
    String password;
    String type;
    String status, newStatus;
    ConnectionISFT test;
    BootstrapButton occupancyButton, exitButton, returnButton;
    TextView statusText;

    public OccupancyStatusActivity() {
        // Required empty public constructor
    }

    public static OccupancyStatusActivity newInstance(String param1, String param2, String param3, String param4) {
        OccupancyStatusActivity fragment = new OccupancyStatusActivity();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        args.putString(ARG_PARAM3, param3);
        args.putString(ARG_PARAM4, param4);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //System.out.println(getArguments().getString(ARG_PARAM4));

        if (getArguments() != null) {
            email = getArguments().getString(ARG_PARAM1);
            password = getArguments().getString(ARG_PARAM2);
            type = getArguments().getString(ARG_PARAM3);
            status = getArguments().getString(ARG_PARAM4);
        }

        //System.out.println(email + "hooo");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.occupation_status, container, false);

        occupancyButton = (BootstrapButton)v.findViewById(R.id.OccupancyButton);
        occupancyButton.setOnClickListener(this);
        exitButton = (BootstrapButton)v.findViewById(R.id.ExitButton);
        exitButton.setOnClickListener(this);
        returnButton = (BootstrapButton)v.findViewById(R.id.ReturnButton);
        returnButton.setOnClickListener(this);

        statusText = (TextView)v.findViewById(R.id.StatusText);
        statusText.setText(status);

        return v;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
