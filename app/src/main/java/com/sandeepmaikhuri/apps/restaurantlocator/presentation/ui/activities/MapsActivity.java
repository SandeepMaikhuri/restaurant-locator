package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MapsActivity extends AbstractMapActivity {
    @Bind(R.id.ic_filter)
    ImageView iconFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ButterKnife.bind(this);
    }
}
