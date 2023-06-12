package kh.edu.rupp.fe.visitme.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncDifferConfig;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import kh.edu.rupp.fe.visitme.R;
import kh.edu.rupp.fe.visitme.api.model.Course;
import kh.edu.rupp.fe.visitme.databinding.ViewHolderCourseBinding;

public class CoursesAdapter extends ListAdapter<Course, CoursesAdapter.CourseViewHolder> {

    public CoursesAdapter() {

        super(new DiffUtil.ItemCallback<Course>() {
            @Override
            public boolean areItemsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Course oldItem, @NonNull Course newItem) {
                return oldItem.getId() == newItem.getId();
            }
        });
    }

    protected CoursesAdapter(@NonNull AsyncDifferConfig<Course> config) {
        super(config);
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolderCourseBinding binding = ViewHolderCourseBinding.inflate(layoutInflater,parent,false);
        return new CourseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        Course course = getItem(position);
        holder.bind(course);
    }

    public static class CourseViewHolder extends RecyclerView.ViewHolder {
        private final ViewHolderCourseBinding binding;
        public CourseViewHolder(ViewHolderCourseBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
        public void bind(Course course){
            Picasso.get().load(course.getImageUrl()).into(binding.imgCourse);
            binding.courseTitle.setText(course.getTitle());
            binding.courseDescription.setText(course.getDescription());
        }
    }
}
