package android.com.constraintlayout;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.constraint.ConstraintSet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private Context mContext;
    private ConstraintLayout mConstraintLayout;
    private ImageView mBurgosImg;
    private ImageView mOkinawaImg;
    private ConstraintSet mConstraintSetDefault = new ConstraintSet();
    private ConstraintSet mConstraintSetBurgosBig = new ConstraintSet();
    private ConstraintSet mConstraintSetOkinawaBig = new ConstraintSet();
    private boolean mIsBigBurgosImg = false;
    private boolean mIsBigOkinawaImg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mConstraintLayout = (ConstraintLayout) findViewById(R.id.constraint_layout);
        mBurgosImg = (ImageView) findViewById(R.id.burgos_img);
        mOkinawaImg = (ImageView) findViewById(R.id.okinawa_img);

        mBurgosImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(mConstraintLayout);
                if (mIsBigBurgosImg) {
                    mConstraintSetDefault.applyTo(mConstraintLayout);
                } else {
                    mConstraintSetBurgosBig.applyTo(mConstraintLayout);
                }
                mIsBigBurgosImg = !mIsBigBurgosImg;
            }
        });

        mOkinawaImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TransitionManager.beginDelayedTransition(mConstraintLayout);
                if (mIsBigOkinawaImg) {
                    mConstraintSetDefault.applyTo(mConstraintLayout);
                } else {
                    mConstraintSetOkinawaBig.applyTo(mConstraintLayout);
                }
                mIsBigOkinawaImg = !mIsBigOkinawaImg;
            }
        });

        mConstraintSetDefault.clone(mConstraintLayout);
        mConstraintSetBurgosBig.load(mContext, R.layout.activity_main_burgos_big);
        mConstraintSetOkinawaBig.load(mContext, R.layout.activity_main_okinawa_big);
    }

    @Override
    public void onBackPressed() {
        if (mIsBigOkinawaImg) {
            TransitionManager.beginDelayedTransition(mConstraintLayout);
            mConstraintSetDefault.applyTo(mConstraintLayout);
            mIsBigOkinawaImg = !mIsBigOkinawaImg;
            return;
        }

        if (mIsBigBurgosImg) {
            TransitionManager.beginDelayedTransition(mConstraintLayout);
            mConstraintSetDefault.applyTo(mConstraintLayout);
            mIsBigBurgosImg = !mIsBigBurgosImg;
            return;
        }

        super.onBackPressed();
    }
}
