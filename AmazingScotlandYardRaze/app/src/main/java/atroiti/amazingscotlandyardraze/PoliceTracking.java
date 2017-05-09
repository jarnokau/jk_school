package atroiti.amazingscotlandyardraze;

import android.os.Bundle;
import android.renderscript.Sampler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class PoliceTracking extends Fragment{
    @Nullable
    @Override
    //populate subView
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.policetracking, container, false);
        //define buttons
        Button UpdateButton = (Button) v.findViewById(R.id.PTUpdateButton);
        Button SimulateButton = (Button) v.findViewById(R.id.PTSimulateButton);
        final MainActivity mActivity= new MainActivity();
        final TextView tv = (TextView) v.findViewById(R.id.RM_PolicePlayer);
        final TextView Distanceview = (TextView) v.findViewById(R.id.PTDistanceValue);
        final TextView LatitudeValue = (TextView) v.findViewById(R.id.PTLatiValue);
        final TextView LongiValue = (TextView) v.findViewById(R.id.PTLongiValue);
        double RobberLati;
        double RobberLongi;
        UpdateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //set role Robber
                //String GPSCoordinates = mActivity.GPS(getContext()).toString();
                //salmisaari
                //latitude and longitude of Robber
                String Lati="60.164771";
                String Longi="24.901189";
                double RobberLati=Double.parseDouble(Lati);
                double RobberLongi=Double.parseDouble(Longi);
                //My (metropolia leppävaara) latitude and longitude
                Lati="60.221781";
                Longi="24.804288";
                LongiValue.setText(Longi);
                LatitudeValue.setText(Lati);
                //distance should be around 8.27km
                //String Lati=GPSCoordinates.substring(0,10);
                //String Longi=GPSCoordinates.substring(GPSCoordinates.indexOf("-")+1);
                double myLatitude= Double.parseDouble(Lati);
                double myLongitude= Double.parseDouble(Longi);
                double distance = mActivity.distance(myLatitude,myLongitude,RobberLati,RobberLongi);

                int IntDistance = (int) Math.round(distance);
                Distanceview.setText(String.valueOf(IntDistance));
            }
        });

        SimulateButton.setOnClickListener(new View.OnClickListener() {
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
