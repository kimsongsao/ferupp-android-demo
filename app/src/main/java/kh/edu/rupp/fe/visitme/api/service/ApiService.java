package kh.edu.rupp.fe.visitme.api.service;

import java.util.List;

import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.api.model.Product;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/course.json")
    Call<List<Course>> getCourseList();
    @GET("/kimsongsao/ferupp/main/products.json")
    Call<List<Product>> getProductList();
}
