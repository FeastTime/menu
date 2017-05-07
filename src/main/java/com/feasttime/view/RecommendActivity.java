package com.feasttime.view;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.feasttime.adapter.MainMenuPagerAdapter;
import com.feasttime.menu.R;
import com.feasttime.model.bean.MenuItemInfo;
import com.feasttime.presenter.IBasePresenter;
import com.feasttime.presenter.menu.MenuContract;
import com.feasttime.presenter.menu.MenuPresenter;
import com.feasttime.presenter.shoppingcart.ShoppingCartPresenter;
import com.feasttime.tools.LogUtil;
import com.feasttime.tools.ScreenTools;
import com.feasttime.widget.jazzyviewpager.JazzyViewPager;

import butterknife.Bind;

/**
 * Created by chen on 2017/5/7.
 */

public class RecommendActivity extends BaseActivity implements MenuContract.IMenuView{
    private MenuPresenter mMenuPresenter = new MenuPresenter();

    @Bind(R.id.title_bar_content_rb)
    RadioGroup mTtitleBarMenuRb;

    @Bind(R.id.recommend_activity_dishes_level_ll)
    LinearLayout dishesLevelLl;

    @Bind(R.id.recommend_activity_hot_level_ll)
    LinearLayout dishesHotLevelLl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMenuPresenter.getMenu("158000000","0","0","0","0");
        mTtitleBarMenuRb.removeAllViews();
    }

    @Override
    protected IBasePresenter[] getPresenters() {
        return new IBasePresenter[]{mMenuPresenter};
    }

    @Override
    protected void onInitPresenters() {
        mMenuPresenter.init(this);
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.recommend_activity;
    }

    @Override
    protected void initViews() {
        initRatingBar();
    }



    @Override
    public void showMenu(MenuItemInfo menuItemInfo) {
        RadioButton menuRb = new RadioButton(this);
        menuRb.setButtonDrawable(android.R.color.transparent);
        menuRb.setText(menuItemInfo.getDishName() + "\n" + "hot");
        menuRb.setTextColor(Color.WHITE);
        menuRb.setPadding(ScreenTools.dip2px(this,40),0,ScreenTools.dip2px(this,40),0);
        if (mTtitleBarMenuRb.getChildCount() == 0) {
            menuRb.setBackgroundResource(R.drawable.title_left_menu_selector);
        } else {
            menuRb.setBackgroundResource(R.drawable.title_normal_menu_selector);
        }

        mTtitleBarMenuRb.addView(menuRb);

        ViewGroup.LayoutParams params = menuRb.getLayoutParams();
        params.height = ViewGroup.LayoutParams.MATCH_PARENT;
        menuRb.setLayoutParams(params);

        ((RadioButton)mTtitleBarMenuRb.getChildAt(0)).setChecked(true);
    }

    private void initRatingBar() {
        for (int i = 0 ; i < 5 ; i ++) {
            CheckBox cb = new CheckBox(this);
            cb.setButtonDrawable(R.drawable.rating_bar_selector);
            cb.setChecked(true);
            cb.setTag(i);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int tag = (int)buttonView.getTag();
                        for (int i = 0 ; i < 5 ; i++) {
                            if (i <= tag) {
                                ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(true);
                            } else {
                                ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(false);
                            }
                        }
                    } else {
                        int tag = (int)buttonView.getTag();
                        for (int i = tag ; i < 5 ; i++) {
                            ((CheckBox)dishesLevelLl.getChildAt(i)).setChecked(false);
                        }
                    }
                }
            });
            dishesLevelLl.addView(cb);
        }

        for (int i = 0 ; i < 5 ; i ++) {
            CheckBox cb = new CheckBox(this);
            cb.setButtonDrawable(R.drawable.rating_bar_selector);
            cb.setChecked(true);
            cb.setTag(i);
            cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked) {
                        int tag = (int)buttonView.getTag();
                        for (int i = 0 ; i < 5 ; i++) {
                            if (i <= tag) {
                                ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(true);
                            } else {
                                ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(false);
                            }
                        }
                    } else {
                        int tag = (int)buttonView.getTag();
                        for (int i = tag ; i < 5 ; i++) {
                              ((CheckBox)dishesHotLevelLl.getChildAt(i)).setChecked(false);
                        }
                    }
                }
            });
            dishesHotLevelLl.addView(cb);
        }
    }
}
