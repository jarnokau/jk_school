package atroiti.amazingscotlandyardraze;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


public class RobberGame extends Fragment{

    LocationListener locLis;

    private MainActivity mActivity;
    static protected Location startLoc = null;

    @Nullable
    @Override
    //populate subView

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.robberpuzzle, container, false);
        //define buttons
        Log.i("here"," we are now");
        Button SendButton = (Button) v.findViewById(R.id.RGSendPuzzleButton);
        ImageButton CameraButton = (ImageButton) v.findViewById(R.id.RGTakePictureButton);
        Log.i("here","Buttons ok, next define view");
        final TextView GPS = (TextView) v.findViewById(R.id.RG_GPSLocation);
        //init main activity
        final MainActivity mActivity= new MainActivity();
        final TextView RGPuzzleField = (TextView) v.findViewById(R.id.RG_GPSLocation);
        Log.i("here","next define location manager");

//send button that will send puzzle, ansver, GPS and photo to otherplayer
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("here","send MMS");
                String OtherPlayer = mActivity.getOtherPlayer(getContext());
                Log.i("otherplayer is",OtherPlayer);
                //Get GPS coordinates from TextView
                String GPSCoordinates = mActivity.GPS(getContext()).toString();
                Log.i("GPS is","GPSCoordinates");
                //add puzzle to SQLite db
                //String Puzzle = GPS.getText().toString();

                //Send puzzle button
                //send MMS message
               // mActivity.sendMMS(getContext());
                mActivity.sendEmail(OtherPlayer,"GPS coordinates here",getContext());
                //check if ansver is empty/default
                //check if puzzle is empty / default
                //
            }
        });
//camera button that will first activate camera and then place last image in imageView
        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("here","at camera button");
                //activate camera
                //get last image URI
                //place image to imageView
                mActivity.activateCamera(getContext());

            }
        });


        Log.i("pagerView","loaded");
        return v;
    }
}
