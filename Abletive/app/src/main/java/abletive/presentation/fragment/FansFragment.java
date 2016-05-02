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

public class FansFragment extends Fragment {
    private static final String ARG_USERID = "userID";
    private View currentView;
    private String userID;
    private ArrayList<FollowUserVO> userVOList;
    private int page = 1;

    public FansFragment() {
        // Required empty public constructor
    }

    public static FansFragment newInstance(String userID) {
        FansFragment fragment = new FansFragment();
        Bundle args = new Bundle();
        args.putString(ARG_USERID, userID);
        fragment.setArguments(args);
        return fragment;
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
        return inflater.inflate(R.layout.fragment_fans, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        currentView = getView();

        FollowTask followTask = new FollowTask(getContext(),userID,
                getContext().getString(R.string.follower));
        followTask.setFollowTaskCallBack(new FollowTask.FollowTaskCallBack() {
            @Override
            public void init(ArrayList<FollowUserVO> userVOList) {
                FansFragment.this.userVOList = userVOList;
                initListView();
                page = 2;
            }
        });
        followTask.execute(1);
    }

    private void initListView() {
        ListView followerList = (ListView) currentView.findViewById(R.id.fans_user_list);
        followerList.setAdapter(new UserItemAdapter(getContext(), R.layout.user_item, userVOList));
        followerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FollowUserVO userVO = (FollowUserVO) parent.getItemAtPosition(position);
                PersonalPageActivity.newInstance(getContext(), userVO.getId());
            }
        });
    }
}
