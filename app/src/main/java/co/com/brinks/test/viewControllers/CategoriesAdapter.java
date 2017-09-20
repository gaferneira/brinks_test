package co.com.brinks.test.viewControllers;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.brinks.test.R;
import co.com.brinks.test.models.Attribute;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.CategoryRowHolder>{

    private List<Attribute> itemList;

    private Activity mActivity;
    private CategoryRowListener mListener;

    private int[] colors;

    public CategoriesAdapter(Activity activity, List<Attribute> itemList, CategoryRowListener listener) {
        this.itemList = itemList;
        this.mActivity = activity;
        this.mListener = listener;

        colors = new int[]{
                R.color.color_1,
                R.color.color_2,
                R.color.color_3,
                R.color.color_4,
                R.color.color_5
        };
    }

    @Override
    public CategoryRowHolder onCreateViewHolder(ViewGroup viewGroup, final int position) {
        final View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        return new CategoryRowHolder(v);
    }

    @Override
    public void onBindViewHolder(CategoryRowHolder rowHolder, final int position) {

        final Attribute item = itemList.get(position);

        rowHolder.nameTextView.setText(item.getLabel());

        int color = colors[position % colors.length];
        color = mActivity.getResources().getColor(color);

        rowHolder.contentView.setBackgroundColor(color);
        rowHolder.nameTextView.setTextColor(color);

        rowHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onClickItem(view, itemList.get(position));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return null != itemList ? itemList.size() : 0;
    }

    public interface CategoryRowListener {
        void onClickItem(View view, Attribute attribute);
    }

    class CategoryRowHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nameTextView)
        protected TextView nameTextView;

        @BindView(R.id.contentView)
        protected View contentView;

        public CategoryRowHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
