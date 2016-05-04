package abletive.presentation.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import abletive.businesslogic.blutil.UserData;
import abletive.businesslogic.postbl.AuthorListImpl;
import abletive.presentation.activity.SearchActivity;
import abletive.vo.PersonalPageVO;
import alandelip.abletivedemo.R;

/**
 * 个人资料界面碎片
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String TAG = "Abletive";
    private static final String ARG_BITMAP = "image";
    private Bitmap loadedImage;
    private View currentView;
    private PersonalPageVO personalPageVO;
    private Palette.Swatch swatch;

    public ProfileFragment() {
        // Required empty public constructor
    }

    public static ProfileFragment newInstance(Bitmap loadedImage) {
        ProfileFragment profileFragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_BITMAP, loadedImage);
        profileFragment.setArguments(args);
        return profileFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            loadedImage = getArguments().getParcelable(ARG_BITMAP);
            if (loadedImage != null) {
                Palette palette = Palette.from(loadedImage).generate();
                if (palette.getDarkVibrantSwatch() != null) {
                    Log.d(TAG, "onCreate: vibrant swatch");
                    swatch = palette.getDarkVibrantSwatch();
                } else if (palette.getMutedSwatch() != null) {
                    Log.d(TAG, "onCreate: mute swatch");
                    swatch = palette.getMutedSwatch();
                } else {
                    Log.d(TAG, "onCreate: default swatch");
                    swatch = new Palette.Swatch(Color.DKGRAY, 1);
                }
            } else {
                Log.d(TAG, "onCreate: default swatch");
                swatch = new Palette.Swatch(Color.DKGRAY, 1);
            }
        }
        personalPageVO = UserData.getInstance().getPersonalPageVO();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();

        currentView = getView();
        initItems();
    }

    /**
     * 初始化选项内容
     */
    private void initItems() {

        TextView personalSite = (TextView) currentView.findViewById(R.id.personal_web);
        personalSite.setTextColor(swatch.getBodyTextColor());

        TextView basicInfo = (TextView) currentView.findViewById(R.id.basic_info);
        basicInfo.setTextColor(swatch.getBodyTextColor());

        TextView communityIDText = (TextView) currentView.findViewById(R.id.community_text);
        communityIDText.setTextColor(swatch.getBodyTextColor());

        TextView posts = (TextView) currentView.findViewById(R.id.post_text);
        posts.setTextColor(swatch.getBodyTextColor());
        posts.setOnClickListener(this);

        TextView postComments = (TextView) currentView.findViewById(R.id.post_comment_text);
        postComments.setTextColor(swatch.getBodyTextColor());
        postComments.setOnClickListener(this);

        TextView communityCredits = (TextView) currentView.findViewById(R.id.credit_text);
        communityCredits.setTextColor(swatch.getBodyTextColor());
        communityCredits.setOnClickListener(this);

        TextView postCollections = (TextView) currentView.findViewById(R.id.collection_text);
        postCollections.setTextColor(swatch.getBodyTextColor());
        postCollections.setOnClickListener(this);

        TextView dueTime = (TextView) currentView.findViewById(R.id.due_time_text);
        dueTime.setTextColor(swatch.getBodyTextColor());
        dueTime.setOnClickListener(this);

        TextView communityPersonalPage = (TextView) currentView.findViewById(R.id.community_page_text);
        communityPersonalPage.setTextColor(swatch.getBodyTextColor());
        communityPersonalPage.setOnClickListener(this);

        TextView shopOrders = (TextView) currentView.findViewById(R.id.shop_text);
        shopOrders.setTextColor(swatch.getBodyTextColor());
        shopOrders.setOnClickListener(this);

        TextView mPostView = (TextView) currentView.findViewById(R.id.post);
        mPostView.setTextColor(swatch.getBodyTextColor());
        mPostView.setText(personalPageVO.getPostCount());

        TextView mPostCommentsView = (TextView) currentView.findViewById(R.id.post_comment);
        mPostCommentsView.setTextColor(swatch.getBodyTextColor());
        mPostCommentsView.setText(personalPageVO.getPostCount());

        TextView mCommunityCreditsView = (TextView) currentView.findViewById(R.id.credit);
        mCommunityCreditsView.setTextColor(swatch.getBodyTextColor());
        mCommunityCreditsView.setText(personalPageVO.getCredits());

        TextView mPostCollectionsView = (TextView) currentView.findViewById(R.id.collection);
        mPostCollectionsView.setTextColor(swatch.getBodyTextColor());
        mPostCollectionsView.setText(personalPageVO.getCollectionCount());

        TextView mMemberTextView = (TextView) currentView.findViewById(R.id.member_info_text);
        mMemberTextView.setTextColor(swatch.getBodyTextColor());

        TextView mMemberStateView = (TextView) currentView.findViewById(R.id.member_state_text);
        mMemberStateView.setTextColor(swatch.getBodyTextColor());
        mMemberStateView.setText(personalPageVO.getMemberVO().getUserType());

        TextView mDueTimeView = (TextView) currentView.findViewById(R.id.due_time);
        mDueTimeView.setTextColor(swatch.getBodyTextColor());
        mDueTimeView.setText(personalPageVO.getMemberVO().getEndTime());

        TextView mCommunityIDView = (TextView) currentView.findViewById(R.id.community);
        mCommunityIDView.setTextColor(swatch.getBodyTextColor());
        mCommunityIDView.setText(personalPageVO.getUserInfoPO().getId() + "");

        TextView mCommunityPageView = (TextView) currentView.findViewById(R.id.community_page);
        mCommunityPageView.setTextColor(swatch.getBodyTextColor());
        mCommunityPageView.
                setText("http://abletive.com/author/" + personalPageVO.getUserInfoPO().getId());

        TextView mGenderView = (TextView) currentView.findViewById(R.id.gender);
        mGenderView.setTextColor(swatch.getBodyTextColor());
        mGenderView.setText(personalPageVO.getGender());

        TextView mMemberView = (TextView) currentView.findViewById(R.id.member);
        mMemberView.setTextColor(swatch.getBodyTextColor());
        mMemberView.setText(personalPageVO.getRegisterTime());

        TextView mShopOrdersView = (TextView) currentView.findViewById(R.id.shop);
        mShopOrdersView.setTextColor(swatch.getBodyTextColor());
        mShopOrdersView.setText(personalPageVO.getShopOrders());
    }

    @Override
    public void onClick(View v) {
        //监听
        switch (v.getId()) {
            //文章列表
            case R.id.post_text:
                String authorID = personalPageVO.getUserInfoPO().getId();
                String authorName = personalPageVO.getUserInfoPO().getDisplay_name();
                SearchActivity.newInstanceForUserPage(getContext(),
                        authorName, authorID, new AuthorListImpl());
                break;
            //文章评论
            case R.id.post_comment_text:
                break;
            //积分
            case R.id.credit_text:
                break;
            //文章收藏
            case R.id.collection_text:
                break;
            //到期日期
            case R.id.due_time_text:
                break;
            //社区主页
            case R.id.community_page_text:
                break;
            //商店订单
            case R.id.shop_text:
                break;
        }
    }
}
