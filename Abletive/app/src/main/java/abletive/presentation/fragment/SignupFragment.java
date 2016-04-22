package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import abletive.presentation.tasks.SignupTask;
import abletive.presentation.uiutil.RegexTool;
import abletive.vo.SignupVO;
import alandelip.abletivedemo.R;

/**
 * 注册界面碎片
 */
public class SignupFragment extends Fragment {

    private View currentView;
    private SignupCallBack signupCallBack;
    private EditText mUsernameEdit, mPasswordEdit, mDisplaynameEdit, mEmailEdit, mPasswordAgainEdit;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_signup, container, false);
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
                //关闭注册界面
                signupCallBack.closeSignup();
            }
        });

        final Button signUpButton = (Button) currentView.findViewById(R.id.sign_up);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //用户注册
                String username = "", displayName = "", password = "", passwordAgain = "", email = "";
                username = mUsernameEdit.getText().toString();
                displayName = mDisplaynameEdit.getText().toString();
                password = mPasswordEdit.getText().toString();
                passwordAgain = mPasswordAgainEdit.getText().toString();
                email = mEmailEdit.getText().toString();

                if (username.length() < 4) {
                    Toast.makeText(getContext(), getContext().getString(R.string.username_short), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (RegexTool.containCharacters(username)) {
                    Toast.makeText(getContext(), getContext().getString(R.string.username_character), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(getContext(), getContext().getString(R.string.password_short), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (RegexTool.containCharacters(password)) {
                    Toast.makeText(getContext(), getContext().getString(R.string.password_character), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.compareTo(passwordAgain) != 0) {
                    Toast.makeText(getContext(), getContext().getString(R.string.password_dismatch), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.contains("@")) {
                    Toast.makeText(getContext(), getContext().getString(R.string.email_invalid), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (displayName.length() == 0) {
                    Toast.makeText(getContext(), getContext().getString(R.string.displayname_null), Toast.LENGTH_SHORT).show();
                    return;
                }
                SignupVO signupVO = new SignupVO(username, password, email, displayName);
                SignupTask signupTask = new SignupTask(getContext(), signupVO);
                signupTask.setSignupCallBack(new SignupTask.SignupCallBack() {
                    @Override
                    public void signup() {
                        signupCallBack.signUp();
                    }
                });
                signupTask.execute();
            }
        });

    }

    private void initEditView() {
        mUsernameEdit = (EditText) currentView.findViewById(R.id.username);
        mDisplaynameEdit = (EditText) currentView.findViewById(R.id.display_name);
        mPasswordEdit = (EditText) currentView.findViewById(R.id.password);
        mPasswordAgainEdit = (EditText) currentView.findViewById(R.id.password_again);
        mEmailEdit = (EditText) currentView.findViewById(R.id.email);
    }

    public void setSignupCallBack(SignupCallBack signupCallBack) {
        this.signupCallBack = signupCallBack;
    }

    public interface SignupCallBack {
        /**
         * 返回登录界面
         */
        void closeSignup();

        /**
         * 处理注册后续
         */
        void signUp();
    }

}
