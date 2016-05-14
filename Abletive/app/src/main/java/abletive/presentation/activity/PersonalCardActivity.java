package abletive.presentation.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import abletive.presentation.tasks.CacheImageTask;
import alandelip.abletivedemo.R;
import cn.bingoogolapple.qrcode.core.DisplayUtils;
import cn.bingoogolapple.qrcode.zxing.QRCodeEncoder;

public class PersonalCardActivity extends AppCompatActivity {

    private static final String TAG = "Abletive";
    private static final String ARG_USERNAME = "user_name", ARG_USERID = "user_id", ARG_DES = "des",
            ARG_AVATAR = "avatar";
    private String userName, userID, description, avatarUrl;
    private Palette.Swatch muteSwatch, vibrantSwatch;

    public static void newInstance(Context context, String userName, String userID,
                                   String description, String avatarUrl) {
        Intent intent = new Intent(context, PersonalCardActivity.class);
        intent.putExtra(ARG_USERNAME, userName);
        intent.putExtra(ARG_USERID, userID);
        intent.putExtra(ARG_DES, description);
        intent.putExtra(ARG_AVATAR, avatarUrl);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_card);

        //获取
        Intent launchIntent = getIntent();
        if (launchIntent != null) {
            userName = launchIntent.getStringExtra(ARG_USERNAME);
            userID = launchIntent.getStringExtra(ARG_USERID);
            description = launchIntent.getStringExtra(ARG_DES);
            avatarUrl = launchIntent.getStringExtra(ARG_AVATAR);
        }

        if (avatarUrl != null) {
            CacheImageTask cacheImageTask = new CacheImageTask(avatarUrl);
            cacheImageTask.setCacheImageTaskCallBack(new CacheImageTask.CacheImageTaskCallBack() {
                @Override
                public void updateImage(Bitmap loadedImage) {
                    Palette palette = Palette.from(loadedImage).generate();
                    muteSwatch = palette.getLightMutedSwatch();
                    vibrantSwatch = palette.getLightVibrantSwatch();

                    initOtherViews(loadedImage);

                }
            });
            cacheImageTask.execute();
        }

        initViews();
    }

    private void initViews() {
        initToolbar();
        initQRCode();
    }

    private void initOtherViews(Bitmap loadedImage) {
        //背景
        ImageView backgroundView = (ImageView) findViewById(R.id.background);
        backgroundView.setImageBitmap(loadedImage);

        //用户头像
        ImageView avatarView = (ImageView) findViewById(R.id.user_avatar);
        avatarView.setImageBitmap(loadedImage);

        //用户名
        TextView userNameView = (TextView) findViewById(R.id.user_name);
        userNameView.setText(userName);
        setTextColor(userNameView);

        //用户ID
        TextView userIDView = (TextView) findViewById(R.id.user_id);
        String userIDText = "ABLETIVE ID: " + userID;
        userIDView.setText(userIDText);
        setTextColor(userIDView);

        //用户签名
        TextView descriptionView = (TextView) findViewById(R.id.user_description);
        descriptionView.setText(description);
        setTextColor(descriptionView);

        ImageView headerBackground = (ImageView) findViewById(R.id.header_background);
        if (vibrantSwatch != null) {
            headerBackground.setBackgroundColor(vibrantSwatch.getRgb());
        } else if (muteSwatch != null) {
            headerBackground.setBackgroundColor(muteSwatch.getRgb());
        }


    }

    private void initQRCode() {
        //生成userID的QRcode
        final ImageView qrcodeView = (ImageView) findViewById(R.id.user_matrix);
        String matrixID = "abletive://user/" + userID;
        QRCodeEncoder.encodeQRCode(matrixID, DisplayUtils.dp2px(this, 200), Color.TRANSPARENT, new QRCodeEncoder.Delegate() {
            @Override
            public void onEncodeQRCodeSuccess(Bitmap bitmap) {
                qrcodeView.setImageBitmap(bitmap);
            }

            @Override
            public void onEncodeQRCodeFailure() {
                Toast.makeText(PersonalCardActivity.this, "生成二维码失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        //TODO 分享功能
        setSupportActionBar(toolbar);
    }

    /**
     * 设置文字配色
     *
     * @param view TextView
     */
    private void setTextColor(TextView view) {
        if (vibrantSwatch != null) {
            view.setTextColor(vibrantSwatch.getTitleTextColor());
        } else if (muteSwatch != null) {
            view.setTextColor(muteSwatch.getTitleTextColor());
        }
    }

}
