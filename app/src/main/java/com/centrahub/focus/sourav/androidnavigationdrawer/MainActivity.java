package com.centrahub.focus.sourav.androidnavigationdrawer;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.centrahub.focus.sourav.androidnavigationdrawer.SimpleAdapter.SelectableAdapter;
import com.centrahub.focus.sourav.androidnavigationdrawer.SimpleAdapter.SelectableViewHolder;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.CLOptionDTO;
import com.centrahub.focus.sourav.androidnavigationdrawer.dto.SelectableItem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectableViewHolder.OnItemSelectedListener {

    List <CLOptionDTO> clTestDTOArrayList;
    private SelectableAdapter selectableAdapter;
    private RecyclerView optionMenuList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getDrawerView());


    }


    private View intitCustomOptionMenu() {

        LinearLayout navigationMenuHeader = new LinearLayout(this);

        NavigationView.LayoutParams navigationMenuHeaderParam = new NavigationView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationMenuHeader.setGravity(Gravity.RIGHT);
        navigationMenuHeader.setOrientation(LinearLayout.VERTICAL);
        navigationMenuHeader.setLayoutParams(navigationMenuHeaderParam);
//        navigationMenuHeader.setBackgroundColor(getResources().getColor(android.R.color.holo_purple));


        clTestDTOArrayList = new ArrayList<>();
        String [] optioLable={"HOME","OFFERS","STORE","COMPLAINT","SERVICE CENTER","LOGOUT"};
        int[] images = new int[]{
                R.drawable.ic_house_outline,
                R.drawable.ic_tag,
                R.drawable.ic_store,
                R.drawable.ic_contract,
                R.drawable.ic_placeholder,
                R.drawable.ic_logout,
        };

        for (int i=0;i<optioLable.length;i++){
            CLOptionDTO clOptionDTO=new CLOptionDTO(optioLable[i],404+i,images[i]);
            clOptionDTO.setOptionImage(images[i]);
            clOptionDTO.setOptionLable(optioLable[i]);
            clOptionDTO.setOptionId(404+i);

             clTestDTOArrayList.add(clOptionDTO);
        }


        optionMenuList = new RecyclerView(this);

        LinearLayout.LayoutParams optionMenuListParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        optionMenuList.setLayoutParams(optionMenuListParam);
//        optionMenuList.setBackground(getResources().getDrawable(R.drawable.shadow_orangeyello));
//        optionMenuListParam.setMargins(20,20,20,20);
//        optionMenuList.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        optionMenuList.setLayoutManager(layoutManager);
//        NavigationOptionView selectableAdapter = new NavigationOptionView(this, clTestDTOArrayList);
        selectableAdapter = new SelectableAdapter(this,this,clTestDTOArrayList, false);
        optionMenuList.setAdapter(selectableAdapter);
/*        ImageView closeImage = new ImageView(this);
        LinearLayout.LayoutParams closeImageParam = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, CLUIUtil.toDip(this, 60));
        closeImageParam.setMargins(0, 15, 25, 0);
        closeImage.setLayoutParams(closeImageParam);
        closeImage.setImageDrawable(getResources().getDrawable(R.drawable.ic_close_black_24dp));
        closeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
                drawerLayout.closeDrawer(Gravity.LEFT);
//        drawerLayout.closeDrawers();

            }
        });

        navigationMenuHeader.addView(closeImage);*/

        navigationMenuHeader.addView(optionMenuList);
        ScrollView scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new NavigationView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        scrollView.addView(navigationMenuHeader);
        scrollView.scrollTo(0, 0);
        scrollView.setId(R.id.nav_scrollbar);
//        return navigationMenuHeader;
        return scrollView;
    }


    public View getDrawerView() {
        final DrawerLayout drawerLayout = new DrawerLayout(this);
        drawerLayout.setBackgroundColor(getResources().getColor(R.color.colorAppBackground));

        drawerLayout.setId(R.id.drawer_layout);
//        drawerLayout.setBackgroundColor(getResources().getColor(R.color.one_simplegraydark));
        ViewGroup.LayoutParams drawerLayout_param = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        drawerLayout.setFitsSystemWindows(true);
        drawerLayout.setLayoutParams(drawerLayout_param);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        NavigationView navigationViewList = new NavigationView(this);
        navigationViewList.setId(R.id.navigation_view);
        navigationViewList.setBackgroundColor(getResources().getColor(R.color.drawer_background1));
        navigationViewList.setItemTextColor(ColorStateList.valueOf(Color.WHITE));
//        navigationViewList.scrollTo(0, 0);
        DrawerLayout.LayoutParams navigationViewList_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        navigationViewList_param.width = getScreenWidth()/5;
        navigationViewList_param.gravity = Gravity.START;

        navigationViewList.setVerticalScrollBarEnabled(true);
        navigationViewList.addHeaderView(intitCustomOptionMenu());
        navigationViewList.setLayoutParams(navigationViewList_param);
        navigationViewList.setScrollBarSize(0);

        AppBarLayout appbar = new AppBarLayout(this);

        RelativeLayout.LayoutParams appbar_param = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        appbar.setLayoutParams(appbar_param);
        appbar_param.addRule(RelativeLayout.ALIGN_PARENT_TOP);
//        appbar_param.addRule(RelativeLayout.CENTER_IN_PARENT);
        appbar.setId(R.id.appbar_layout);
        Toolbar toolbar = new Toolbar(this);
        toolbar.setId(R.id.drawer_toolbar);
        AppBarLayout.LayoutParams toolbar_param = new AppBarLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        toolbar.setBackgroundColor(Color.GRAY);
        toolbar.setLayoutParams(toolbar_param);
        toolbar.setTitle("UNNÉCTΦ");
//        toolbar.setTitleTextColor(getResources().getColor(android.R.color.darker_gray));
        toolbar.setTitleTextColor(Color.GRAY);
        toolbar.setBackgroundColor(getResources().getColor(R.color.colorAppBackground));
//        toolbar.addView(getFlatToolbar());

        final RelativeLayout clRelativeLayout = new RelativeLayout(this);
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.app_name, R.string.app_name) {
            public void onDrawerClosed(View view) {
                supportInvalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
//                clRelativeLayout.setTranslationY(slideOffset * drawerView.getWidth());
                clRelativeLayout.setTranslationX(slideOffset * drawerView.getWidth());
                drawerLayout.bringChildToFront(drawerView);
                drawerLayout.requestLayout();
            }
        };


        drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        appbar.addView(toolbar);
//        LinearLayout clLinearLayout = new LinearLayout(this);
        clRelativeLayout.setId(R.id.main_content);
        DrawerLayout.LayoutParams clLinearLayout_param = new DrawerLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        clRelativeLayout.setGravity(Gravity.TOP);
        clRelativeLayout.setLayoutParams(clLinearLayout_param);
        clRelativeLayout.addView(appbar);
        clRelativeLayout.addView(listRecyclerOption());
//        clRelativeLayout.addView(fancyButton);

        drawerLayout.addView(clRelativeLayout);
        drawerLayout.addView(navigationViewList);
//        drawerLayout.addView(listRecyclerOption());
        return drawerLayout;
    }

    private View listRecyclerOption() {
        View view = new View(this);
        RelativeLayout.LayoutParams view_param=new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        view_param.addRule(RelativeLayout.BELOW,R.id.appbar_layout);
        view.setLayoutParams(view_param);
        view.setBackgroundColor(getResources().getColor(R.color.colorFadedWhite));
        return view;
    }

    @Override
    public void onItemSelected(SelectableItem selectableItem) {

        List<CLOptionDTO> selectedItems = selectableAdapter.getSelectedItems();
        Snackbar.make(optionMenuList,"Selected item is "+selectableItem.getOptionLable()+
                ", Totally  selectem item count is "+selectedItems.size(),Snackbar.LENGTH_LONG).show();
    }
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }

}
