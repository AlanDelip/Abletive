package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import alandelip.abletivedemo.R;

/**
 * 用户界面碎片
 */
public class UserFragment extends Fragment {

    private View currentView;
    private UserData userData;
    private TextView userName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentView = getView();
        userData = UserData.getInstance();

        initToolBar();
        //如果登录就显示用户信息，否则显示请登录信息
        if (userData.isLogin()) {
            initUserData();
        } else {
            initLoginData();
        }

//        initViewPager();
    }

    /**
     * 初始化标头
     */
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        setHasOptionsMenu(true);
    }

    /**
     * 初始化用户登录信息显示
     */
    private void initLoginData() {
        userName = (TextView) currentView.findViewById(R.id.user_nickname);
        //设置显示用户名为“未登录”
        userName.setText(R.string.no_login);

        TextView loginInfo = (TextView) currentView.findViewById(R.id.log_info);
        //设置登录提醒可见
        loginInfo.setVisibility(View.VISIBLE);
        loginInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO 跳转至登录界面
            }
        });
    }

    /**
     * 初始化用户信息显示
     */
    private void initUserData() {

    }

    private void initViewPager() {
//        final MaterialViewPager mViewPager = (MaterialViewPager) getView().findViewById(R.id.materialViewPager);

//        Toolbar toolbar = mViewPager.getToolbar();
//        if (toolbar != null) {
//            ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
//            toolbar.setTitle(null);
//            ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
//            if (actionBar != null) {
//                actionBar.setDisplayHomeAsUpEnabled(false);
//                actionBar.setDisplayShowHomeEnabled(false);
//                actionBar.setDisplayShowTitleEnabled(true);
//                actionBar.setDisplayUseLogoEnabled(false);
//            }
//        }
//
//        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getActivity().getSupportFragmentManager()) {

//            @Override
//            public Fragment getItem(int position) {
//                return RecyclerViewFragment.newInstance();
//            }
//
//            @Override
//            public int getCount() {
//                return 4;
//            }
//
//            @Override
//            public CharSequence getPageTitle(int position) {
//                switch (position % 4) {
//                    case 0:
//                        return "个人档案";
//                    case 1:
//                        return "社区贡献";
//                    case 2:
//                        return "个人作品";
//                    case 3:
//                        return "关注者";
//                }
//                return "";
//            }
//        });
//
//        ImageView userLogo = (ImageView) mViewPager.getChildAt(0).findViewById(R.id.user_logo);
//        userLogo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //TODO: 实现头像和背景的自定义更改
//                Toast.makeText(getContext(), "it work!!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);
//        mViewPager.getPagerTitleStrip().setViewPager(mViewPager.getViewPager());
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_user, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sign_up) {
            //TODO 签到
            Toast.makeText(getContext(), getString(R.string.sign_up), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_settings) {
            //TODO 设置
            Toast.makeText(getContext(), getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
