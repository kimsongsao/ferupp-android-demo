package kh.edu.rupp.fe.visitme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import kh.edu.rupp.fe.visitme.api.model.Product;
import kh.edu.rupp.fe.visitme.databinding.ViewHolderCourseBinding;
import kh.edu.rupp.fe.visitme.databinding.ViewHolderProductGridBinding;

public class ProductGridsAdapter extends ListAdapter<Product, ProductGridsAdapter.ProductViewHolder> {
    private List<Product> items;
    private OnClickListener onClickListener;
    public ProductGridsAdapter() {

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

    protected ProductGridsAdapter(@NonNull AsyncDifferConfig<Product> config) {
        super(config);
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderProductGridBinding binding = ViewHolderProductGridBinding.inflate(layoutInflater,parent,false);
        return new ProductViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product course = getItem(position);
        holder.bind(course);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onClickListener != null) {
                    onClickListener.onClick(position, course);
                }
            }
        });
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        private final ViewHolderProductGridBinding binding;
        public ProductViewHolder(ViewHolderProductGridBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Product course){
            Picasso.get().load(course.getImageUrl()).into(binding.imgCourse);
            binding.courseTitle.setText(course.getName());
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClick(int position, Product model);
    }
}
