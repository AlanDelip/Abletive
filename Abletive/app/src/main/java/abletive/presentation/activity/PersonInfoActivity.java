package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.widget.RoundImageView;
import abletive.vo.UserVO;
import alandelip.abletivedemo.R;

public class PersonInfoActivity extends AppCompatActivity {

    private UserVO userVO;
    private UserData userData;

    public static void newInstance(Context context) {
        Intent intent = new Intent(context, PersonInfoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);

        //获得目前的用户信息
        userData = UserData.getInstance();
        userVO = userData.getUserVO();
        initViews();
    }

    private void initViews() {
        initToolBar();
        initButton();
        initFields();
    }

    private void initFields() {
        ImageView mAvatarView = (RoundImageView) findViewById(R.id.avatar);
        MainActivity.IMAGE_CACHE.get(userVO.getAvatarUrl(), mAvatarView);

        TextView genderView = (TextView) findViewById(R.id.gender);
        genderView.setText(userVO.getGender());

        TextView nicknameView = (TextView) findViewById(R.id.nickname);
        nicknameView.setText(userVO.getNickname());

        TextView personalPageView = (TextView) findViewById(R.id.personal_page);
        personalPageView.setText(userVO.getUrl());

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(userVO.getDescription());

        TextView weiboView = (TextView) findViewById(R.id.weibo);
        weiboView.setText(userVO.getWeibo());

        TextView qqView = (TextView) findViewById(R.id.qq);
        qqView.setText(userVO.getQq());

        TextView loginNameView = (TextView) findViewById(R.id.login_username);
        loginNameView.setText(userVO.getUsername());

        TextView emailView = (TextView) findViewById(R.id.email);
        emailView.setText(userVO.getEmail());
    }

    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void initButton() {
        TextView mSignOutButton = (TextView) findViewById(R.id.sign_out);
        mSignOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置注销登录状态
                userData.setIsLogin(false);
                finish();
            }
        });

        TextView mSaveButton = (TextView) findViewById(R.id.save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 保存信息
                Toast.makeText(PersonInfoActivity.this, "已保存", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        TextView mModifyPassButton = (TextView) findViewById(R.id.modify_password);
        mModifyPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转至修改密码界面
                Toast.makeText(PersonInfoActivity.this, "修改密码...", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            finish();
        }
        return true;
    }
}
