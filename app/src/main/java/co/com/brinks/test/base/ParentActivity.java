package co.com.brinks.test.base;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import butterknife.ButterKnife;
import co.com.brinks.test.viewControllers.MainActivity;
import co.com.brinks.test.R;
import co.com.brinks.test.utils.VolleyService;

public class ParentActivity extends AppCompatActivity {

    public static final String EXTRA_LIST = "extra_list";
    public static final String EXTRA_CATEGORY = "extra_category";

    ProgressDialog processDialog;
    protected Toolbar toolbar;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar!=null) {
            setSupportActionBar(toolbar);
            if (!(this instanceof MainActivity)){
                getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            }
        }

        if (getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==android.R.id.home) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(int titleID) {
        super.setTitle(titleID);
    }

    @Override
    public void finish() {
        if(processDialog!=null) {
            processDialog.dismiss();
        }
        super.finish();
    }

    public ProgressDialog getProcessDialog() {
        if(processDialog==null) {
            processDialog = new ProgressDialog(this);
            processDialog.setCanceledOnTouchOutside(false);
        }
        return processDialog;
    }

    public VolleyService getVolleyService(){
        return VolleyService.getInstance();
    }
}
