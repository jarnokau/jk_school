package atroiti.amazingscotlandyardraze;

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


public class PoliceGame extends Fragment{

    LocationListener locLis;
    Integer puzzleId;
    private MainActivity mActivity;
    static protected Location startLoc = null;
    String CorrectAnsver="nothing";

    @Nullable
    @Override
    //populate subView

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate( R.layout.policepuzzle, container, false);

        //define buttons
        final Button SendButton = (Button) v.findViewById(R.id.PGSendAnsverButton);
        final Button SimulateButton = (Button) v.findViewById(R.id.PGSimuPuzzlButton);
        final TextView GPS = (TextView) v.findViewById(R.id.PG_GPSHeader);
        //init main activity
        final MainActivity mActivity= new MainActivity();
        final TextView PGPuzzleField = (TextView) v.findViewById(R.id.PGPuzzleField);
        final TextView PGAnsverField = (TextView) v.findViewById(R.id.PGAnswerField);
        final TextView PGAnsverLabelField = (TextView) v.findViewById(R.id.PGtextView2);
        final TextView PGPuzzleIDField = (TextView) v.findViewById(R.id.PGPuzzleNumber1);

        //GetPuzzleID, if it is 0 then its new game and 1st puzzle, change it to 1
        puzzleId = mActivity.getPuzzleId(getContext());
        Log.i("PuzzleID is",puzzleId.toString());

        if (puzzleId == 0) {
            //new game, got to wait page until you get email with puzzle

            //got to wait page, until email arraives
            //puzzleId=1;
            Log.i("PuzzleID is",puzzleId.toString());
        };

        if (puzzleId == 6) {
            //all 5 ansvers for puzzles have been sent, so now to Wait screen
            FragmentTransaction trans = getFragmentManager()
                    .beginTransaction();
            Log.i("puzzleId", "is 6, now just have to wait");
            //trans.replace(R.id.root_frame, new RobberGame());
        }
        if (puzzleId == 1) {
            //get puzzle and ansver from sharedPref
            String PandA = mActivity.getPuzzle(getContext());
            Integer index = PandA.indexOf("/");
            String Puzzle = PandA.substring(0, index - 1);
            CorrectAnsver = PandA.substring(index + 1);
            Log.i("Puzzle", Puzzle);
            Log.i("Ansver", CorrectAnsver);
            //puzzle found and will be placed visible
            PGPuzzleIDField.setText(puzzleId);
            PGPuzzleField.setText(Puzzle);
        }
//send button that will send puzzle, ansver, GPS and photo to otherplayer
        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get ansver from text field
                String MyAnsver = PGAnsverField.getText().toString() ;
                Log.i("ansver",">"+MyAnsver+"<");
                Log.i("Correct_ansver","<"+CorrectAnsver+">");
                //Get ansver from SharePRef
                if(MyAnsver.compareToIgnoreCase(CorrectAnsver)==0){
                    Log.i("ansver"," Was correct");
                    //open tracking screen
                    FragmentTransaction trans = getFragmentManager()
                            .beginTransaction();
                    //open Police setup view/page
                    trans.replace(R.id.root_frame, new PoliceTracking());
                    trans.commit();
                }else{
                    //ansver was wrong
                    PGAnsverLabelField.setText("Ansver was Wrong. Write ansver here:");
                }

                Log.i("asnver","Pass if sentence");
                //mActivity.sendEmail(OtherPlayer,Subject,obj.toString(),getContext());
                //Reset puzzle and ansver fields, increase puzzle counter
                /*
                puzzleId=puzzleId+1;
                PGPuzzleIDField.setText(puzzleId.toString());
                mActivity.setPuzzleId(puzzleId,getContext());
                PGPuzzleField.setText("Puzzle here");
                PGAnsverField.setText("answer here");
                SendButton.setText("Send Next Ansver");
                */
            }
        });
        SimulateButton.setOnClickListener(new View.OnClickListener() {
                                          @Override
                                          public void onClick(View v) {
                                              //define simulated info
                                              puzzleId=1;
                                              String Puzzle = "how much is 1+2";
                                              String Ansver = "3";
                                              //store simulated info
                                              mActivity.setPuzzleId(puzzleId,getContext());
                                              mActivity.setPuzzle(Puzzle,Ansver,getContext());
                                              //read simulated info
                                              String PandA = mActivity.getPuzzle(getContext());
                                              Integer index = PandA.indexOf("/");
                                              Puzzle = PandA.substring(0, index);
                                              CorrectAnsver = PandA.substring(index + 1);
                                              Log.i("Puzzle", Puzzle);
                                              Log.i("Ansver", CorrectAnsver);
                                              //puzzle found and will be placed visible
                                              PGPuzzleIDField.setText(puzzleId.toString());
                                              PGPuzzleField.setText(Puzzle);
                                          }
                                      });
        Log.i("pagerView","loaded");
        return v;
    }
}
