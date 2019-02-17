package com.lacy.example.keywork.ui.activity;

import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lacy.example.keywork.R;
import com.lacy.example.keywork.common.helper.KeywordHelper;
import com.lacy.example.keywork.core.base.BaseActivity;
import com.lacy.example.keywork.presenter.MainPresenter;
import com.lacy.example.keywork.view.MainView;

import java.util.List;
import java.util.Random;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {
    @BindView(R.id.lnKeyword)
    LinearLayout lnKeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void initPresenter() {
        presenter = new MainPresenter();
    }

    @Override
    protected void initView() {
        presenter.getAllKeywords();
    }

    @Override
    public void getAllKeywordSuccess(List<String> keywords) {
        hideLoadingDialog();
        if (keywords != null && !keywords.isEmpty()) {
            for (String item : keywords) {
                AppCompatTextView childView = new AppCompatTextView(this);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
                params.gravity = Gravity.CENTER;
                int margin = (int) getResources().getDimension(R.dimen.margin);
                int padding = (int) getResources().getDimension(R.dimen.text_padding);
                params.setMargins(margin, margin, margin, margin);
                childView.setTextColor(getResources().getColor(R.color.white, null));
                childView.setTextSize(20);
                childView.setGravity(Gravity.CENTER);
                childView.setPadding(padding, padding, padding, padding);
                childView.setLayoutParams(params);
                childView.setText(KeywordHelper.keywordTokenizer(item));
                // random color
                int[] androidColors = this.getResources().getIntArray(R.array.androidcolors);
                int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
                // create rounded corner
                GradientDrawable shape = new GradientDrawable();
                shape.setCornerRadius(20);
                shape.setColor(randomAndroidColor);
                childView.setBackground(shape);
                lnKeyword.addView(childView);
            }
        }
    }
}
