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

    private LocationManager locMana;
    private LocationListener locLis;
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
        final TextView GPS = (TextView) v.findViewById(R.id.RG_GPSLocation);
        //init main activity
        final MainActivity mActivity= new MainActivity();

        final TextView RGPuzzleField = (TextView) v.findViewById(R.id.RG_GPSLocation);
        //define location manager
        locMana = (LocationManager) mActivity.getSystemService(Context.LOCATION_SERVICE);

        locLis = new LocationListener(){

            @Override
            public void onLocationChanged(Location location){
                startLoc = location;

                GPS.setText("" + startLoc.getLatitude()+ "-" + startLoc.getLongitude());

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) { }

            @Override
            public void onProviderEnabled(String provider) { }

            @Override
            public void onProviderDisabled(String provider) { }
        };

        SendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Get GPS coordinates from TextView
                String GPSCoordinates = GPS.getText().toString();
                //add puzzle to SQLite db( !TODO if there is time)
                String Puzzle = GPS.getText().toString();
                //Send puzzle button
                //check if ansver is empty/default
                //check if puzzle is empty / default
                //


            }
        });
        CameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //take picture from Camera
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
                // Find the last picture
                String[] projection = new String[]{
                        MediaStore.Images.ImageColumns._ID,
                        MediaStore.Images.ImageColumns.DATA,
                        MediaStore.Images.ImageColumns.BUCKET_DISPLAY_NAME,
                        MediaStore.Images.ImageColumns.DATE_TAKEN,
                        MediaStore.Images.ImageColumns.MIME_TYPE
                };
                final Cursor cursor = getContext().getContentResolver()
                        .query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, projection, null,
                                null, MediaStore.Images.ImageColumns.DATE_TAKEN + " DESC");

// Put Last Image in the image view
                /*
                if (cursor.moveToFirst()) {
                    final ImageView imageView = (ImageView) findViewById(R.id.pictureView);
                    String imageLocation = cursor.getString(1);
                    File imageFile = new File(imageLocation);
                    if (imageFile.exists()) {   // TODO: is there a better way to do this?
                        Bitmap bm = BitmapFactory.decodeFile(imageLocation);
                        imageView.setImageBitmap(bm);
                    }
                }
                */

            }
        });
        //role is selected
        Log.i("pagerView","loaded");
        return v;
    }
}
