package futsal.yo.com.yofutsal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import futsal.yo.com.yofutsal.Helper.AccessToken;

/**
 * Created by Rifka on 10/04/2018.
 */

public class Intro extends Activity{

    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    private int[] layouts;
    private Button btnSkip, btnNext;
    private AccessToken accessToken;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_intro);

        getAccessToken();

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutDots);
        btnSkip = (Button) findViewById(R.id.btn_skip);
        btnNext = (Button) findViewById(R.id.btn_next);

        layouts = new int[] {
                R.layout.intro1,
                R.layout.intro2,
                R.layout.intro3,
        };

        //adding bottom dots
        addBottomDots(0);

        viewPagerAdapter = new ViewPagerAdapter();
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

//  cekAccessToken
    private void getAccessToken(){
        String token = accessToken.getToken(Intro.this);
        if(!token.equals("")){
            intent = new Intent(Intro.this, TabMenu.class);
            startActivity(intent);
            finish();
        }
    }

    //btnSkip
    public void btnSkipClick(View v) {
        launchHomeScreen();
    }

    //btnNext
    public void btnNextClick(View v) {
        //checking for last page
        //if last page home screen will be launched
        int current = getItem(1);
        if (current < layouts.length) {
            //move to next screen
            viewPager.setCurrentItem(current);
        } else {
            launchHomeScreen();
        }
    }

    //ViewPager Change Listener
    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            addBottomDots(position);

            //changing the next button text
            if (position == layouts.length - 1) {
                btnNext.setText(getString(R.string.start));
                btnSkip.setVisibility(View.GONE);
            } else {
                btnNext.setText(getString(R.string.next));
                btnSkip.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //add Bottom Dots
    private void addBottomDots(int currentPage) {
        dots = new TextView[layouts.length];

        dotsLayout.removeAllViews();
        for (int i=0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.dot_inactive));
            dotsLayout.addView(dots[i]);
        }

        if (dots.length > 0) {
            dots[currentPage].setTextColor(getResources().getColor(R.color.dot_active));
        }
    }

    private int getItem (int i) {
        return viewPager.getCurrentItem() + i;
    }

    private void launchHomeScreen() {
        startActivity(new Intent(this, Login.class));
        finish();
    }

    //View Pager Adapter
    public class ViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public ViewPagerAdapter() {

        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(layouts[position], container, false);
            container.addView(view);

            return view;
        }

        @Override
        public int getCount() {
            return layouts.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
