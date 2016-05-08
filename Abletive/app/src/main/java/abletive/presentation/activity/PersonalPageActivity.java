package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.honorato.multistatetogglebutton.MultiStateToggleButton;
import org.honorato.multistatetogglebutton.ToggleButton;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.fragment.FansFragment;
import abletive.presentation.fragment.FocusFragment;
import abletive.presentation.fragment.ProfileFragment;
import abletive.presentation.tasks.FollowTask;
import abletive.presentation.tasks.PersonalPageTask;
import abletive.vo.PersonalPageVO;
import alandelip.abletivedemo.R;
import cn.trinea.android.common.entity.CacheObject;
import jp.wasabeef.blurry.Blurry;

public class PersonalPageActivity extends AppCompatActivity {
    private static final String ARG_USERID = "userID";
    private String userID;
    private FragmentManager fragmentManager;
    private ProfileFragment profileFragment;
    private FocusFragment focusFragment;
    private FansFragment fansFragment;
    private PersonalPageVO personalPageVO;
    private Palette.Swatch muteLight, vibrantLight;
    private Toolbar toolbar;
    private Bitmap loadedImage;

    public static void newInstance(Context context, String userID) {
        Intent intent = new Intent(context, PersonalPageActivity.class);
        intent.putExtra(ARG_USERID, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        //管理碎片
        fragmentManager = getSupportFragmentManager();
        if (getIntent() != null) {
            userID = getIntent().getStringExtra(ARG_USERID);
        }

        //从网络获得个人主页，然后设置个人主页的碎片
        final PersonalPageTask personalPageTask = new PersonalPageTask(this, userID);
        personalPageTask.setPersonPeronalPageCallBack(new PersonalPageTask.PersonalPageCallBack() {
            @Override
            public void initViews() {
                personalPageVO = UserData.getInstance().getPersonalPageVO();
                PersonalPageActivity.this.initViews();
                //默认设置为个人资料碎片
                setTabSelection(1);
            }
        });
        personalPageTask.execute();
    }

    /**
     * 初始化Views
     */
    private void initViews() {
        initToolBar();
        initAvatar();
        initOthers();
        initButton();
    }

    /**
     * 初始化头像和配色
     */
    private void initAvatar() {
        //用户名
        TextView userName = (TextView) findViewById(R.id.user_nickname);
        userName.setText(personalPageVO.getUserInfoPO().getDisplay_name());

        //头像
        ImageView avatar = (ImageView) findViewById(R.id.user_avatar);
        MainActivity.IMAGE_CACHE.get(personalPageVO.getAvatarUrl(), avatar);
        CacheObject<Bitmap> cacheObject = MainActivity.IMAGE_CACHE.get(personalPageVO.getAvatarUrl());
        if (cacheObject != null) {
            loadedImage = cacheObject.getData();
        }

        //设置背景
        ImageView background = (ImageView) findViewById(R.id.background);
        if (loadedImage != null) {
            background.setImageBitmap(loadedImage);
            Blurry.with(this)
                    .radius(20)
                    .sampling(8)
                    .async()
                    .capture(background)
                    .into(background);

            //配色
            Palette palette = Palette.from(loadedImage).generate();
            muteLight = palette.getLightMutedSwatch();
            vibrantLight = palette.getLightVibrantSwatch();
            if (muteLight != null) {
                userName.setTextColor(muteLight.getRgb());
                toolbar.setBackgroundColor(muteLight.getRgb());
            } else if (vibrantLight != null) {
                userName.setTextColor(vibrantLight.getRgb());
                toolbar.setBackgroundColor(vibrantLight.getRgb());
            }
        }
    }

    private void initOthers() {
        final TextView userType = (TextView) findViewById(R.id.user_type);
        if (UserData.getInstance().getUserVO().getId().equals(userID)) {
            userType.setText("自己");
        } else {
            if (personalPageVO.getFollowState().equals("未关注")) {
                userType.setText("+加为好友");
                userType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //添加好友
                        FollowTask followTask = new FollowTask(PersonalPageActivity.this,
                                userID, FollowTask.FollowState.FOLLOW);
                        followTask.setFollowTaskCallBack(new FollowTask.FollowTaskCallBack() {
                            @Override
                            public void afterFollow(String content) {
                                userType.setText(content);
                            }
                        });
                        followTask.execute();
                    }
                });
            } else {
                userType.setText(personalPageVO.getFollowState());
                userType.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //解除好友关系
                        FollowTask followTask = new FollowTask(PersonalPageActivity.this,
                                userID, FollowTask.FollowState.UNFOLLOW);
                        followTask.setFollowTaskCallBack(new FollowTask.FollowTaskCallBack() {
                            @Override
                            public void afterFollow(String content) {
                                userType.setText(content);
                            }
                        });
                        followTask.execute();
                    }
                });
            }
        }
    }

    private void initButton() {
        //设置内容
        MultiStateToggleButton multiStateToggleButton = (MultiStateToggleButton) findViewById(R.id.button_group);
        String[] elements = new String[3];
        elements[0] = "个人资料";
        elements[1] = "关注者" + personalPageVO.getFollowingCount() + "人";
        elements[2] = "粉丝" + personalPageVO.getFollowerCount() + "人";
        multiStateToggleButton.setElements(elements);

        //根据avatar设置按钮颜色
        ToggleButton toggleButton = (ToggleButton) findViewById(R.id.button_group);
        toggleButton.setBackgroundColor(Color.TRANSPARENT);
        toggleButton.setValue(0);
        if (muteLight != null) {
            toggleButton.setColors(muteLight.getRgb(), muteLight.getBodyTextColor());
        } else if (vibrantLight != null) {
            toggleButton.setColors(vibrantLight.getRgb(), vibrantLight.getBodyTextColor());
        }
        toggleButton.refreshDrawableState();

        toggleButton.setOnValueChangedListener(new ToggleButton.OnValueChangedListener() {
            @Override
            public void onValueChanged(int value) {
                setTabSelection(value + 1);
            }
        });

    }

    private void initToolBar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    /**
     * 设置tab
     *
     * @param index 1代表个人资料，2代表关注，3代表粉丝
     */
    private void setTabSelection(int index) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 1:
                //跳转至个人资料碎片
                if (profileFragment != null) {
                    transaction.show(profileFragment);
                } else {
                    profileFragment = ProfileFragment.newInstance(loadedImage);
                    transaction.add(R.id.personal_page_fragment, profileFragment);
                }
                break;
            case 2:
                //跳转至关注碎片
                if (focusFragment != null) {
                    transaction.show(focusFragment);
                } else {
                    focusFragment = FocusFragment.newInstance(userID);
                    transaction.add(R.id.personal_page_fragment, focusFragment);
                }
//                FollowActivity.newInstance(this, personalPageVO.getUserInfoPO().getDisplay_name(),
//                        1, userID);
                break;
            case 3:
                //跳转至粉丝碎片
                if (fansFragment != null) {
                    transaction.show(fansFragment);
                } else {
                    fansFragment = FansFragment.newInstance(userID);
                    transaction.add(R.id.personal_page_fragment, fansFragment);
                }
//                FollowActivity.newInstance(this, personalPageVO.getUserInfoPO().getDisplay_name(),
//                        2, userID);
                break;
            default:
                break;
        }
        transaction.commit();
    }

    private void hideFragments(FragmentTransaction transaction) {
        if (profileFragment != null) {
            transaction.hide(profileFragment);
        }
        if (focusFragment != null) {
            transaction.hide(focusFragment);
        }
        if (fansFragment != null) {
            transaction.hide(fansFragment);
        }
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
