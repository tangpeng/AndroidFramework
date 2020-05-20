package com.onemore.goodproduct.acitivity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.onemore.goodproduct.R;

import butterknife.BindView;
import butterknife.ButterKnife;
/**
 * des: https://www.jianshu.com/p/e095f5cafc7e
 * author:lucas tangpeng
 * date:2020-05-20 14:59
 */
public class WebSocketActivity extends AppCompatActivity {

    @BindView(R.id.btn)
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_socket);
        ButterKnife.bind(this);

        btn.setText("范德萨发了上课的");
    }

}
