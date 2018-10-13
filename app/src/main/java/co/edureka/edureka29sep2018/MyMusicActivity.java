package co.edureka.edureka29sep2018;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class MyMusicActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnPlay, btnStop;
    String songToPlay, songFromUrl;

    MediaPlayer mediaPlayer;

    VideoView videoView;

    void initViews(){
        btnPlay = findViewById(R.id.buttonPlay);
        btnStop = findViewById(R.id.buttonStop);

        videoView = findViewById(R.id.videoView);
        // All the media controls will come up on Video View | Play Pause Seek UI
        videoView.setMediaController(new MediaController(this));

        //videoView.setVideoPath();
        //videoView.setVideoURI();
        //videoView.start();

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        songToPlay = Environment.getExternalStorageDirectory().getPath()+"/Music/Nights.mp3";

        // replace dummy url below with some real song url
        songFromUrl = "https://somedomian.com/somesong.mp3";

        mediaPlayer = new MediaPlayer();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_music);
        initViews();
    }

    void playSong(){

        try {

            // Plays Local Audio File
            //mediaPlayer.setDataSource(songToPlay);

            // Plays from URL
            mediaPlayer.setDataSource(this, Uri.parse(songFromUrl));

            mediaPlayer.prepare();
            mediaPlayer.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    void stopSong(){
        mediaPlayer.stop();
        mediaPlayer.release();
    }

    void pauseSong(){
        mediaPlayer.pause();
    }

    void seekSong(int mSecs){
        mediaPlayer.seekTo(mSecs);
    }


    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.buttonPlay){
            playSong();
        }else{
            stopSong();
        }
    }
}
