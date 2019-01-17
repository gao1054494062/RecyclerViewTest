package com.example.lenovo.viewpagergridview;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private boolean isDraggingToLeft = false;

    public static final int GAME_PAGE_COUNT = 3;
    public static final int GAME_COUNT_ONE_PAGE = 19;
    public static final int TOP_BOTTOM_MARGIN = 8;
    public static final int LEFT_RIGHT_MARGIN = 12;
    private LinearLayout.LayoutParams mLayoutParams = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, 196 * 3);

    private List<GameEntranceItem> gameEntranceList;
    private ViewPager gameListViewPager;
    private IndicatorView gameIndicatorView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ScreenUtil.init(this);
        initData();
        initView();
        init();
    }


    private void initView() {
        gameListViewPager = (ViewPager) findViewById(R.id.main_home_entrance_vp);
        gameIndicatorView = (IndicatorView) findViewById(R.id.main_home_entrance_indicator);
    }


    private void initData() {
        gameEntranceList = new ArrayList<>();
        gameEntranceList.add(new GameEntranceItem("1", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("2", R.mipmap.ic_category_1));
        gameEntranceList.add(new GameEntranceItem("3", R.mipmap.ic_category_2));
        gameEntranceList.add(new GameEntranceItem("4", R.mipmap.ic_category_3));
        gameEntranceList.add(new GameEntranceItem("5", R.mipmap.ic_category_4));
        gameEntranceList.add(new GameEntranceItem("6", R.mipmap.ic_category_5));
        gameEntranceList.add(new GameEntranceItem("7", R.mipmap.ic_category_6));
        gameEntranceList.add(new GameEntranceItem("8", R.mipmap.ic_category_7));
        gameEntranceList.add(new GameEntranceItem("9", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("10", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("11", R.mipmap.ic_category_1));
        gameEntranceList.add(new GameEntranceItem("12", R.mipmap.ic_category_1));
        gameEntranceList.add(new GameEntranceItem("1", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("2", R.mipmap.ic_category_1));
        gameEntranceList.add(new GameEntranceItem("3", R.mipmap.ic_category_2));
        gameEntranceList.add(new GameEntranceItem("4", R.mipmap.ic_category_3));
        gameEntranceList.add(new GameEntranceItem("5", R.mipmap.ic_category_4));
        gameEntranceList.add(new GameEntranceItem("6", R.mipmap.ic_category_5));
        gameEntranceList.add(new GameEntranceItem("7", R.mipmap.ic_category_6));
        gameEntranceList.add(new GameEntranceItem("8", R.mipmap.ic_category_7));
        gameEntranceList.add(new GameEntranceItem("9", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("10", R.mipmap.ic_category_0));
        gameEntranceList.add(new GameEntranceItem("11", R.mipmap.ic_category_1));
    }
    int mPageCount = 0;
    private void init() {

        mPageCount = (int) Math.ceil(gameEntranceList.size() * 1.0 / (GAME_COUNT_ONE_PAGE - 1)) ;
        if (mPageCount == 0) {
            mPageCount = 1;
        }

        LayoutInflater inflater = LayoutInflater.from(this);
        List<RecyclerView> viewList = new ArrayList<RecyclerView>();
        for (int index = 0; index < mPageCount; index++) {
            RecyclerView recyclerView = (RecyclerView) inflater.inflate(R.layout.item_home_entrance_vp,
                    gameListViewPager, false);
            recyclerView.setLayoutParams(mLayoutParams);
            recyclerView.setLayoutManager(new GridLayoutManager(this, GAME_PAGE_COUNT));
            recyclerView.addItemDecoration(new GameItemDecoration(LEFT_RIGHT_MARGIN, TOP_BOTTOM_MARGIN));
            GameRecycleViewAdapter entranceAdapter = new GameRecycleViewAdapter(this, gameEntranceList,
                    index, GAME_COUNT_ONE_PAGE);
            recyclerView.setAdapter(entranceAdapter);
            viewList.add(recyclerView);
        }
        gameListViewPager.setAdapter(new GameListPagerAdapter(viewList));
        gameListViewPager.setLayoutParams(mLayoutParams);
        gameListViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

            int lastItem;
            int currentPosition;

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                lastItem = currentPosition;
                currentPosition = position;
                gameIndicatorView.setCurrentIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

//                android.util.Log.i("gaowei", "state = " + state);
//                if (state != ViewPager.SCROLL_STATE_IDLE) return;
//
//                android.util.Log.i("gaowei", "currentPosition = " + currentPosition);
//                if (currentPosition == 0) {
//                    gameListViewPager.setCurrentItem(mPageCount - 1, false);
//
//                } else if (currentPosition == mPageCount - 1) {
//                    gameListViewPager.setCurrentItem(0, false);
//                }
            }
        });
        gameIndicatorView.setIndicatorCount(gameListViewPager.getAdapter().getCount());
        gameIndicatorView.setCurrentIndicator(gameListViewPager.getCurrentItem());
    }
}