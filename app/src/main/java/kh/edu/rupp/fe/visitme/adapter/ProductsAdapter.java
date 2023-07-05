package kh.edu.rupp.fe.visitme.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.api.model.Product;
import kh.edu.rupp.fe.visitme.databinding.ViewHolderCourseBinding;

public class ProductsAdapter extends ListAdapter<Product, ProductsAdapter.ProductViewHolder> {

    public ProductsAdapter() {

        super(new DiffUtil.ItemCallback<Product>() {
            @Override
            public boolean areItemsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Product oldItem, @NonNull Product newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }

    protected ProductsAdapter(@NonNull AsyncDifferConfig<Product> config) {
        super(config);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderCourseBinding binding = ViewHolderCourseBinding.inflate(layoutInflater,parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product course = getItem(position);
        holder.bind(course);
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ViewHolderCourseBinding binding;
        public ProductViewHolder(ViewHolderCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Product course){
            Picasso.get().load(course.getImageUrl()).into(binding.imgCourse);
            binding.courseTitle.setText(course.getName());
            binding.courseDescription.setText(course.getDescription());
        }
    }
}
