package falleight.isft;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.sql.SQLException;

public class EditProfileActivity extends Fragment implements View.OnClickListener{
    private static final String ARG_PARAM1 = "email";
    private static final String ARG_PARAM2 = "pass";
    private static final String ARG_PARAM3 = "type";
    private static final String ARG_PARAM4 = "word";

    Button button;
    TextView textView;
    EditText editText;
    String email, password, type, word;
    ConnectionISFT test;

    public EditProfileActivity() {
        // Required empty public constructor
    }

    public static EditProfileActivity newInstance(String param1, String param2, String param3, String param4) {
        EditProfileActivity fragment = new EditProfileActivity();
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

        if (getArguments() != null) {
            email = getArguments().getString(ARG_PARAM1);
            password = getArguments().getString(ARG_PARAM2);
            type = getArguments().getString(ARG_PARAM3);
            word = getArguments().getString(ARG_PARAM4);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.edit_profile, container, false);

        button = (Button)v.findViewById(R.id.button);
        textView = (TextView)v.findViewById(R.id.textView);
        editText = (EditText)v.findViewById(R.id.editText);

        button.setOnClickListener(this);
        textView.setText(word);

        return v;
    }

    @Override
    public void onClick(View v) {
        word = editText.getText().toString();
        textView.setText(word);
        new AsyncAppTask().execute();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
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
                    test.updateWord(word);
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
