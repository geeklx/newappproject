package com.haier.uplus.control.activity;

import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseActivity;
import com.haier.cellarette.baselibrary.widget.CircularSeekBar;
import com.haier.uplus.control.R;
import com.haier.uplus.control.utils.SharedPreferencesUtil;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:虚拟酒柜
 */
public class VirtualCellaretteActivity extends BaseActivity implements View.OnClickListener{

    ImageView mIvTipsOnFirstComeIn;
    private TextView tv_up_temp;
    private CircularSeekBar csb_temp_up;
    private ImageView iv_up_anim;
    private ImageView iv_down_anim;
    private AnimationDrawable anim_up;
    private AnimationDrawable anim_down;
    private TextView tv_down_temp;
    private CircularSeekBar csb_temp_down;

    private int Withtemperature = 5;
    /**
     * 第一个温度球的滑动温度
     */
    int currentValue = 0;
    /**
     * 第二个温度球的滑动温度
     */
    int currentValue1 = 0;
    private ImageView iv_lighting;
    private ImageView iv_dew;
    private ImageView iv_cycle;

    private boolean lighting = true;
    private boolean dew = true;
    private boolean cycle = true;
    private ImageView virtualbar_back;
    private RelativeLayout rl_root;
    private View virtualbar;
    private SharedPreferencesUtil sharedUtil;

    private static final String FIRST_TIME = "first_time";

    @Override
    protected int getLayoutId() {
        return R.layout.activity_virtual_cellarette;
    }

    @Override
    protected void setup(@Nullable Bundle savedInstanceState) {
        super.setup(savedInstanceState);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        initData();
        initView();
        setListener();
    }

    private void initView() {

        Typeface typeFace = Typeface.createFromAsset(getAssets(), "DINCond-Regular.ttf");
        rl_root = f(R.id.rl_root);
        tv_up_temp = f(R.id.tv_up_temp);
        tv_up_temp.setTypeface(typeFace);
        csb_temp_up = f(R.id.csb_temp_up);

        tv_down_temp = f(R.id.tv_down_temp);
        tv_down_temp.setTypeface(typeFace);
        csb_temp_down = f(R.id.csb_temp_down);

        iv_up_anim = f(R.id.iv_up_anim);
        iv_down_anim =  f(R.id.iv_down_anim);

        iv_lighting = f(R.id.iv_lighting);
        iv_dew = f(R.id.iv_dew);
        iv_cycle = f(R.id.iv_cycle);
        virtualbar_back = f(R.id.iv_back);
        csb_temp_up.setTemperatureValue(18, 18, 5);
        csb_temp_down.setTemperatureValue(20, 20, 5);
        tv_up_temp.setText("10");
        tv_down_temp.setText("10");

        iv_lighting.setOnClickListener(this);
        iv_dew.setOnClickListener(this);
        iv_cycle.setOnClickListener(this);
        virtualbar_back.setOnClickListener(this);

        iv_up_anim.setImageResource(R.drawable.top_the_snow);
        iv_down_anim.setImageResource(R.drawable.bottom_the_snow);

        anim_up = (AnimationDrawable)iv_up_anim.getDrawable();
        anim_down = (AnimationDrawable)iv_down_anim.getDrawable();
        anim_up.stop();
        anim_down.stop();
        virtualbar_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        csb_temp_up.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onSetValues(int progress) {
                currentValue = progress + Withtemperature;
                // 中间的文字
                String string = tv_up_temp.getText().toString();
                initValue = Integer.parseInt(string);
                if (initValue == currentValue) {
                    if (anim_up.isRunning()) {
                        anim_up.stop();
                    } else if (anim_down.isRunning()) {
                        anim_down.stop();
                    }
                } else if (initValue < currentValue) {
                    if (!anim_up.isRunning()) {
                        anim_down.start();
                    } else if (!anim_down.isRunning()) {
                        anim_up.start();
                    }

                } else if (initValue > currentValue) {
                    if (anim_up.isRunning()) {
                        anim_up.start();
                    } else if (!anim_down.isRunning()) {
                        anim_up.start();
                    } else {
                        anim_up.start();
                        anim_down.stop();
                    }
                }
            }

            @Override
            public void onRefreshTemperatureOfRoom() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChange(CircularSeekBar view, int newProgress) {

                String string = tv_up_temp.getText().toString();
                initValue = Integer.parseInt(string);
                String tempString = String.valueOf(newProgress + Withtemperature);
                if (Integer.valueOf(tempString) > initValue) {
                    iv_up_anim.setVisibility(View.GONE);
                    iv_down_anim.setVisibility(View.VISIBLE);
                    anim_up.stop();
                    anim_down.stop();
                } else if (Integer.valueOf(tempString) < initValue) {
                    iv_up_anim.setVisibility(View.VISIBLE);
                    iv_down_anim.setVisibility(View.GONE);
                    anim_up.stop();
                    anim_down.stop();
                } else {
                    iv_up_anim.setVisibility(View.GONE);
                    iv_down_anim.setVisibility(View.GONE);
                    if (anim_up.isRunning()) {
                        anim_up.stop();
                    } else if (anim_down.isRunning()) {
                        anim_down.stop();
                    }
                    anim_up.stop();
                    anim_down.stop();
                }
            }
        });
        csb_temp_down.setSeekBarChangeListener(new CircularSeekBar.OnSeekChangeListener() {

            @Override
            public void onSetValues(int progress) {
                currentValue1 = progress + Withtemperature;
                // 中间的文字
                String string = tv_down_temp.getText().toString();
                initValue1 = Integer.parseInt(string);
                if (initValue1 == currentValue1) {
                    if (anim_up.isRunning()) {
                        anim_up.stop();
                    } else if (anim_down.isRunning()) {
                        anim_down.stop();
                    }
                } else if (initValue1 < currentValue1) {
                    if (!anim_up.isRunning()) {
                        anim_down.start();
                    } else if (!anim_down.isRunning()) {
                        anim_up.start();
                    }

                } else if (initValue1 > currentValue1) {
                    if (anim_up.isRunning()) {
                        anim_up.start();// tashi
                    } else if (!anim_down.isRunning()) {
                        anim_up.start();
                    } else {
                        anim_up.start();
                        anim_down.stop();
                    }
                }
            }

            @Override
            public void onRefreshTemperatureOfRoom() {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProgressChange(CircularSeekBar view, int newProgress) {

                String string = tv_down_temp.getText()
                        .toString();
                initValue1 = Integer.parseInt(string);
                String tempString = String.valueOf(newProgress + Withtemperature);
                if (Integer.valueOf(tempString) > initValue1) {
                    iv_up_anim.setVisibility(View.GONE);
                    iv_down_anim.setVisibility(View.VISIBLE);
                    anim_up.stop();
                    anim_down.stop();
                } else if (Integer.valueOf(tempString) < initValue1) {
                    iv_up_anim.setVisibility(View.VISIBLE);
                    iv_down_anim.setVisibility(View.GONE);
                    anim_up.stop();
                    anim_down.stop();
                } else {
                    iv_up_anim.setVisibility(View.GONE);
                    iv_down_anim.setVisibility(View.GONE);
                    if (anim_up.isRunning()) {
                        anim_up.stop();
                    } else if (anim_down.isRunning()) {
                        anim_down.stop();
                    }
                    anim_up.stop();
                    anim_down.stop();
                }

            }
        });

        tv_up_temp.setOnClickListener(this);
        tv_down_temp.setOnClickListener(this);

        csb_temp_up.edit();
        csb_temp_down.edit();

//        Button btn_know = (Button) findViewById(R.id.btn_know);
//        btn_know.setOnClickListener(this);

//        virtualbar = findViewById(R.id.activity_virtual_guide);
        sharedUtil = SharedPreferencesUtil.getInstance();

        //初始化遮罩层
//        boolean isFirstTime = PreferencesUtils.getBoolean(mBaseActivity, FIRST_TIME, true);
//        if (isFirstTime){
//            mIvTipsOnFirstComeIn.setVisibility(View.VISIBLE);
//            mIvTipsOnFirstComeIn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    mIvTipsOnFirstComeIn.setClickable(true);
//                    mIvTipsOnFirstComeIn.setVisibility(View.GONE);
//                    PreferencesUtils.putBoolean(mBaseActivity,FIRST_TIME,false);
//                }
//            });
//        }else {
//            mIvTipsOnFirstComeIn.setVisibility(View.GONE);
//            mIvTipsOnFirstComeIn.setClickable(false);
//        }


    }

    /**
     * 第一个温度球中间显示的温度
     */
    private long initValue = 0;
    /**
     * 第二个温度球中间显示的温度
     */
    private long initValue1 = 0;

    private void initData() {
        // TODO Auto-generated method stub

    }

    protected void setListener() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onClick(View arg0) {
        int i = arg0.getId();
        if (i == R.id.iv_lighting) {
            if (lighting) {
                iv_lighting.setBackgroundResource(R.drawable.light_icon1);
                lighting = false;
            } else {
                iv_lighting.setBackgroundResource(R.drawable.light_icon2);
                lighting = true;
            }


        } else if (i == R.id.iv_dew) {
            if (dew) {
                iv_dew.setBackgroundResource(R.drawable.location_icon1);
                dew = false;
            } else {
                iv_dew.setBackgroundResource(R.drawable.location_icon2);
                dew = true;
            }


        } else if (i == R.id.iv_cycle) {
            if (cycle) {
                iv_cycle.setBackgroundResource(R.drawable.loop_icon1);
                cycle = false;
            } else {
                iv_cycle.setBackgroundResource(R.drawable.loop_icon2);
                cycle = true;
            }


        } /*else if (i == R.id.virtualbar_back) {
            finish();

        } else if (i == R.id.btn_know) {
            virtualbar.setVisibility(View.GONE);
            seekbar_home_airConditionPageView_temperature.edit();
            seekbar_home_airConditionPageView_temperature1.edit();
            selector_mywinecabinet_lighting.setClickable(true);
            selector_mywinecabinet_lighting.setFocusable(true);
            selector_mywinecabinet_dew.setClickable(true);
            selector_mywinecabinet_dew.setFocusable(true);
            icon_mywinecabinet_new_cycle.setClickable(true);
            icon_mywinecabinet_new_cycle.setFocusable(true);
            sharedUtil.setIsFirstRun(false);

        } else {
        }*/
    }
}
