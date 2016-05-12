package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.presentation.tasks.LoginTask;
import alandelip.abletivedemo.R;

public class LogInActivity extends AppCompatActivity {

    private EditText mUsernameEdit, mPasswordEdit;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LogInActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log);

        initButton();
        initEditView();
    }

    private void initButton() {
        ImageView mCloseButton = (ImageView) findViewById(R.id.close);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭activity
                finish();
            }
        });

        Button mLoginButton = (Button) findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录+后续处理
                LoginTask loginTask = new LoginTask(LogInActivity.this, mUsernameEdit.getText().toString(),
                        mPasswordEdit.getText().toString());
                loginTask.setLoginCallBack(new LoginTask.LoginCallBack() {
                    @Override
                    public void login() {
                        finish();
                    }
                });
                loginTask.execute();
            }
        });

        TextView mSignText = (TextView) findViewById(R.id.sign_up);
        mSignText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至注册界面
                SignupActivity.newInstance(LogInActivity.this);
            }
        });

        TextView mForgetText = (TextView) findViewById(R.id.forget);
        mForgetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 处理忘记密码事件
                Toast.makeText(LogInActivity.this, "暂时还不能修改，请登录Abletive.com进行修改~", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initEditView() {
        mUsernameEdit = (EditText) findViewById(R.id.username);
        mPasswordEdit = (EditText) findViewById(R.id.password);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        if (ClientLogic.getInstance().isSignup()) {
            //设置成默认状态
            ClientLogic.getInstance().setSignup(false);
            //已经成功注册，直接跳回个人界面
            finish();
        }
    }
}
