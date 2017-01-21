package com.sandeepmaikhuri.apps.restaurantlocator.presentation.ui.adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.TextView;


import com.sandeepmaikhuri.apps.restarauntlocator.R;

import java.util.List;

/**
 * Created by Sandeep on 16/12/16.
 */
public class ExpandableListAdapter extends BaseExpandableListAdapter
{

    private Context context;
    private List<String> listDataHeader;
    private List<String> categoiesList;
    private List<String> selectedItems;

    public ExpandableListAdapter(Context context, List<String> listDataHeader, List<String> categoiesList, List<String> selectedItems)
    {
        this.context = context;
        this.listDataHeader = listDataHeader;
        this.categoiesList = categoiesList;
        this.selectedItems = selectedItems;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon)
    {
        return categoiesList.get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition)
    {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
    {
        final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_child_item, null);
        }

        final CheckBox checkbox = (CheckBox) convertView.findViewById(R.id.checkbox);

        checkbox.setText(childText);
        if (selectedItems.contains(childText))
        {
            checkbox.setChecked(true);
        }
        else
        {
            checkbox.setChecked(false);
        }

        checkbox.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(checkbox.isChecked())
                {
                    selectedItems.add(childText);
                }
                else
                {
                    if(selectedItems.contains(childText))
                    {
                        selectedItems.remove(childText);
                    }
                }
            }
        });

        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition)
    {
        return this.categoiesList.size();
    }

    @Override
    public Object getGroup(int groupPosition)
    {
        return this.listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount()
    {
        return this.listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition)
    {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
    {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null)
        {
            LayoutInflater infalInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.list_group, null);
        }

        TextView lblListHeader = (TextView) convertView.findViewById(R.id.label_group_header);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        return convertView;
    }

    @Override
    public boolean hasStableIds()
    {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition)
    {
        return true;
    }
}