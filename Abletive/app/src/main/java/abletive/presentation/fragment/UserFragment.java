package abletive.presentation.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abletive.businesslogic.blutil.UserData;
import abletive.presentation.activity.LogActivity;
import abletive.presentation.activity.MainActivity;
import abletive.vo.UserVO;
import alandelip.abletivedemo.R;
import cn.trinea.android.common.entity.FailedReason;
import cn.trinea.android.common.service.impl.ImageMemoryCache;
import jp.wasabeef.blurry.Blurry;

/**
 * 用户界面碎片
 */
public class UserFragment extends Fragment {

    private View currentView;
    private UserVO userVO;
    private UserData userData;
    private TextView userName;
    private Toolbar toolbar;

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
        userVO = userData.getUserVO();

        if (toolbar == null) {
            initToolBar();
        }
        //如果登录就显示用户信息，否则显示请登录信息
        if (userData.isLogin()) {
            initUserData();
        } else {
            initLoginData();
        }
    }

    /**
     * 初始化标头
     */
    private void initToolBar() {
        toolbar = (Toolbar) currentView.findViewById(R.id.toolbar);
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
                //跳转至登录界面
                LogActivity.newInstance(getContext());
            }
        });
    }

    /**
     * 初始化用户信息显示
     */
    private void initUserData() {
        //设置用户名
        userName = (TextView) currentView.findViewById(R.id.user_nickname);
        userName.setText(userVO.getNickname());

        //设置个人资料和我的收藏按钮
        final Button personalInfoButton = (Button) currentView.findViewById(R.id.personal_info),
                collectionsButton = (Button) currentView.findViewById(R.id.collections);
        personalInfoButton.setVisibility(View.VISIBLE);
        collectionsButton.setVisibility(View.VISIBLE);

        TextView loginInfo = (TextView) currentView.findViewById(R.id.log_info);
        //设置登录提醒不可见
        loginInfo.setVisibility(View.GONE);

        //设置头像和背景
        final ImageView mHeaderBackground = (ImageView) currentView.findViewById(R.id.header_background);
        final ImageView mAvatarView = (ImageView) currentView.findViewById(R.id.user_avatar);
        MainActivity.IMAGE_CACHE.get(userVO.getAvatarUrl(), mAvatarView);
        MainActivity.IMAGE_CACHE.setOnImageCallbackListener(new ImageMemoryCache.OnImageCallbackListener() {
            @Override
            public void onPreGet(String imageUrl, View view) {
            }

            @Override
            public void onGetNotInCache(String imageUrl, View view) {
            }

            @Override
            public void onGetSuccess(String imageUrl, Bitmap loadedImage, View view, boolean isInCache) {
                if (imageUrl.equals(userVO.getAvatarUrl())) {
                    //设置头像和背景
                    mHeaderBackground.setImageBitmap(loadedImage);
                    mHeaderBackground.setAlpha(Float.valueOf("0.8"));
                    Blurry.with(getContext())
                            .radius(20)
                            .sampling(8)
                            .async()
                            .capture(mHeaderBackground)
                            .into(mHeaderBackground);
                    mAvatarView.setImageBitmap(loadedImage);
                    //设置个人资料和收藏按钮配色
                    Palette palette = Palette.from(loadedImage).generate();
                    Palette.Swatch muteDark = palette.getDarkMutedSwatch(),
                            vibrantDark = palette.getDarkVibrantSwatch();
                    if (muteDark != null) {
                        personalInfoButton.setTextColor(muteDark.getTitleTextColor());
                        collectionsButton.setTextColor(muteDark.getTitleTextColor());
                    } else if (vibrantDark != null) {
                        personalInfoButton.setTextColor(vibrantDark.getTitleTextColor());
                        collectionsButton.setTextColor(vibrantDark.getTitleTextColor());
                    }
                }
            }

            @Override
            public void onGetFailed(String imageUrl, Bitmap loadedImage, View view, FailedReason failedReason) {
            }
        });

        //设置列表
        ImageView mPersonalPage = (ImageView) currentView.findViewById(R.id.personal_page),
                mMyMember = (ImageView) currentView.findViewById(R.id.my_member),
                mScanMatrix = (ImageView) currentView.findViewById(R.id.scan_matrix),
                mMyMatrix = (ImageView) currentView.findViewById(R.id.my_matrix),
                arrow1 = (ImageView) currentView.findViewById(R.id.arrow1),
                arrow2 = (ImageView) currentView.findViewById(R.id.arrow2),
                arrow3 = (ImageView) currentView.findViewById(R.id.arrow3),
                arrow4 = (ImageView) currentView.findViewById(R.id.arrow4);
        TextView mPersonalPageText = (TextView) currentView.findViewById(R.id.personal_page_text),
                mMyMemberText = (TextView) currentView.findViewById(R.id.my_member_text),
                mMyMatrixText = (TextView) currentView.findViewById(R.id.my_matrix_text),
                mScanMatrixText = (TextView) currentView.findViewById(R.id.scan_matrix_text);
        mPersonalPage.setVisibility(View.VISIBLE);
        mMyMatrix.setVisibility(View.VISIBLE);
        mScanMatrix.setVisibility(View.VISIBLE);
        mMyMember.setVisibility(View.VISIBLE);
        arrow1.setVisibility(View.VISIBLE);
        arrow2.setVisibility(View.VISIBLE);
        arrow3.setVisibility(View.VISIBLE);
        arrow4.setVisibility(View.VISIBLE);
        mPersonalPageText.setVisibility(View.VISIBLE);
        mMyMemberText.setVisibility(View.VISIBLE);
        mMyMatrixText.setVisibility(View.VISIBLE);
        mScanMatrixText.setVisibility(View.VISIBLE);
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
            Toast.makeText(getContext(), getString(R.string.sign), Toast.LENGTH_SHORT).show();
        } else if (id == R.id.action_settings) {
            //TODO 设置
            Toast.makeText(getContext(), getString(R.string.action_settings), Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
