package kh.edu.rupp.fe.visitme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import kh.edu.rupp.fe.visitme.ProfileActivity;
import kh.edu.rupp.fe.visitme.R;
import kh.edu.rupp.fe.visitme.adapter.CoursesAdapter;
import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.api.model.Product;
import kh.edu.rupp.fe.visitme.api.service.ApiService;
import kh.edu.rupp.fe.visitme.databinding.FragmentHomeBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class HomeFragment extends Fragment {
    private FragmentHomeBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        binding.imgProfile.setOnClickListener(v->navigateToProfile());
        binding.seeAllCourses.setOnClickListener(v->navigateToCourse());
        loadProductList();
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
    private void loadProductList(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://ferupp.s3.ap-southeast-1.amazonaws.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<Product>> task = apiService.getProductList();
        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    Toast.makeText(getContext(), "Successfully to get course list",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "Failed to get course list",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Log.e("Error API: ", t.getMessage());
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
}
