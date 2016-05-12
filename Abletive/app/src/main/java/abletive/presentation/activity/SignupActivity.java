package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import abletive.businesslogic.blutil.ClientLogic;
import abletive.presentation.tasks.SignupTask;
import abletive.presentation.uiutil.RegexTool;
import abletive.vo.SignupVO;
import alandelip.abletivedemo.R;

public class SignupActivity extends AppCompatActivity {

    private EditText mUsernameEdit, mPasswordEdit, mDisplaynameEdit, mEmailEdit, mPasswordAgainEdit;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, SignupActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initViews();
    }

    private void initViews() {
        initButton();
        initEditView();
    }

    private void initButton() {
        ImageView mCloseButton = (ImageView) findViewById(R.id.close);
        mCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //关闭注册界面
                finish();
            }
        });

        final Button signUpButton = (Button) findViewById(R.id.sign_up);
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
                    Toast.makeText(SignupActivity.this, getString(R.string.username_short), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (RegexTool.containCharacters(username)) {
                    Toast.makeText(SignupActivity.this, getString(R.string.username_character), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() < 6) {
                    Toast.makeText(SignupActivity.this, getString(R.string.password_short), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (RegexTool.containCharacters(password)) {
                    Toast.makeText(SignupActivity.this, getString(R.string.password_character), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.compareTo(passwordAgain) != 0) {
                    Toast.makeText(SignupActivity.this, getString(R.string.password_dismatch), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!email.contains("@")) {
                    Toast.makeText(SignupActivity.this, getString(R.string.email_invalid), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (displayName.length() == 0) {
                    Toast.makeText(SignupActivity.this, getString(R.string.displayname_null), Toast.LENGTH_SHORT).show();
                    return;
                }
                SignupVO signupVO = new SignupVO(username, password, email, displayName);
                SignupTask signupTask = new SignupTask(SignupActivity.this, signupVO);
                signupTask.setSignupCallBack(new SignupTask.SignupCallBack() {
                    @Override
                    public void signup() {
                        ClientLogic.getInstance().setSignup(true);
                        Toast.makeText(SignupActivity.this, "注册成功！", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
                signupTask.execute();
            }
        });
    }

    private void initEditView() {
        mUsernameEdit = (EditText) findViewById(R.id.username);
        mDisplaynameEdit = (EditText) findViewById(R.id.display_name);
        mPasswordEdit = (EditText) findViewById(R.id.password);
        mPasswordAgainEdit = (EditText) findViewById(R.id.password_again);
        mEmailEdit = (EditText) findViewById(R.id.email);
    }

}
