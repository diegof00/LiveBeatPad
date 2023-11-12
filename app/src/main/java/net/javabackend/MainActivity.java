package net.javabackend;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
  }

  @SuppressLint("NonConstantResourceId")
  public void onSoundButtonClick(View view) {

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
      mediaPlayer.start();
      mediaPlayer.setOnCompletionListener(MediaPlayer::release);
    }

    //detect button  pressed
  }
}