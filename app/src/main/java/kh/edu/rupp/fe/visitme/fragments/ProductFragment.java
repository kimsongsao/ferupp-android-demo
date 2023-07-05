package kh.edu.rupp.fe.visitme.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import kh.edu.rupp.fe.visitme.ProfileActivity;
import kh.edu.rupp.fe.visitme.R;
import kh.edu.rupp.fe.visitme.adapter.CoursesAdapter;
import kh.edu.rupp.fe.visitme.adapter.ProductGridsAdapter;
import kh.edu.rupp.fe.visitme.adapter.ProductsAdapter;
import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.api.model.Product;
import kh.edu.rupp.fe.visitme.api.service.ApiService;
import kh.edu.rupp.fe.visitme.databinding.FragmentCourseBinding;
import kh.edu.rupp.fe.visitme.databinding.FragmentProductBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {
    private FragmentProductBinding binding;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProductBinding.inflate(inflater, container, false);
        loadCourseList();
        return binding.getRoot();
    }
    private void loadCourseList(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit httpClient = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        ApiService apiService = httpClient.create(ApiService.class);

        Call<List<Product>> task = apiService.getProductList();
        task.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if (response.isSuccessful()){
                    //showCourseList(response.body());
                    showGrid(response.body());
                }else{
                    Toast.makeText(getContext(), "Failed to get course list",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });
    }
    private void showCourseList(List<Product> courses){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        binding.recyclerCourseView.setLayoutManager(linearLayoutManager);
        ProductsAdapter adapter = new ProductsAdapter();
        adapter.submitList(courses);
//        adapter.setClickListener(this);
        binding.recyclerCourseView.setAdapter(adapter);
    }
    private void showGrid(List<Product> products){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);
        binding.recyclerCourseView.setLayoutManager(gridLayoutManager);
        ProductGridsAdapter adapter = new ProductGridsAdapter();
        adapter.submitList(products);
        // Applying OnClickListener to our Adapter
        adapter.setOnClickListener(new ProductGridsAdapter.OnClickListener() {
            @Override
            public void onClick(int position, Product model) {
                Log.i("Product Name: ", model.getName());
                Intent intent = new Intent(getContext(), ProfileActivity.class);
                intent.putExtra("id", model.getId());

                startActivity(intent);
            }
        });

        binding.recyclerCourseView.setAdapter(adapter);
    }
}