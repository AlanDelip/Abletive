package abletive.businesslogic.internetbl;

import android.content.Context;

import abletive.businesslogic.blutil.HttpBuilder;
import abletive.businesslogic.blutil.HttpConnection;
import abletive.businesslogic.blutil.JSONHandler;
import abletive.logicservice.internetblservice.ForumHttpService;
import abletive.po.HttpRankListPO;
import abletive.presentation.uiutil.MApplication;
import alandelip.abletivedemo.R;

/**
 * 论坛相关网络逻辑
 *
 * @author Alan
 */
public class ForumHttpImpl implements ForumHttpService {

    private Context context;
    private HttpConnection httpConnection;

    public ForumHttpImpl() {
        context = MApplication.getContext();
        httpConnection = new HttpConnection();
    }

    @Override
    public HttpRankListPO getRankList(int limit) {
        String request = new HttpBuilder()
                .addField(context.getString(R.string.user), false)
                .addField(context.getString(R.string.get_credit_ranks))
                .addParam(context.getString(R.string.limit), limit)
                .build();

        String result = httpConnection.getResult(request);

        HttpRankListPO httpRankListPO = null;
        if (result != null) {
            httpRankListPO = JSONHandler.getRankList(result);
        }

        return httpRankListPO;
    }
}
