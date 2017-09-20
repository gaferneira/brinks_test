package co.com.brinks.test.viewControllers;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.brinks.test.R;
import co.com.brinks.test.base.ParentActivity;

public class MainActivity extends ParentActivity {

    private static final long SPLASH_TIME_OUT = 3000;

    @BindView(R.id.splashView)
    View splashView;

    @BindView(R.id.mainContentView)
    View mainContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Show list
                splashView.animate().alpha(0f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        mainContentView.setVisibility(View.VISIBLE);
                        splashView.setVisibility(View.GONE);
                    }
                });
            }
        }, SPLASH_TIME_OUT);



    }

    @Override
    protected void onResume() {
        super.onResume();

    }
}
