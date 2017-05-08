package atroiti.amazingscotlandyardraze;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
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
    Integer puzzleId;
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
        final TextView GPS = (TextView) v.findViewById(R.id.RG_GPSHeader);
        //init main activity
        final MainActivity mActivity= new MainActivity();
        final TextView RGPuzzleField = (TextView) v.findViewById(R.id.RGPuzzleField);
        final TextView RGAnsverField = (TextView) v.findViewById(R.id.RGAnswerField);
        final TextView RGPuzzleIDField = (TextView) v.findViewById(R.id.RPPuzzleNumber1);
        //GetPuzzleID, if it is 0 then its new game and 1st puzzle, change it to 1
        puzzleId = mActivity.getPuzzleId(getContext());
        Log.i("PuzzleID is",puzzleId.toString());
        if (puzzleId == 0) {
            puzzleId=1;
            Log.i("PuzzleID is",puzzleId.toString());
        };
        if (puzzleId == 6) {
            //all 5 puzzles have been sent, so now to Wait screen
            FragmentTransaction trans = getFragmentManager()
                    .beginTransaction();
            Log.i("puzzleId", "is 6, now just have to wait");
            //trans.replace(R.id.root_frame, new RobberGame());
        }
        RGPuzzleIDField.setText(puzzleId);
//send button that will send puzzle, ansver, GPS and photo to otherplayer
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //check if ansver is empty/default
                //check if puzzle is empty / default
                //if not default or empty, proceed with sending
                //change button text to "getting GPS location...
                SendButton.setText("Waiting GPS location...");
                Log.i("here","send MMS");
                String OtherPlayer = mActivity.getOtherPlayer(getContext());
                Log.i("otherplayer is",OtherPlayer);
                //Get GPS coordinates in string format  latitude-longitude exam. 1.2345-5.66778
                String GPSCoordinates = mActivity.GPS(getContext()).toString();
                if (GPSCoordinates=="1-1"){
                    //no GPS location aquired, so do something
                }
                //separate latitude and longitude
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
                mActivity.setPuzzleId(puzzleId,getContext());
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
