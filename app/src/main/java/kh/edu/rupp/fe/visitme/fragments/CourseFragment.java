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
import kh.edu.rupp.fe.visitme.databinding.FragmentCourseBinding;
import kh.edu.rupp.fe.visitme.databinding.FragmentHomeBinding;

public class CourseFragment extends Fragment {
    private FragmentCourseBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCourseBinding.inflate(inflater, container, false);

        return binding.getRoot();
    }

}
