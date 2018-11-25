package mx.edu.ittepic.dadm_u3_ejercicio7;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    MediaPlayer musica,corri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new Lienzo(this));
    }

    public void empiezo(){
        musica = MediaPlayer.create(this,R.raw.start);//direccion
        musica.start();

    }
    public void Pararempiezo(){
       musica.pause();

    }
    public void pierdo(){
        musica = MediaPlayer.create(this,R.raw.pierde);//direccion
        musica.start();

    }
    public void corrido(){
        musica = MediaPlayer.create(this,R.raw.corrido);//direccion
        musica.start();

    }
    public void ganar(){
        musica = MediaPlayer.create(this,R.raw.yuju);//direccion
        musica.start();

    }
}
