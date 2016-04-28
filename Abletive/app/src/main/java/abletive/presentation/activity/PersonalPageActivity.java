package abletive.presentation.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import org.honorato.multistatetogglebutton.ToggleButton;

import abletive.presentation.fragment.FansFragment;
import abletive.presentation.fragment.FocusFragment;
import abletive.presentation.fragment.ProfileFragment;
import alandelip.abletivedemo.R;

public class PersonalPageActivity extends AppCompatActivity {

    private FragmentManager fragmentManager;
    private ProfileFragment profileFragment;
    private FocusFragment focusFragment;
    private FansFragment fansFragment;
    private ToggleButton toggleButton;
    private Button profileButton, focusButton, fansButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_page);

        fragmentManager = getSupportFragmentManager();
        initViews();
        //默认设置为个人资料碎片
        setTabSelection(1);
    }

    private void initViews() {
        initToolBar();
        initButton();
    }

    private void initButton() {
        toggleButton = (ToggleButton) findViewById(R.id.button_group);
        profileButton = (Button) findViewById(R.id.profile);
        focusButton = (Button) findViewById(R.id.focus);
        fansButton = (Button) findViewById(R.id.fans);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(1);
            }
        });
        focusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(2);
            }
        });
        fansButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setTabSelection(3);
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

    /**
     * 设置tab，1代表个人资料，2代表关注，3代表粉丝
     *
     * @param index
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
                    focusFragment = new FocusFragment();
                    transaction.add(R.id.personal_page_fragment, focusFragment);
                }
                break;
            case 3:
                //TODO 跳转至粉丝碎片
                if (fansFragment != null) {
                    transaction.show(fansFragment);
                } else {
                    fansFragment = new FansFragment();
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

    /**
     * 清除选中状态
     */
    private void clearTab() {

    }
}
