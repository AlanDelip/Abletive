package abletive.presentation.widget;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import abletive.businesslogic.blutil.UserTransformer;
import abletive.presentation.activity.MainActivity;
import abletive.vo.CommentListVO;
import alandelip.abletivedemo.R;

/**
 * 评论列表适配器
 * Created by Alan on 2016/5/7.
 */
public class CommentListItemAdapter extends ArrayAdapter<CommentListVO> {
    int resource;

    public CommentListItemAdapter(Context context, int resource, List<CommentListVO> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentListVO commentListVO = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(resource, null);
        }

        ImageView avatarView = (ImageView) convertView.findViewById(R.id.avatar);
        String thumbUrl = commentListVO.getAvatarUrl();
        thumbUrl = UserTransformer.transfer(thumbUrl);
        MainActivity.IMAGE_CACHE.get(thumbUrl, avatarView);

        TextView replyView = (TextView) convertView.findViewById(R.id.reply);
        replyView.setText(commentListVO.getParentName());

        TextView authorView = (TextView) convertView.findViewById(R.id.user_name);
        authorView.setText(commentListVO.getAuthor());

        WebView commentWebView = (WebView) convertView.findViewById(R.id.comment_web_view);
        commentWebView.loadData(commentListVO.getCommentHtml(), "text/html;charset=utf-8", null);
        commentWebView.setBackgroundColor(Color.TRANSPARENT);

        TextView agentView = (TextView) convertView.findViewById(R.id.device_info);
        agentView.setText(commentListVO.getAgent());

        TextView timeView = (TextView) convertView.findViewById(R.id.time);
        timeView.setText(commentListVO.getTime());

        return convertView;
    }
}
