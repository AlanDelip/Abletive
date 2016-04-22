package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import abletive.presentation.tasks.LoginTask;
import alandelip.abletivedemo.R;

/**
 * 登录界面碎片
 */
public class LoginFragment extends Fragment {

    private View currentView;
    private LoginCallback loginCallback;
    private EditText mUsernameEdit, mPasswordEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentView = getView();
        initButton();
        initEditView();

    }

    private void initButton() {
        ImageView mCloseButton = (ImageView) currentView.findViewById(R.id.close);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭activity
                loginCallback.closeLogin();
            }
        });

        Button mLoginButton = (Button) currentView.findViewById(R.id.login);
        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //登录+后续处理
                LoginTask loginTask = new LoginTask(getContext(), mUsernameEdit.getText().toString(),
                        mPasswordEdit.getText().toString());
                loginTask.setLoginCallBack(new LoginTask.LoginCallBack() {
                    @Override
                    public void login() {
                        loginCallback.closeLogin();
                    }
                });
                loginTask.execute();
            }
        });

        TextView mSignText = (TextView) currentView.findViewById(R.id.sign_up);
        mSignText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转至注册界面
                loginCallback.signUp();
            }
        });

        TextView mForgetText = (TextView) currentView.findViewById(R.id.forget);
        mForgetText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 处理忘记密码事件
                loginCallback.handleForget();
            }
        });

    }

    private void initEditView() {
        mUsernameEdit = (EditText) currentView.findViewById(R.id.username);
        mPasswordEdit = (EditText) currentView.findViewById(R.id.password);
    }

    public void setLoginCallBack(LoginCallback loginCallBack) {
        this.loginCallback = loginCallBack;
    }

    public interface LoginCallback {
        /**
         * 关闭登录Activity
         */
        void closeLogin();

        /**
         * 跳转至注册界面
         */
        void signUp();

        /**
         * 跳转至忘记密码网页
         */
        void handleForget();
    }
}
