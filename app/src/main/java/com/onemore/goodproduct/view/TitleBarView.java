package com.onemore.goodproduct.view;

import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.onemore.goodproduct.R;
import com.onemore.goodproduct.util.CommonUtil;


public class TitleBarView extends RelativeLayout {

    private static final String TAG = "TitleBarView";
    private Context mContext;
    private RelativeLayout rlTitleTop;

    private ImageView btnLeft;

    private ImageView btnRight;
    private ImageView btnRight2;
    private TextView tv_center;

    private LinearLayout mLayoutLeft;
    private LinearLayout mLayoutRight;
    private LinearLayout mBtnSearch;
    private ImageView iv_title_left;

    private TextView tvRight;


    private LinearLayout mLayoutSearch;

    private EditText et_search;

    private ImageView mImgDelete;

    public TitleBarView(Context context) {
        super(context);
        mContext = context;
        initView();
    }

    public TitleBarView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initView();
    }

    private void initView() {
        LayoutInflater.from(mContext).inflate(R.layout.title_layout, this);
        rlTitleTop = findViewById(R.id.rl_title_top);
        btnLeft = findViewById(R.id.iv_title_left_back);
        btnRight = findViewById(R.id.iv_title_right_img);
        btnRight2 = findViewById(R.id.iv_title_right_img2);
        tv_center = findViewById(R.id.title_name_text);
        mLayoutLeft = findViewById(R.id.ll_title_left_back);
        mLayoutRight = findViewById(R.id.ll_title_right_img);
        mBtnSearch = findViewById(R.id.title_btn_search);
        iv_title_left = findViewById(R.id.iv_title_left);

        tvRight = findViewById(R.id.tv_title_right_text);
        mLayoutSearch = findViewById(R.id.title_layout_et_search);
        et_search = findViewById(R.id.title_et_search);
        mImgDelete = findViewById(R.id.title_img_delete);
        setBtnLeftOnclickListener();
        setSearchListener();
    }

    public void setBtnRight(int icon) {
        btnRight.setVisibility(VISIBLE);
        btnRight.setImageResource(icon);
    }

    public void setBtnRight2(int icon) {
        btnRight2.setVisibility(VISIBLE);
        btnRight2.setImageResource(icon);
    }

    public void setBtnLeft(int icon) {
        btnLeft.setVisibility(View.GONE);
        iv_title_left.setVisibility(View.VISIBLE);
        iv_title_left.setImageResource(icon);
    }

    public void setBtnRightShow(boolean show) {
        if (show) {
            btnRight.setVisibility(View.VISIBLE);
        } else {
            btnRight.setVisibility(View.GONE);
        }
    }

    public void setLayoutLeftShow(int visibility) {
        mLayoutLeft.setVisibility(visibility);
    }

    public void setLayoutLeftVisibility(int visibility) {
        mLayoutLeft.setVisibility(visibility);
    }

    public int getLayoutLeftVisibility() {
        return mLayoutLeft.getVisibility();
    }

    public void setLayoutRightVisibility(int visibility) {
        mLayoutRight.setVisibility(visibility);
    }

    public int getLayoutRightVisibility() {
        return mLayoutRight.getVisibility();
    }

    public void setCenterTxtShow(int visibility) {
        tv_center.setVisibility(visibility);
    }

    public void setCenterBtnShow(int visibility) {
        mBtnSearch.setVisibility(visibility);
    }

    public  RelativeLayout getTitleTop(){
        return rlTitleTop;
    }

    public void setTitleText(int txtRes) {
        tv_center.setText(getResources().getString(txtRes));
    }

    public void setTitleText(String txtRes) {
        tv_center.setText(txtRes);
    }

    public String getTitleText() {
        return tv_center.getText().toString().trim();
    }

    public TextView getTitleTextView() {
        return tv_center;
    }

    public void setRightText(String txtRes) {
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(txtRes);
    }

    public TextView getBtnRightText() {
        return tvRight;
    }

    public String getRightText() {
        return (String) tvRight.getText();
    }

    public void setTitleText(String txtRes, int color) {
        tv_center.setText(txtRes);
        tv_center.setTextColor(mContext.getResources().getColor(color));
    }

    public void setRightTextOnclickListener(OnClickListener listener) {
        tvRight.setOnClickListener(listener);
    }


    public void setBtnRightOnclickListener(OnClickListener listener) {
        btnRight.setOnClickListener(listener);
    }

    public void setBtnRightOnclickListener2(OnClickListener listener) {
        btnRight2.setOnClickListener(listener);
    }

    // 设置标题左侧打开侧滑菜单的按钮监听
    public void setBtnLeftListener(OnClickListener listener) {
        //mLayoutLeft.setVisibility(View.VISIBLE);
        mLayoutLeft.setOnClickListener(listener);
    }

    public void setBtnCenterSearchOnclickListener(OnClickListener listener) {
        tv_center.setVisibility(View.GONE);
        mBtnSearch.setVisibility(View.VISIBLE);
        mBtnSearch.setOnClickListener(listener);
    }

    public void hideBtnCenterSearch() {
        mBtnSearch.setVisibility(View.GONE);
        tv_center.setVisibility(View.VISIBLE);
    }

    public void hideBtnRight() {
        mLayoutRight.setVisibility(View.INVISIBLE);
    }

    public ImageView getBtnRight() {
        if (btnRight != null) {
            return btnRight;
        }
        return null;
    }

    private void setBtnLeftOnclickListener() {
        mLayoutLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Activity) mContext).finish();
                CommonUtil.hideSoftInput((Activity) mContext, et_search);
            }
        });
    }

    public void setBtnLeftOnclickNull() {
        mLayoutLeft.setVisibility(View.GONE);
    }

    public void destoryView() {
        tv_center.setText(null);
    }

    public void setBtnLeftImg(int resId) {
        btnLeft.setImageResource(resId);
    }

    public void setBtnRightText(String txt) {
        btnRight.setVisibility(View.GONE);
        btnRight2.setVisibility(GONE);
        tvRight.setVisibility(View.VISIBLE);
        tvRight.setText(txt);
    }

    public void setBtnRightTextOnclickListener(OnClickListener onclickListener) {
        tvRight.setOnClickListener(onclickListener);
    }

    public void showSearch() {
        tv_center.setVisibility(View.GONE);
        mLayoutSearch.setVisibility(View.VISIBLE);
    }

    public EditText getEt_search() {
        return et_search;
    }

    private void setSearchListener() {
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    mImgDelete.setVisibility(View.VISIBLE);
                } else {
                    mImgDelete.setVisibility(View.GONE);
                }
            }
        });
        mImgDelete.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.setText(null);
            }
        });
    }
}
