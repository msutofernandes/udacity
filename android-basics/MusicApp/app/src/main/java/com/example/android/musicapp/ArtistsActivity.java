package com.example.android.musicapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class ArtistsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);

        ImageView albums_icon = (ImageView) findViewById(R.id.albums_icon);

        albums_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIconIntent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
                startActivity(albumsIconIntent);
            }
        });

        ImageView musics_icon = (ImageView) findViewById(R.id.musics_icon);

        musics_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicsIconIntent = new Intent(ArtistsActivity.this, MusicsActivity.class);
                startActivity(musicsIconIntent);
            }
        });

        ImageView playlists_icon = (ImageView) findViewById(R.id.playlist_icon);

        playlists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playlistsIconIntent = new Intent(ArtistsActivity.this, PlaylistActivity.class);
                startActivity(playlistsIconIntent);
            }
        });
    }
}
