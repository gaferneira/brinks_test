package co.com.brinks.test.viewControllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.VolleyError;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.brinks.test.R;
import co.com.brinks.test.models.App;
import co.com.brinks.test.utils.VolleyResult;
import co.com.brinks.test.utils.VolleyService;

public class AppsAdapter extends ArrayAdapter<App>{

    private String category;

    List<App> objects;
    List<App> filterObjects;

    public AppsAdapter(Context context, List<App> items) {
        super(context, R.layout.item_app, items);
        objects = items;
        filterObjects = items;
    }

    @Nullable
    @Override
    public App getItem(int position) {
        return filterObjects.get(position);
    }

    @Override
    public int getCount() {
        return filterObjects==null ? 0 : filterObjects.size();
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                FilterResults filterResults = new FilterResults();
                List<App> tempList=new ArrayList<App>();

                if ((constraint== null || constraint.length()==0) && category==null){
                    tempList = objects;
                } else if(objects!=null) {
                    for (App app : objects){
                        boolean isCategory = category==null || category.matches(app.getCategory().getAttributes().getLabel());
                        boolean contains = (constraint == null || constraint.length() == 0) || app.getName().getLabel().toUpperCase().contains(constraint.toString().toUpperCase());
                        if (isCategory && contains) {
                            tempList.add(app);
                        }
                    }
                }

                //following two lines is very important
                //as publish result can only take FilterResults objects
                filterResults.values = tempList;
                filterResults.count = tempList.size();
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                //noinspection unchecked
                if(results.values==null){
                    return;
                }

                filterObjects = (List<App>) results.values;
                if (results.count > 0) {
                    notifyDataSetChanged();
                } else {
                    notifyDataSetInvalidated();
                }
            }
        };
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        AppRowHolder holder;
        View view;
        if (convertView==null || convertView.getTag()==null) {
            view = View.inflate(getContext(), R.layout.item_app, null);
            holder = new AppRowHolder(view);
            view.setTag(holder);
        }
        else
        {
            view = convertView;
            holder = (AppRowHolder) view.getTag();
        }

        final App item = filterObjects.get(position);

        holder.nameTextView.setText(item.getName().getLabel());
        holder.dateTextView.setText(item.getReleaseDate().getAttributes().getLabel());

        VolleyService.getInstance().downloadImage(item.getImages()[0].getLabel(), holder.imageView, 0, new VolleyResult<Bitmap>() {
            @Override
            public void onSuccess(Bitmap response) throws Exception {

            }

            @Override
            public void onError(VolleyError error, Bitmap response) {

            }
        });

        return view;
    }

    class AppRowHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.imageView)
        protected ImageView imageView;

        @BindView(R.id.nameTextView)
        protected TextView nameTextView;

        @BindView(R.id.dateTextView)
        protected TextView dateTextView;

        public AppRowHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
