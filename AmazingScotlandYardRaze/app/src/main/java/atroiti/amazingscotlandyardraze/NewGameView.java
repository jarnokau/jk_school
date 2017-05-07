package atroiti.amazingscotlandyardraze;

import android.content.SharedPreferences;
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
        //SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);
        //define buttons
        Button RobberButton = (Button) v.findViewById(R.id.NG_robber_button);
        Button PoliceButton = (Button) v.findViewById(R.id.NG_police_button);
        Button CloseButton = (Button) v.findViewById(R.id.NG_CloseButton);

//initialize mainActivity where we have some public funtions
        final MainActivity mActivity= new MainActivity();
        //SharedPreferences sharedPrefs = getSharedPreferences(PREF, MODE_PRIVATE);
        //Editor editor = sharedpreferences.edit();

        RobberButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Robber
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                //selected role Robber in new game, open newgame robber view
                String Role = "Robber";
                //call public funktion from mainActivity that will store Role I SharedPreferences
                mActivity.setRole(Role,getContext());

                trans.replace(R.id.root_frame, new RobberMain());
                trans.commit();
                Log.i("role choosen","Robber");
                //sharedPrefs.putString("ROLE", "Robber");
                //sharedPrefs.commit();
            }
        });
        PoliceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //set role Police
                String Role = "Police";
                //save role selection to reference
                mActivity.setRole(Role,getContext());
                //open police setup role view
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.root_frame, new PoliceMain());
                trans.commit();

            }
        });
        CloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //simply close app, leave settings as they were
                 System.exit(0);
            }
        });

        //role is selected
        return v;
    }
}
