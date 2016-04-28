package abletive.presentation.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import alandelip.abletivedemo.R;

/**
 * 个人资料界面碎片
 */
public class ProfileFragment extends Fragment {
    private static final String ARG_PARAM1 = "avatar";
    
    private Bitmap avatar;
    private View currentView;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * 创建个人资料碎片
     *
     * @param avatar 头像（用于配色）
     * @return 个人资料碎片
     */
    public static ProfileFragment newInstance(Bitmap avatar) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_PARAM1, avatar);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            avatar = getArguments().getParcelable(ARG_PARAM1);
        }
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
        //TODO
    }
}
