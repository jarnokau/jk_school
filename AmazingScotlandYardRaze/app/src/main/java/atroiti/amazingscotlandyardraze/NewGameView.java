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


public class NewGameView extends Fragment{
    @Nullable
    @Override
    //populate subView
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.ng_roleselect, container, false);
        //define buttons
        Button RobberButton = (Button) v.findViewById(R.id.NG_robber_button);
        Button PoliceButton = (Button) v.findViewById(R.id.NG_police_button);
        String tmp ="", tmpLine="";
        //SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);
        //Editor editor = sharedpreferences.edit();

        RobberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Robber
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                //selected role Robber in new game, open newgame robber view
                trans.replace(R.id.root_frame, new RobberMain());
                String Role = "Robber";
                trans.commit();
                Log.i("role choosen","Robber");
                //editor.putString("ROLE", "Robber");
                //editor.commit();
            }
        });
        PoliceButton.setOnClickListener(new View.OnClickListener() {
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
