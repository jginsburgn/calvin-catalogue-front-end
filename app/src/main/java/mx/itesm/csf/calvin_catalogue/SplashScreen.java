package mx.itesm.csf.calvin_catalogue;


import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import java.lang.reflect.Field;
import java.util.Random;


public class SplashScreen extends AppCompatActivity {

    final Random rnd = new Random();
    String vidName;
    int drawableId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* lock orientation to landscape */
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setTheme(android.R.style.ThemeOverlay_Material_Dark);
        /* Remove title bar */
            this.requestWindowFeature(Window.FEATURE_NO_TITLE);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        /* Hide ActionBar*/
            ActionBar m_myActionBar = getSupportActionBar();
            m_myActionBar.hide();

        /*RANDOM IMAGE NAME*/
            vidName = "vid_" + rnd.nextInt(500)%3; // 3 vid

            try {
                Class res = R.raw.class;
                Field field = res.getField(vidName);
                drawableId = field.getInt(null);
            }
            catch (Exception e) {
                Log.e("MyTag", "Failure to get drawable id." + vidName, e);
            }

            try
            {
                VideoView videoHolder = new VideoView(this);
                setContentView(videoHolder);
                Uri video = Uri.parse("android.resource://" + getPackageName() + "/" + drawableId);
                videoHolder.setVideoURI(video);

                videoHolder.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        jump();
                    }
                });

                videoHolder.start();

            } catch (Exception ex) {
                jump();
            }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        jump();
        return true;
    }

    private void jump()
    {
        if (isFinishing())
            return;
        startActivity(new Intent(this, Login.class));
        finish();
    }
}
