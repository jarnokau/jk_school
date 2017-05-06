package atroiti.amazingscotlandyardraze;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class RobberMain extends Fragment{
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
                //selected role Robber in new game, open newgame robber view
                trans.replace(R.id.root_frame, new RobberPuzzle());
                String Role = "Robber";
                Log.i("role choosen","Robber");
                //editor.putString("ROLE", "Robber");
                //editor.commit();
            }
        });
        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Police
                String Role = "Police";
                Log.i("role choosen","Police");
                //editor.putString("ROLE", "Police");
                //editor.commit();
            }
        });
        //role is selected
        Log.i("pagerView","loaded");
        return v;
    }
}
