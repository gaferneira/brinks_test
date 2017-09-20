package co.com.brinks.test.viewControllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.brinks.test.R;
import co.com.brinks.test.base.ParentActivity;
import co.com.brinks.test.base.ParentFragment;
import co.com.brinks.test.models.App;
import co.com.brinks.test.models.Attribute;
import co.com.brinks.test.utils.VolleyResult;

public class CategoriesFragment extends ParentFragment implements CategoriesAdapter.CategoryRowListener{

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout swipeLayout;

    CategoriesAdapter mAdapter;
    List<App> mListApps;
    List<Attribute> mListCategorias;

    private Attribute todas;


    public CategoriesFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the selector_icon_saldo_home for this fragment
        rootView = inflater.inflate(R.layout.fragment_categories, container, false);
        ButterKnife.bind(this, rootView);

        configList();
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(mListCategorias==null || mListCategorias.isEmpty()) {
            swipeLayout.setProgressViewOffset(false, 0,
                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
            swipeLayout.setRefreshing(true);
            setItems();
        }
    }

    private void configList() {

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mListCategorias = new ArrayList<>();
        mAdapter = new CategoriesAdapter(getActivity(), mListCategorias, this);
        mRecyclerView.setAdapter(mAdapter);

        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setItems();
            }
        });

    }

    private void setItems() {

        swipeLayout.setRefreshing(true);
        getVolleyService().getTopApps(new VolleyResult<String>() {

                    @Override
                    public void onSuccess(String response) throws Exception {

                        JSONObject jsonObject = new JSONObject(response);
                        JSONObject feedJson = jsonObject.getJSONObject("feed");
                        JSONArray entryArray =  feedJson.getJSONArray("entry");
                        int lon = entryArray.length();

                        mListCategorias = new ArrayList<>();
                        mListApps = new ArrayList<>();
                        HashMap<String, Attribute> hashMap = new HashMap<>();


                        todas = new Attribute();
                        todas.setLabel(getString(R.string.title_top));
                        mListCategorias.add(todas);
                        if (lon > 0) {
                            Gson gson = new Gson();
                            for (int i = 0; i < lon; i++) {
                                App app = gson.fromJson(entryArray.getString(i), App.class);
                                Attribute attribute = app.getCategory().getAttributes();
                                if (!hashMap.containsKey(attribute.getId())){
                                    hashMap.put(attribute.getId(), attribute);
                                    mListCategorias.add(attribute);
                                }
                                mListApps.add(app);
                            }
                        }

                        mAdapter = new CategoriesAdapter(getActivity(), mListCategorias, CategoriesFragment.this);

                        if (mRecyclerView!=null) {
                            mRecyclerView.setAdapter(mAdapter);
                        }
                        if (swipeLayout!=null) {
                            swipeLayout.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onError(VolleyError error, String response) {
                        if (swipeLayout!=null) {
                            swipeLayout.setRefreshing(false);
                        }
                        Toast.makeText(getActivity(), response!=null ? response : error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    @Override
    public void onClickItem(View view, Attribute category) {
        Intent intent = new Intent(getActivity(), ListAppActivity.class);
        intent.putExtra(ParentActivity.EXTRA_LIST, (Serializable)  mListApps);
        if (category!=todas) {
            intent.putExtra(ParentActivity.EXTRA_CATEGORY, category.getLabel());
        }
        startActivity(intent);
    }
}
