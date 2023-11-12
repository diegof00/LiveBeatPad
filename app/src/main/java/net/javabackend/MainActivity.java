package net.javabackend;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.media.MediaPlayer.OnErrorListener;
import android.media.MediaPlayer.OnPreparedListener;
import android.provider.MediaStore.Audio.Media;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

  private static final List<MediaPlayer> mediaPlayers = new ArrayList<>();


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @SuppressLint("NonConstantResourceId")
  public void onSoundButtonClick(View view) throws IOException {

    //detect if a sound is playing
    mediaPlayers.stream().filter(MediaPlayer::isPlaying).forEach(MediaPlayer::stop);
    mediaPlayers.clear();


    MediaPlayer mediaPlayer = null;
    switch (view.getId()) {
      case R.id.button1:
        mediaPlayer = MediaPlayer.create(this, R.raw.rocky);
        break;
      case R.id.button2:
        mediaPlayer = MediaPlayer.create(this, R.raw.x_files);
        break;
      case R.id.button3:
        mediaPlayer = MediaPlayer.create(this, R.raw.no_le_concedo_ningun_punto);
        break;
      // Agrega más casos según la cantidad de botones que tengas
    }


    if (mediaPlayer != null && !mediaPlayer.isPlaying()) {
      mediaPlayers.add(mediaPlayer);
      mediaPlayer.setOnPreparedListener(this.prepare());
      mediaPlayer.setOnCompletionListener(this.completionListener());
      mediaPlayer.setOnErrorListener(this.listener());
    }


    //detect button  pressed
  }

  private OnPreparedListener prepare() {
    return MediaPlayer::start;
  }

  private OnCompletionListener completionListener() {
    return MediaPlayer::release;
  }

  private OnErrorListener listener() {
    return (mp, what, extra) -> {
      mp.reset();
      return false;
    };
  }
}