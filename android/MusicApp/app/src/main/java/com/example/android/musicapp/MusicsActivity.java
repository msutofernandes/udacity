package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MusicsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_musics);

        ImageView artists_icon = (ImageView) findViewById(R.id.artists_icon);

        artists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artistsIconIntent = new Intent(MusicsActivity.this, ArtistsActivity.class);
                startActivity(artistsIconIntent);
            }
        });

        ImageView albums_icon = (ImageView) findViewById(R.id.albums_icon);

        albums_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent albumsIconIntent = new Intent(MusicsActivity.this, AlbumsActivity.class);
                startActivity(albumsIconIntent);
            }
        });

        ImageView playlists_icon = (ImageView) findViewById(R.id.playlist_icon);

        playlists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playlistsIconIntent = new Intent(MusicsActivity.this, PlaylistActivity.class);
                startActivity(playlistsIconIntent);
            }
        });
    }
}
