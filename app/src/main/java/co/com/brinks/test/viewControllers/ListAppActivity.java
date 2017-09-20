package co.com.brinks.test.viewControllers;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.brinks.test.R;
import co.com.brinks.test.base.ParentActivity;
import co.com.brinks.test.models.App;

public class ListAppActivity extends ParentActivity {

    @BindView(R.id.list_view)
    ListView mListView;

    @BindView(R.id.search_view)
    SearchView searchView;

    AppsAdapter mAdapter;
    List<App> mList;
    private String category;

    Comparator<App> alfabeticComparator = new Comparator<App>() {
        @Override
        public int compare(App o1, App o2) {
            return o1.getName().getLabel().compareTo(o2.getName().getLabel());
        }
    };

    Comparator<App> dateComparator = new Comparator<App>() {
        @Override
        public int compare(App o1, App o2) {
            return o2.getReleaseDate().getLabel().compareTo(o1.getReleaseDate().getLabel());
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_app);
        ButterKnife.bind(this);

        mList = (List<App>) getIntent().getSerializableExtra(EXTRA_LIST);
        category = getIntent().getStringExtra(EXTRA_CATEGORY);

        if (category!=null){
            setTitle(category);
        } else {
            setTitle(getString(R.string.title_top));
        }


        configList();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.list_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.alfabetic_order){
            Collections.sort(mList, alfabeticComparator);
            configList();
        } else if (id == R.id.date_order){
            Collections.sort(mList, dateComparator);
            configList();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void configList() {

        mAdapter = new AppsAdapter(this, mList);
        mAdapter.setCategory(category);
        mListView.setAdapter(mAdapter);

        mAdapter.getFilter().filter("");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mAdapter.getFilter().filter(newText);
                return true;
            }
        });

    }
}
