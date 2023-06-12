package kh.edu.rupp.fe.visitme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import kh.edu.rupp.fe.visitme.ProfileActivity;
import kh.edu.rupp.fe.visitme.adapter.CoursesAdapter;
import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.api.service.ApiService;
import kh.edu.rupp.fe.visitme.databinding.FragmentCourseBinding;
import kh.edu.rupp.fe.visitme.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CourseFragment extends Fragment {
    private FragmentCourseBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentCourseBinding.inflate(inflater, container, false);
        loadCourseList();
        return binding.getRoot();
    }
    private void loadCourseList(){
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<Course>> task = apiService.getCourseList();
        task.enqueue(new Callback<List<Course>>() {
            @Override
            public void onResponse(Call<List<Course>> call, Response<List<Course>> response) {
                if (response.isSuccessful()){
                    showCourseList(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get course list",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Course>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void showCourseList(List<Course> courses){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerCourseView.setLayoutManager(linearLayoutManager);
        CoursesAdapter adapter = new CoursesAdapter();
        adapter.submitList(courses);
        binding.recyclerCourseView.setAdapter(adapter);
    }
}
