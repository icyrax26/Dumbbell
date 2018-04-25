package com.kylebruney.android.madcow;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kylebruney.android.madcow.Activity.BaseActivity;
import com.kylebruney.android.madcow.Activity.SetupWeightActivity;

import java.util.ArrayList;

public class OnBoardingActivity extends BaseActivity {

    private LinearLayout mPagerIndicator;
    private int mDotsCount;
    private ImageView[] mDots;
    private ViewPager mOnboardPager;
    private OnBoard_Adapter mAdapter;
    private Button mBtnGetStarted;
    int mPreviousPos = 0;
    ArrayList<OnBoardItem> onBoardItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);

        mBtnGetStarted = findViewById(R.id.btn_get_started);
        mOnboardPager = findViewById(R.id.pager_introduction);
        mPagerIndicator = findViewById(R.id.viewPagerCountDots);

        loadData();

        mAdapter = new OnBoard_Adapter(this,onBoardItems);
        mOnboardPager.setAdapter(mAdapter);
        mOnboardPager.setCurrentItem(0);
        mOnboardPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < mDotsCount; i++) {
                    mDots[i].setImageDrawable(ContextCompat.getDrawable
                            (OnBoardingActivity.this, R.drawable.non_selected_item_dot));
                }

                mDots[position].setImageDrawable(ContextCompat.getDrawable
                        (OnBoardingActivity.this, R.drawable.selected_item_dot));


                int pos = position+1;

                if (pos == mDotsCount && mPreviousPos == (mDotsCount -1))
                     show_animation();
                else if (pos == (mDotsCount -1) && mPreviousPos == mDotsCount)
                     hide_animation();

                mPreviousPos = pos;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mBtnGetStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent settingsIntent = new Intent(OnBoardingActivity.this, SetupWeightActivity.class);
                OnBoardingActivity.this.startActivity(settingsIntent);
            }
        });

        setUiPageViewController();

    }

    public void loadData() {
        int[] header = {R.string.ob_header1, R.string.ob_header2, R.string.ob_header3};
        int[] desc = {R.string.ob_desc1, R.string.ob_desc2, R.string.ob_desc3};
        int[] imageId = {R.drawable.applogo2, R.drawable.applogo2, R.drawable.applogo2};

        for (int i = 0; i < imageId.length; i++)
        {
            OnBoardItem item = new OnBoardItem();
            item.setImageID(imageId[i]);
            item.setTitle(getResources().getString(header[i]));
            item.setDescription(getResources().getString(desc[i]));

            onBoardItems.add(item);
        }
    }

    public void show_animation()
    {
        Animation show = AnimationUtils.loadAnimation(this, R.anim.slide_up_anim);

        mBtnGetStarted.startAnimation(show);

        show.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                mBtnGetStarted.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBtnGetStarted.clearAnimation();
            }
        });
    }

    public void hide_animation()
    {
        Animation hide = AnimationUtils.loadAnimation(this, R.anim.slide_down_anim);

        mBtnGetStarted.startAnimation(hide);

        hide.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                mBtnGetStarted.clearAnimation();
                mBtnGetStarted.setVisibility(View.GONE);
            }
        });
    }

    private void setUiPageViewController() {
        mDotsCount = mAdapter.getCount();
        mDots = new ImageView[mDotsCount];

        for (int i = 0; i < mDotsCount; i++) {
            mDots[i] = new ImageView(this);
            mDots[i].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.non_selected_item_dot));

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            params.setMargins(6, 0, 6, 0);

            mPagerIndicator.addView(mDots[i], params);
        }
        mDots[0].setImageDrawable(ContextCompat.getDrawable(OnBoardingActivity.this, R.drawable.selected_item_dot));
    }
}
