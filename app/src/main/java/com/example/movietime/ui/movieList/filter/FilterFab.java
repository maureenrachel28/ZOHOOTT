package com.example.movietime.ui.movieList.filter;

import android.app.Dialog;
import android.util.ArrayMap;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.allattentionhere.fabulousfilter.AAH_FabulousFragment;
import com.example.movietime.R;
import com.google.android.flexbox.FlexboxLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class FilterFab extends AAH_FabulousFragment {

    private final String[] FILTER_TYPES = {"Genre", "Languages"};
    private View root;
    private TabLayout tabTypes;
    private ViewPager viewPager;
    private RelativeLayout filterLayout;
    private LinearLayout bottomView;
    private FilterPagerAdapter pagerAdapter;
    public MutableLiveData<ArrayList<String>> selectedGenres= new MutableLiveData<>(), selectedLanguages = new MutableLiveData<>();
    private ArrayList<Integer> availableStatus;


    @Override
    public void setupDialog(Dialog dialog, int style) {
        root = View.inflate(getContext(), R.layout.filter_fab_layout, null);
        initFilterView();


        root.findViewById(R.id.filter_reset).setVisibility(View.GONE);
        super.setupDialog(dialog, style);

    }

    private void initFilterView() {
        root.findViewById(R.id.filter_reset).setVisibility(View.GONE);
        tabTypes = root.findViewById(R.id.filter_tab_types);
        viewPager = root.findViewById(R.id.filterViewPager);
        filterLayout = (RelativeLayout) root.findViewById(R.id.rl_content);
        bottomView = (LinearLayout) root.findViewById(R.id.ll_buttons);
        pagerAdapter = new FilterPagerAdapter();
        setAnimationDuration(300);
        setViewgroupStatic(bottomView);
        setViewMain(filterLayout);
        setViewPager(viewPager);
        setMainContentView(root);
        viewPager.setAdapter(pagerAdapter);
        tabTypes.setupWithViewPager(viewPager);
        pagerAdapter.notifyDataSetChanged();
    }

    class FilterPagerAdapter extends PagerAdapter {
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.filter_selection_layout, container, false);
            FlexboxLayout fbl = (FlexboxLayout) layout.findViewById(R.id.filterFlexbox);
            inflateLayoutWithFilters(FILTER_TYPES[position], fbl);
            container.addView(layout);
            return layout;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return FILTER_TYPES[position];
        }

        private void inflateLayoutWithFilters(final String filter_type, FlexboxLayout flexboxLayout) {
            final ArrayList<String> keys = new ArrayList<>();
            switch (filter_type) {
                case "Languages":
                    keys.add("English");
                    keys.add("Tamil");
                    keys.add("Hindi");
                    break;
                case "Genre":
                    keys.add("Action");
                    keys.add("Animation");
                    keys.add("Romantic");
            }

            for (String key : keys) {
                View item = getActivity().getLayoutInflater().inflate(R.layout.filter_item, null);
                final TextView filterItem = ((TextView) item.findViewById(R.id.filterItemName));
                filterItem.setText(key);
                final String tagValue = key;
                if (selectedGenres.getValue() != null && selectedGenres.getValue().indexOf(tagValue) != -1) {
                    filterItem.setTag("selected");
                    filterItem.setBackgroundResource(R.drawable.chip_selected);
                } else {
                    filterItem.setTag("unselected");
                    filterItem.setBackgroundResource(R.drawable.chip_unselected);
                }
                filterItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch (filter_type) {
                            case "Languages":
                                ArrayList<String> tempSelectedLang = new ArrayList<>();
                                if (selectedLanguages.getValue() != null && selectedLanguages.getValue().size() != 0)
                                    tempSelectedLang = selectedLanguages.getValue();
                                if (filterItem.getTag() != null && filterItem.getTag().equals("selected")) {
                                    filterItem.setTag("unselected");
                                    filterItem.setBackgroundResource(R.drawable.chip_unselected);
                                    int index = tempSelectedLang.indexOf(tagValue);
                                    if (index != -1) {
                                        tempSelectedLang.remove(index);
                                    }
                                } else {
                                    filterItem.setTag("selected");
                                    filterItem.setBackgroundResource(R.drawable.chip_selected);
                                    tempSelectedLang.add(tagValue);
                                }
                                selectedLanguages.setValue(tempSelectedLang);
                                break;
                            case "Genre":
                                ArrayList<String> tempSelectedTags = new ArrayList<>();
                                if (selectedGenres.getValue() != null && selectedGenres.getValue().size() != 0)
                                    tempSelectedTags = selectedGenres.getValue();
                                if (filterItem.getTag() != null && filterItem.getTag().equals("selected")) {
                                    filterItem.setTag("unselected");
                                    filterItem.setBackgroundResource(R.drawable.chip_unselected);
                                    int index = tempSelectedTags.indexOf(tagValue);
                                    if (index != -1) {
                                        tempSelectedTags.remove(index);
                                    }
                                } else {
                                    filterItem.setTag("selected");
                                    filterItem.setBackgroundResource(R.drawable.chip_selected);
                                    tempSelectedTags.add(tagValue);
                                }
                                selectedGenres.setValue(tempSelectedTags);
                                break;
                        }

                    }
                });
                flexboxLayout.addView(item);
            }
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }
}
