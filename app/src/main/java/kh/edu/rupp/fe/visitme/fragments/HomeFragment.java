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
import kh.edu.rupp.fe.visitme.R;
import kh.edu.rupp.fe.visitme.adapter.CoursesAdapter;
import kh.edu.rupp.fe.visitme.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.imgProfile.setOnClickListener(v->navigateToProfile());
        binding.seeAllCourses.setOnClickListener(v->navigateToCourse());
        return binding.getRoot();
    }
    private void navigateToProfile() {
        Intent intent = new Intent(getContext(), ProfileActivity.class);
        intent.putExtra("message", "Hello My Profile");
        startActivity(intent);
    }
    private void navigateToCourse() {
        CourseFragment courseFragment= new CourseFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.mainLayout, courseFragment)
                .addToBackStack(null)
                .commit();
    }
}
