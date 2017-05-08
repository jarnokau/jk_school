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
import android.widget.TextView;
import java.lang.String;


public class RobberMain extends Fragment{
    @Nullable
    @Override
    //populate subView
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.robbermain, container, false);
        //define buttons
        Button SaveButton = (Button) v.findViewById(R.id.SaveSettingButton);
        Button CancelButton = (Button) v.findViewById(R.id.cancelButton);
        final MainActivity mActivity= new MainActivity();
        final TextView tv = (TextView) v.findViewById(R.id.RM_PolicePlayer);

        SaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Robber
                Log.i("SaveButton","clicked");
                //Comman below throw an error
                String OtherPlayer = (String) tv.getText().toString();
                Log.i("Otherplayer","contact info optained");
                //save settings
                mActivity.setOtherPlayer(tv.getText().toString(),getContext());
                Log.i("OtherPlayer","information saved to sharedPreferences");
                //Open Puzzle View
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                Log.i("frame","open gameview next");
                trans.replace(R.id.root_frame, new RobberGame());
                Log.i("frame","commit next");
                trans.commit();
                Log.i("frame","commit done");
            }
        });

        CancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Cancel button
                //remove role selection
                mActivity.DeleteRole(getContext());
                //open new game view
                FragmentTransaction trans = getFragmentManager()
                        .beginTransaction();
                trans.replace(R.id.root_frame, new NewGameView());
                trans.commit();

            }
        });
        //role is selected

        return v;
    }
}
