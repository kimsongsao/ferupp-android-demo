package kh.edu.rupp.fe.visitme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import java.util.Locale;

import kh.edu.rupp.fe.visitme.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.imgProfile.setOnClickListener(v->navigateToProfile());
    }

    private void navigateToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        intent.putExtra("message", "Hello My Profile");
        startActivity(intent);
    }

}