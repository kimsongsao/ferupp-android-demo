package kh.edu.rupp.fe.visitme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import kh.edu.rupp.fe.visitme.ProfileActivity;
import kh.edu.rupp.fe.visitme.databinding.FragmentAccountBinding;
import kh.edu.rupp.fe.visitme.databinding.FragmentHomeBinding;

public class AccountFragment extends Fragment {
    private FragmentAccountBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }
    private void navigateToProfile() {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra("message", "Hello My Profile");
        startActivity(intent);
    }
}
