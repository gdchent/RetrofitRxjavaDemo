package com.example.huhai.retrofitrxjavademo.login.ui;

import android.content.Intent;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.huhai.retrofitrxjavademo.BuildConfig;
import com.example.huhai.retrofitrxjavademo.R;
import com.example.huhai.retrofitrxjavademo.base.BaseActivity;
import com.example.huhai.retrofitrxjavademo.login.contract.LoginContract;
import com.example.huhai.retrofitrxjavademo.login.presenter.LoginPresenter;
import com.example.huhai.retrofitrxjavademo.utils.SpUtils;

public class LoginActivity
        extends BaseActivity<LoginPresenter> implements LoginContract.View {

    private static final String TAG = "LoginActivity";
    private TextView mTextView;
    private TextView mTextLoginStutes;
    private TextView mBtnLogin;
    private TextView mTvDialogStatus;
    private TextView mIsDebug;
    private View mBtnblog;
    private View btnStart;
    private View btnStop;
    private View btnSave;
    private EditText ettime;
    private boolean isStart = false;

    @Override
    protected void init() {
        mTextView = findViewById(R.id.tv_text);
        mTextLoginStutes = findViewById(R.id.tv_login_status);
        mBtnLogin = findViewById(R.id.btn_login);
        mTvDialogStatus = findViewById(R.id.tv_dialog_status);
        mIsDebug = findViewById(R.id.isdebug);
        mBtnblog = findViewById(R.id.btn_web);
        btnStart = findViewById(R.id.btn_start);
        btnStop = findViewById(R.id.btn_stop);
        btnSave = findViewById(R.id.btn_save);
        ettime = findViewById(R.id.et_time);
        initview();
        initlistener();
    }

    private void initview() {
        int time = SpUtils.getInt(LoginActivity.this, "scanTime", 5);
        ettime.setText(time + "");
    }

    private void initlistener() {

        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //在初始化的时候可以请求网络进行登陆
                //设定两个假的号码，
                String number = "13510680920";
                String password = "123456";
                mPresenter.login(number, password);
            }
        });

        if (BuildConfig.IS_DEBUG) {
            mIsDebug.setText("测试环境" + BuildConfig.URL);

        } else {
            mIsDebug.setText("正式环境" + BuildConfig.URL);
        }
        mBtnblog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, BolgUrlEditActivity.class);
                startActivity(intent);
            }
        });
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.start();
                isStart = true;
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.cancel();
                isStart = false;
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ettime.getText().toString())) {
                    Toast.makeText(LoginActivity.this, "不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    SpUtils.getInt(LoginActivity.this, "scanTime", Integer.parseInt(ettime.getText().toString()));
                    Toast.makeText(LoginActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected int bindLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected LoginPresenter createPresenter() {
        return new LoginPresenter(this);
    }


    //------------------以下全是跟界面相关的处理逻辑----------------------------------

    @Override
    public void showToast(String msg) {
        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginStatus(String message) {
        mTextLoginStutes.setText(message);
    }

    @Override
    public void showData(String message) {
        mTextView.setText(message);
    }


    @Override
    public void showDialog() {
        mTvDialogStatus.setText("正在显示dialoig");
    }

    @Override
    public void dismissDialog() {
        mTvDialogStatus.setText("dialog消失");
    }

    @Override
    public void toMainActivity() {
        Toast.makeText(LoginActivity.this, "跳转主界面", Toast.LENGTH_SHORT).show();
    }

    //------------------以上全是跟界面相关的处理逻辑----------------------------------

    CountDownTimer timer = new CountDownTimer(2000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            int remainTime = (int) (millisUntilFinished / 1000L);
            Log.d("LoginActivity", "======remainTime=====" + remainTime);
        }

        @Override
        public void onFinish() {
            Intent intent = new Intent(LoginActivity.this, H5activity.class);
            startActivity(intent);
            timer.cancel();
        }
    };


    @Override
    protected void onResume() {
        super.onResume();
        if (isStart) {
            timer.start();
        }

    }
}
