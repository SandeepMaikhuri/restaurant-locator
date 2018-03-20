package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandeepmaikhuri.apps.restarauntlocator.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Sandeep on 7/12/16.
 */
public class MainActivity extends BaseActivity
{
    @Bind(R.id.button) Button btn_locateRestaurants;
    @Bind(R.id.label_feeling_hungry) TextView label_feeling_hungry;
    @Bind(R.id.label_locate) TextView label_locate;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);

        initGUI();

    }

    @OnClick(R.id.button)
    void onButtonClick()
    {
        startActivity(new Intent(getBaseContext(), MapsActivity.class));
        finish();
    }

    private void initGUI()
    {
        setContentView(R.layout.main_activity);

        ButterKnife.bind(MainActivity.this);

        ((RelativeLayout.LayoutParams) btn_locateRestaurants.getLayoutParams()).setMargins(
                oLayoutCustomization.getRectViewWidthSize(17), 0,
                oLayoutCustomization.getRectViewWidthSize(17), oLayoutCustomization.getRectViewWidthSize(17));

        label_feeling_hungry.setTextSize(oLayoutCustomization.getFontSize(67));
        label_locate.setTextSize(oLayoutCustomization.getFontSize(27));
        btn_locateRestaurants.setTextSize(oLayoutCustomization.getFontSize(27));

        label_feeling_hungry.setTypeface(oCustomTypeface.coolDots);
        btn_locateRestaurants.setTypeface(oCustomTypeface.coolDots);
    }
}
