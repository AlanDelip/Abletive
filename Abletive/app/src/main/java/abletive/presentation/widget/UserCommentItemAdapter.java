package abletive.presentation.widget;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import abletive.vo.UserCommentVO;
import alandelip.abletivedemo.R;

/**
 * 用户文章评论适配器
 * Created by Alan on 2016/5/7.
 */
public class UserCommentItemAdapter extends ArrayAdapter<UserCommentVO> {

    private int resource;

    public UserCommentItemAdapter(Context context, int resource, List<UserCommentVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        UserCommentVO userCommentVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        TextView title = (TextView) convertView.findViewById(R.id.title);
        String titleText = "在《" + userCommentVO.getPostTitle() + "》中发表回复";
        title.setText(titleText);

        TextView comment = (TextView) convertView.findViewById(R.id.comment);
        comment.setText(userCommentVO.getComment());

        TextView agent = (TextView) convertView.findViewById(R.id.agent);
        agent.setText(userCommentVO.getAgent());

        TextView time = (TextView) convertView.findViewById(R.id.time);
        time.setText(userCommentVO.getTime());

        return convertView;
    }
}
