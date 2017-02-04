package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class PlaylistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist);

        ImageView artists_icon = (ImageView) findViewById(R.id.artists_icon);

        artists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artistsIconIntent = new Intent(PlaylistActivity.this, ArtistsActivity.class);
                startActivity(artistsIconIntent);
            }
        });

        ImageView albums_icon = (ImageView) findViewById(R.id.albums_icon);

        albums_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIconIntent = new Intent(PlaylistActivity.this, AlbumsActivity.class);
                startActivity(albumsIconIntent);
            }
        });

        ImageView musics_icon = (ImageView) findViewById(R.id.musics_icon);

        musics_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicsIconIntent = new Intent(PlaylistActivity.this, MusicsActivity.class);
                startActivity(musicsIconIntent);
            }
        });
    }
}
