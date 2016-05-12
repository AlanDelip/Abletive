package abletive.presentation.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.userbl.UserSignImpl;
import abletive.logicservice.userblservice.UserSignService;
import abletive.presentation.uiutil.WidgetTool;
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
        //TODO 上传自定义头像

        //TODO 暂时没有设置个人资料的接口API
        TextView genderView = (TextView) findViewById(R.id.gender);
        genderView.setText(userVO.getGender());
        TextView genderText = (TextView) findViewById(R.id.gender_text);
        final AlertDialog genderDialog = WidgetTool.getChoiceDialog(PersonInfoActivity.this, "性别",
                new String[]{"男", "女", "未知"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            default:
                                break;
                        }
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 点击确定后的动作
                    }
                });
        genderText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                genderDialog.show();
            }
        });

        TextView nicknameView = (TextView) findViewById(R.id.nickname);
        nicknameView.setText(userVO.getNickname());
        final EditText nicknameEdit = new EditText(this);
        final AlertDialog nicknameDialog = WidgetTool.getTextDialog(PersonInfoActivity.this, "昵称",
                nicknameEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 点击确认后的反应
                    }
                });
        nicknameEdit.setText(userVO.getNickname());
        nicknameView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nicknameDialog.show();
            }
        });

        TextView personalPageView = (TextView) findViewById(R.id.personal_page);
        personalPageView.setText(userVO.getUrl());
        final EditText personalPageEdit = new EditText(this);
        final AlertDialog personalPageDialog = WidgetTool.getTextDialog(PersonInfoActivity.this, "个人主页",
                personalPageEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 点击确认后的反应
                    }
                });
        personalPageEdit.setText(userVO.getUrl());
        personalPageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalPageDialog.show();
            }
        });

        TextView descriptionView = (TextView) findViewById(R.id.description);
        descriptionView.setText(userVO.getDescription());
        final EditText descriptionEdit = new EditText(this);
        final AlertDialog descriptionDialog =  WidgetTool.getTextDialog(PersonInfoActivity.this, "个人简介",
                descriptionEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO 点击确认后的保存
                    }
                });
        descriptionEdit.setText(userVO.getDescription());
        descriptionView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descriptionDialog.show();
            }
        });

        TextView weiboView = (TextView) findViewById(R.id.weibo);
        TextView weiboText = (TextView) findViewById(R.id.weibo_text);
        weiboView.setText(userVO.getWeibo());
        final EditText weiboEdit = new EditText(this);
        final AlertDialog weiboDialog = WidgetTool.getTextDialog(PersonInfoActivity.this, "微博",
                weiboEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        weiboEdit.setText(userVO.getWeibo());
        weiboText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                weiboDialog.show();
            }
        });

        TextView qqView = (TextView) findViewById(R.id.qq);
        TextView qqText = (TextView) findViewById(R.id.qq_text);
        qqView.setText(userVO.getQq());
        final EditText qqEdit = new EditText(this);
        final AlertDialog qqDialog = WidgetTool.getTextDialog(PersonInfoActivity.this, "QQ",
                qqEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //TODO
                    }
                });
        qqEdit.setText(userVO.getQq());
        qqText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqDialog.show();
            }
        });

        TextView loginNameView = (TextView) findViewById(R.id.login_username);
        loginNameView.setText(userVO.getUsername());

        TextView emailView = (TextView) findViewById(R.id.email);
        emailView.setText(userVO.getEmail());
        final EditText emailEdit = new EditText(this);
        final AlertDialog emailDialog = WidgetTool.getTextDialog(PersonInfoActivity.this, "电子邮件",
                emailEdit, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
        emailEdit.setText(userVO.getEmail());
        emailView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailDialog.show();
            }
        });
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
                UserSignService userBl = new UserSignImpl();
                userBl.signout(PersonInfoActivity.this);
                finish();
            }
        });

        TextView mSaveButton = (TextView) findViewById(R.id.save);
        mSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 保存信息
                Toast.makeText(PersonInfoActivity.this, "此版本暂不支持修改~", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        TextView mModifyPassButton = (TextView) findViewById(R.id.modify_password);
        mModifyPassButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转至修改密码界面
                Toast.makeText(PersonInfoActivity.this, "暂时不能修改诶~", Toast.LENGTH_SHORT).show();
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
