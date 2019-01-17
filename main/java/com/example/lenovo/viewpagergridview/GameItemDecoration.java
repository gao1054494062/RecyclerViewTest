package com.example.lenovo.viewpagergridview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

class GameItemDecoration extends RecyclerView.ItemDecoration {
    private int leftRight;
    private int topBottom;

    public GameItemDecoration(int leftRight, int topBottom) {
        this.leftRight = leftRight;
        this.topBottom = topBottom;
    }
    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = leftRight;
        outRect.right = leftRight;
        outRect.bottom = topBottom;
        outRect.top = topBottom;
    }
}
