package kh.edu.rupp.fe.visitme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.os.LocaleListCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.MediaController;
import android.widget.VideoView;

import com.google.android.material.navigation.NavigationBarView;

import java.util.Locale;

import kh.edu.rupp.fe.visitme.databinding.ActivityMainBinding;
import kh.edu.rupp.fe.visitme.fragments.AccountFragment;
import kh.edu.rupp.fe.visitme.fragments.ChatFragment;
import kh.edu.rupp.fe.visitme.fragments.CourseFragment;
import kh.edu.rupp.fe.visitme.fragments.HomeFragment;
import kh.edu.rupp.fe.visitme.fragments.MoreFragment;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        navigateFragment(new HomeFragment());
        binding.mainBottomNav.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.navHome) {
                navigateFragment(new HomeFragment());
            } else if (item.getItemId() == R.id.navCourses) {
                navigateFragment(new CourseFragment());
            } else if (item.getItemId() == R.id.navChat) {
                navigateFragment(new ChatFragment());
            } else if (item.getItemId() == R.id.navAccount) {
                navigateFragment(new AccountFragment());
            } else if (item.getItemId() == R.id.navMore) {
                navigateFragment(new MoreFragment());
            } else {

            }
            return false;
        });
    }

    private void navigateFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(binding.mainLayout.getId(), fragment);
        fragmentTransaction.commit();
    }

}