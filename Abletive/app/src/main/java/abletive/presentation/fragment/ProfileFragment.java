package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import abletive.businesslogic.blutil.UserData;
import abletive.vo.PersonalPageVO;
import alandelip.abletivedemo.R;

/**
 * 个人资料界面碎片
 */
public class ProfileFragment extends Fragment implements View.OnClickListener {
    private static final String ARG_USERID = "userID";

    private String userID;
    private View currentView;
    private TextView mPostView, mPostCommentsView, mCommunityCreditsView,
            mPostCollectionsView, mDueTimeView, mCommunityIDView,
            mCommunityPageView, mGenderView, mMemberView,
            mShopOrdersView, mMemberStateView;
    private PersonalPageVO personalPageVO;


    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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

        TextView posts = (TextView) currentView.findViewById(R.id.post_text);
        posts.setOnClickListener(this);

        TextView postComments = (TextView) currentView.findViewById(R.id.post_comment_text);
        postComments.setOnClickListener(this);

        TextView communityCredits = (TextView) currentView.findViewById(R.id.credit_text);
        communityCredits.setOnClickListener(this);

        TextView postCollections = (TextView) currentView.findViewById(R.id.collection_text);
        postCollections.setOnClickListener(this);

        TextView dueTime = (TextView) currentView.findViewById(R.id.due_time_text);
        dueTime.setOnClickListener(this);

        TextView communityPersonalPage = (TextView) currentView.findViewById(R.id.community_page_text);
        communityPersonalPage.setOnClickListener(this);

        TextView shopOrders = (TextView) currentView.findViewById(R.id.shop_text);
        shopOrders.setOnClickListener(this);

        mPostView = (TextView) currentView.findViewById(R.id.post);
        mPostView.setText(personalPageVO.getPostCount());

        mPostCommentsView = (TextView) currentView.findViewById(R.id.post_comment);
        mPostCommentsView.setText(personalPageVO.getPostCount());

        mCommunityCreditsView = (TextView) currentView.findViewById(R.id.credit);
        mCommunityCreditsView.setText(personalPageVO.getCredits());

        mPostCollectionsView = (TextView) currentView.findViewById(R.id.collection);
        mPostCollectionsView.setText(personalPageVO.getCollectionCount());

        mMemberStateView = (TextView) currentView.findViewById(R.id.member_state_text);
        mMemberStateView.setText(personalPageVO.getMemberVO().getUserType());

        mDueTimeView = (TextView) currentView.findViewById(R.id.due_time);
        mDueTimeView.setText(personalPageVO.getMemberVO().getEndTime());

        mCommunityIDView = (TextView) currentView.findViewById(R.id.community);
        mCommunityIDView.setText(personalPageVO.getUserInfoPO().getId() + "");

        mCommunityPageView = (TextView) currentView.findViewById(R.id.community_page);
        mCommunityPageView.
                setText("http://abletive.com/author/" + personalPageVO.getUserInfoPO().getId());

        mGenderView = (TextView) currentView.findViewById(R.id.gender);
        mGenderView.setText(personalPageVO.getGender());

        mMemberView = (TextView) currentView.findViewById(R.id.member);
        mMemberView.setText(personalPageVO.getRegisterTime());

        mShopOrdersView = (TextView) currentView.findViewById(R.id.shop);
        mShopOrdersView.setText(personalPageVO.getShopOrders());

    }

    @Override
    public void onClick(View v) {
        //TODO 监听
        switch (v.getId()) {
            case R.id.post_text:
                break;
            case R.id.post_comment_text:
                break;
            case R.id.credit_text:
                break;
            case R.id.collection_text:
                break;
            case R.id.due_time_text:
                break;
            case R.id.community_page_text:
                break;
            case R.id.shop_text:
                break;
        }
    }
}
