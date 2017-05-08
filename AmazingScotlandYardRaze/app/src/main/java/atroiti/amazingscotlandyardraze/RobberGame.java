package atroiti.amazingscotlandyardraze;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.icu.text.IDNA;
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

import org.json.JSONArray;
import org.json.JSONObject;


public class RobberGame extends Fragment{

    LocationListener locLis;
    Integer puzzleId = 1;

    private MainActivity mActivity;
    static protected Location startLoc = null;

    @Nullable
    @Override
    //populate subView

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.robberpuzzle, container, false);
        //define buttons
        Log.i("here"," we are now");
        final Button SendButton = (Button) v.findViewById(R.id.RGSendPuzzleButton);
        ImageButton CameraButton = (ImageButton) v.findViewById(R.id.RGTakePictureButton);
        Log.i("here","Buttons ok, next define view");
        final TextView GPS = (TextView) v.findViewById(R.id.RG_GPSLocation);
        //init main activity
        final MainActivity mActivity= new MainActivity();
        final TextView RGPuzzleField = (TextView) v.findViewById(R.id.RGPuzzleField);
        final TextView RGAnsverField = (TextView) v.findViewById(R.id.RGAnswerField);
        final TextView RGPuzzleIDField = (TextView) v.findViewById(R.id.RPPuzzleNumber1);
        Log.i("here","next define location manager");

//send button that will send puzzle, ansver, GPS and photo to otherplayer
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Send puzzle button
                //check if ansver is empty/default
                //check if puzzle is empty / default
                //if not default or empty, proceed with sending
                //change button text to "getting GPS location...
                SendButton.setText("Waiting GPS location...");
                Log.i("here","send MMS");
                String OtherPlayer = mActivity.getOtherPlayer(getContext());
                Log.i("otherplayer is",OtherPlayer);
                //Get GPS coordinates in string format  latitude-longitude exam. 1.2345-5.66778
                //change button text to "getting GPS location...
                String GPSCoordinates = mActivity.GPS(getContext()).toString();
                if (GPSCoordinates=="1-1"){
                    //no GPS location aquired, so do something
                }
                String Lati=GPSCoordinates.substring(0,10);
                String Longi=GPSCoordinates.substring(GPSCoordinates.indexOf("-")+1);
                double Latitude= Double.parseDouble(Lati);
                double Longitude= Double.parseDouble(Longi);
                Log.i("GPS is",GPSCoordinates);
                //get puzzle and ansver
                String Puzzle = RGPuzzleField.getText().toString() ;
                String ansver = RGAnsverField.getText().toString() ;
                //maybe later add puzzle to SQLite db
                Log.i("puzzle is",Puzzle);
                Log.i("ansver is",ansver);
                Log.i("PuzzleId is",puzzleId.toString());
                Log.i("Latitude is",Lati);
                Log.i("Longitude is",Longi);
                //compose JSON message from data to be sent
                String Subject = "[ASYR]"+puzzleId;
                JSONObject obj = null;
                JSONArray jsonArray = new JSONArray();
                obj = new JSONObject();
                try {
                    obj.put("PuzzleId", puzzleId);
                    obj.put("Puzzle", Puzzle);
                    obj.put("ansver", ansver);
                    obj.put("latitude", Latitude);
                    obj.put("longitude", Longitude);
                }catch (Exception ex) {
                    Log.e("error", "err", ex);
                }
                Log.i("JSON is",obj.toString());
                mActivity.sendEmail(OtherPlayer,Subject,obj.toString(),getContext());
                //Reset puzzle and ansver fields, increase puzzle counter
                puzzleId=puzzleId+1;
                RGPuzzleIDField.setText(puzzleId);
                RGPuzzleField.setText("Puzzle here");
                RGAnsverField.setText("answer here");
                SendButton.setText("Send Next Puzzle");
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
