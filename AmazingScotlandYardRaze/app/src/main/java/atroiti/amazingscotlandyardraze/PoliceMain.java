package atroiti.amazingscotlandyardraze;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class PoliceMain extends Fragment{
    @Nullable
    @Override
    //populate subView
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.robbermain, container, false);
        //define buttons
        Button SaveButton = (Button) v.findViewById(R.id.SaveSettingButton);
        Button CancelButton = (Button) v.findViewById(R.id.cancelButton);
        String tmp ="", tmpLine="";
        //SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);
        //Editor editor = sharedpreferences.edit();

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Robber
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                //save settings and open puzzle view
                trans.replace(R.id.root_frame, new RobberGame());


                //editor.putString("ROLE", "Robber");
                //editor.commit();
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cancel button
                //remove role selection
                //open new game view

            }
        });
        //role is selected

        return v;
    }
}
