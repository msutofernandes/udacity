package com.example.android.musicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class AlbumsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);

        ImageView artists_icon = (ImageView) findViewById(R.id.artists_icon);

        artists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent artistsIconIntent = new Intent(AlbumsActivity.this, ArtistsActivity.class);
                startActivity(artistsIconIntent);
            }
        });

        ImageView playlists_icon = (ImageView) findViewById(R.id.playlist_icon);

        playlists_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent playlistsIconIntent = new Intent(AlbumsActivity.this, PlaylistActivity.class);
                startActivity(playlistsIconIntent);
            }
        });

        ImageView musics_icon = (ImageView) findViewById(R.id.musics_icon);

        musics_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent musicsIconIntent = new Intent(AlbumsActivity.this, MusicsActivity.class);
                startActivity(musicsIconIntent);
            }
        });
    }
}
