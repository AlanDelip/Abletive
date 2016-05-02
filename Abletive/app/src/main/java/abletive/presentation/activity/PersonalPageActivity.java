package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
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

import org.honorato.multistatetogglebutton.ToggleButton;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.fragment.FansFragment;
import abletive.presentation.fragment.FocusFragment;
import abletive.presentation.fragment.ProfileFragment;
import abletive.presentation.tasks.PersonalPageTask;
import abletive.vo.PersonalPageVO;
import alandelip.abletivedemo.R;

public class PersonalPageActivity extends AppCompatActivity {

    private static final String ARG_USERID = "userID";
    private String userID;
    private FragmentManager fragmentManager;
    private ProfileFragment profileFragment;
    private FocusFragment focusFragment;
    private FansFragment fansFragment;
    private ToggleButton toggleButton;
    private PersonalPageVO personalPageVO;
    private Palette.Swatch muteLight, vibrantLight;
    private Toolbar toolbar;

    public static void newInstance(Context context, String userID) {
        Intent intent = new Intent(context, PersonalPageActivity.class);
        intent.putExtra(ARG_USERID, userID);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        fragmentManager = getSupportFragmentManager();
        if (getIntent() != null) {
            userID = getIntent().getStringExtra(ARG_USERID);
        }

        //从网络获得个人主页，然后设置个人主页的碎片
        PersonalPageTask personalPageTask = new PersonalPageTask(this, userID);
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

    private void initViews() {
        initToolBar();
        initOthers();
        initButton();
    }

    private void initOthers() {
        TextView userName = (TextView) findViewById(R.id.user_nickname);
        ImageView avatar = (ImageView) findViewById(R.id.user_avatar);
        MainActivity.IMAGE_CACHE.get(personalPageVO.getAvatarUrl(), avatar);
        Bitmap loadedImage = MainActivity.IMAGE_CACHE.get(personalPageVO
                .getAvatarUrl())
                .getData();
        View view = findViewById(R.id.background);
        Palette palette = Palette.from(loadedImage).generate();
        muteLight = palette.getLightMutedSwatch();
        vibrantLight = palette.getLightVibrantSwatch();
        if (muteLight != null) {
            view.setBackgroundColor(muteLight.getRgb());
            userName.setTextColor(muteLight.getTitleTextColor());
            toolbar.setBackgroundColor(muteLight.getRgb());
        } else if (vibrantLight != null) {
            view.setBackgroundColor(vibrantLight.getRgb());
            userName.setTextColor(vibrantLight.getTitleTextColor());
            toolbar.setBackgroundColor(muteLight.getRgb());
        }
    }

    private void initButton() {
        toggleButton = (ToggleButton) findViewById(R.id.button_group);
        toggleButton.setValue(0);
        //根据avatar设置按钮颜色
        if (muteLight != null) {
            toggleButton.setColors(muteLight.getRgb(), muteLight.getTitleTextColor());
        } else if (vibrantLight != null) {
            toggleButton.setColors(vibrantLight.getRgb(), vibrantLight.getTitleTextColor());
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
        clearTab();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragments(transaction);
        switch (index) {
            case 1:
                //TODO 跳转至个人资料碎片
                if (profileFragment != null) {
                    transaction.show(profileFragment);
                } else {
                    profileFragment = new ProfileFragment();
                    transaction.add(R.id.personal_page_fragment, profileFragment);
                }
                break;
            case 2:
                //TODO 跳转至关注碎片
                if (focusFragment != null) {
                    transaction.show(focusFragment);
                } else {
                    focusFragment = FocusFragment.newInstance(userID);
                    transaction.add(R.id.personal_page_fragment, focusFragment);
                }
                break;
            case 3:
                //TODO 跳转至粉丝碎片
                if (fansFragment != null) {
                    transaction.show(fansFragment);
                } else {
                    fansFragment = FansFragment.newInstance(userID);
                    transaction.add(R.id.personal_page_fragment, fansFragment);
                }
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

    /**
     * 清除选中状态
     */
    private void clearTab() {

    }
}
