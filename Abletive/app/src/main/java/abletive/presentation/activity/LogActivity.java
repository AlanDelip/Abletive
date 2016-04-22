package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.widget.Toast;

import abletive.presentation.fragment.LoginFragment;
import abletive.presentation.fragment.SignupFragment;
import alandelip.abletivedemo.R;

public class LogActivity extends AppCompatActivity {

    /**
     * 登录界面碎片
     */
    private LoginFragment loginFragment;
    /**
     * 注册界面碎片
     */
    private SignupFragment signupFragment;
    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, LogActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_log);

        fragmentManager = getSupportFragmentManager();
        //默认显示登录界面
        showLoginFragment();
    }

    /**
     * 显示登录界面
     */
    private void showLoginFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideSignupFrament(transaction);
        if (loginFragment != null) {
            transaction.show(loginFragment);
        } else {
            loginFragment = new LoginFragment();
            loginFragment.setLoginCallBack(new LoginFragment.LoginCallback() {
                @Override
                public void closeLogin() {
                    finish();
                }

                @Override
                public void signUp() {
                    //显示注册界面
                    showSignupFragment();
                }

                @Override
                public void handleForget() {
                    //TODO
                    Toast.makeText(LogActivity.this, "忘记密码", Toast.LENGTH_SHORT).show();
                }
            });
            transaction.add(R.id.content, loginFragment);
        }
        transaction.commit();

    }

    /**
     * 隐藏登录界面
     */
    private void hideLoginFragment(FragmentTransaction transaction) {
        if (loginFragment != null) {
            transaction.hide(loginFragment);
        }
    }

    /**
     * 显示注册界面
     */
    private void showSignupFragment() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideLoginFragment(transaction);
        if (signupFragment != null) {
//            transaction.setCustomAnimations(R.anim.popup_enter,R.anim.popup_exit);
//            transaction.replace(R.id.content,signupFragment);
            transaction.show(signupFragment);
        } else {
            signupFragment = new SignupFragment();
            signupFragment.setSignupCallBack(new SignupFragment.SignupCallBack() {
                @Override
                public void closeSignup() {
                    showLoginFragment();
                }

                @Override
                public void signUp() {
                    //注册完成后续
                    finish();
                }
            });
            transaction.add(R.id.content, signupFragment);
        }
        transaction.commit();
    }

    /**
     * 隐藏注册界面
     */
    private void hideSignupFrament(FragmentTransaction transaction) {
        if (signupFragment != null) {
            transaction.hide(signupFragment);
        }
    }

}
