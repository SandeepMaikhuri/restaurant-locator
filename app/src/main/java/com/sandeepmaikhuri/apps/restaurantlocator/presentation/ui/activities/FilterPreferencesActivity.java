package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.sandeepmaikhuri.apps.restarauntlocator.R;
import com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.adapters.ExpandableListAdapter;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.AppConstants;
import com.sandeepmaikhuri.apps.restaurantlocator.utils.LayoutCustomization;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sandeep on 16/12/16.
 */
public class FilterPreferencesActivity extends BaseActivity implements OnClickListener
{
    private ArrayList<String> categoriesList;
    private ArrayList<String> selectedItems;
    private List<String> groupHeaders;
    private LayoutCustomization oLayoutCustomization;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        initVariables();

        initGUI();
        setclickListeners();
    }

    private void initGUI()
    {
        setContentView(R.layout.filter_pref_activity);

        findViewById(R.id.header).getLayoutParams().height = oLayoutCustomization.getRectViewHeightSize(100);
        ((TextView)findViewById(R.id.header)).setTextSize(oLayoutCustomization.getRectViewWidthSize(27));
        ((TextView)findViewById(R.id.label_intro)).setTextSize(oLayoutCustomization.getRectViewWidthSize(17));

        ((RelativeLayout.LayoutParams)findViewById(R.id.button_done).getLayoutParams()).setMargins(0, oLayoutCustomization.getRectViewHeightSize(10),
                0, oLayoutCustomization.getRectViewHeightSize(10));


        if (getIntent() != null && getIntent().getStringArrayListExtra(AppConstants.CATEGORIES_LIST) != null)
        {
            categoriesList = getIntent().getStringArrayListExtra(AppConstants.CATEGORIES_LIST);

            ((ExpandableListView) findViewById(R.id.expandable_list)).setAdapter(new ExpandableListAdapter(this, groupHeaders, categoriesList, selectedItems));
        }
    }

    private void initVariables()
    {
        groupHeaders = new ArrayList<>();
        selectedItems = new ArrayList<>();

        groupHeaders.add("Categories");
        oLayoutCustomization = new LayoutCustomization(getResources().getDisplayMetrics());
    }

    private void setclickListeners()
    {
        findViewById(R.id.button_done).setOnClickListener(this);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
    }

    @Override
    public void onClick(View v)
    {
        int id = v.getId();

        switch (id)
        {
            case R.id.button_done:

                Intent intent = new Intent();
                intent.putStringArrayListExtra(AppConstants.SELECTED_VALUES, selectedItems);

                setResult(Activity.RESULT_OK, intent);
                finish();

                break;
        }
    }
}
