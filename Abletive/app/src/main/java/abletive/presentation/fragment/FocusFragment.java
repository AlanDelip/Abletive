package abletive.presentation.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import abletive.presentation.activity.PersonalPageActivity;
import abletive.presentation.tasks.FollowTask;
import abletive.presentation.widget.UserItemAdapter;
import abletive.vo.FollowUserVO;
import alandelip.abletivedemo.R;

/**
 * 关注人界面碎片
 */
public class FocusFragment extends Fragment {

    private static final String ARG_USERID = "userID";
    private View currentView;
    private String userID;
    private ArrayList<FollowUserVO> userVOList;
    private int page = 1;
    private FollowTask followTask;

    public FocusFragment() {
        // Required empty public constructor
    }

    public static FocusFragment newInstance(String userID) {
        FocusFragment focusFragment = new FocusFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERID, userID);
        focusFragment.setArguments(args);
        return focusFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            userID = getArguments().getString(ARG_USERID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_focus, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentView = getView();

        followTask = new FollowTask(getContext(), userID,
                getContext().getString(R.string.following));
        followTask.setFollowTaskCallBack(new FollowTask.FollowTaskCallBack() {
            @Override
            public void init(ArrayList<FollowUserVO> userVOList) {
                FocusFragment.this.userVOList = userVOList;
                initListView();
                page = 2;
            }
        });
        followTask.execute(1);

    }

    private void initListView() {
        ListView followingList = (ListView) currentView.findViewById(R.id.focus_user_list);
        followingList.setAdapter(new UserItemAdapter(getContext(), R.layout.user_item, userVOList));
        followingList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FollowUserVO userVO = (FollowUserVO) parent.getItemAtPosition(position);
                PersonalPageActivity.newInstance(getContext(), userVO.getId());
            }
        });
    }
}
