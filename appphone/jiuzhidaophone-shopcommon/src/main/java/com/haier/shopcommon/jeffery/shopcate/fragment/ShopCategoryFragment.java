package com.haier.shopcommon.jeffery.shopcate.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.haier.cellarette.baselibrary.baseactivitys.BaseFragment;
import com.haier.cellarette.baselibrary.size.SizeUtils;
import com.haier.cellarette.baselibrary.toasts2.Toasty;
import com.haier.cellarette.libglide37.glide.GlideOptionsFactory;
import com.haier.cellarette.libglide37.glide.GlideUtil;
import com.haier.cellarette.libmvp.mvp.PresenterHelper;
import com.haier.shopcommon.R;
import com.haier.shopcommon.jeffery.base.CommonAdapter;
import com.haier.shopcommon.jeffery.base.CommonViewHolder;
import com.haier.shopcommon.jeffery.decoration.SpacesItemDecoration;
import com.haier.shopcommon.jeffery.shopcate.adapter.CateRWineAdapter;
import com.haier.shopcommon.jeffery.shopcate.adapter.ShopCateListAdapter;
import com.haier.shopcommon.jeffery.shopcate.bean.CategoryBean;
import com.haier.shopcommon.jeffery.shopcate.bean.Genre;
import com.haier.shopcommon.jeffery.shopcate.bean.WineBean;
import com.haier.shopcommon.jeffery.shopcate.presenter.IShopCategoryView;
import com.haier.shopcommon.jeffery.shopcate.presenter.ShopCatePresenter;
import com.haier.shopcommon.jeffery.view.searchview.CommonSearchView;
import com.haier.shopcommon.jeffery.view.searchview.EditCallBack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商城分类
 * Created by JefferyLeng on 2018/6/15.
 */
public class ShopCategoryFragment extends BaseFragment implements IShopCategoryView {

    private CommonSearchView mCommonSearchView;
    private Context mContext;

    private ListView mLvCateList;
    private RecyclerView mRvCateContent;

    private ShopCateListAdapter mShopCateListAdapter;

    private ShopCatePresenter mShopCatePresenter;

    /**
     * multiType style layout
     */
    public final String CATE_CONTENT_STYLE_PLACE = "TXT";
    public final String CATE_CONTENT_STYLE_BRAND = "PIC_TXT";
    public final String CATE_CONTENT_STYLE_RWINE = "LINE_POINT_SUB";
    public final String CATE_CONTENT_STYLE_BEER = "LINE_POINT";

    private List<CategoryBean.DataBean> mCategoryBeans = new ArrayList<>();
    private CateRWineAdapter mCateRWineAdapter;
    private CateRWineAdapter mCateBeerAdapter;
    private SpacesItemDecoration mSpacesItemDecoration;

    @Override
    protected int getLayoutId() {
        return R.layout.shop_category_fragment;
    }

    @Override
    protected void setup(View rootView, @Nullable Bundle savedInstanceState) {
        super.setup(rootView, savedInstanceState);
        mContext = getActivity().getApplicationContext();
        initView(rootView);
        initListener();
        mShopCatePresenter = PresenterHelper.create(ShopCatePresenter.class, this);
        mShopCatePresenter.start(mContext);

    }

    private void initView(View rootView) {
        mCommonSearchView = f(rootView, R.id.csv_search);
        mLvCateList = f(rootView, R.id.lv_cate_list);
        mRvCateContent = f(rootView, R.id.rcv_cate_content);
    }

    private void initListener() {
        if (mShopCateListAdapter == null) {
            mShopCateListAdapter = new ShopCateListAdapter(mContext, mCategoryBeans);
        }

        mLvCateList.setAdapter(mShopCateListAdapter);
        mLvCateList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mShopCateListAdapter.setSelectItem(i);
                mShopCateListAdapter.notifyDataSetInvalidated();
                //flag 根据左侧的列表点击 拿到对应的子数据集合
                CategoryBean.DataBean item = (CategoryBean.DataBean) mShopCateListAdapter.getItem(i);
                // TODO: 2018/6/29 临时的flag 数据，需要和后台接口约定
                String interfaceLink = item.getInterfaceLink();
                String moreLinkParam = item.getMoreLinkParam();
                matchAdapterForContent(interfaceLink, Integer.parseInt(moreLinkParam));
            }
        });

        mCommonSearchView.setOnEditCallBack(new EditCallBack() {
            @Override
            public void jumpToSearch() {
                // TODO: 2018/6/22 go to shop search page（replace fragment）
                Toasty.success(mContext, "go to search fragment").show();
            }
        });
    }

    /**
     * 根据左侧选中的类型来填充右侧商城内容
     *
     * @param type     该分类对应的布局样式
     * @param sequence gson中定义的数据获取index,type分类对应的要获取哪个数据集合数据
     *                 todo: 代码优化
     */
    private void matchAdapterForContent(String type, int sequence) {
        if (type == null || type.isEmpty()) {
            return;
        }
        if (CATE_CONTENT_STYLE_PLACE.equals(type)) {
            // TODO: 2018/6/29 mCategoryBeans.get(0).getDataList() : 为了避免前端写死，保证数据逻辑的灵活性，需要和后台对接建立排序字段，来作为数据获取的index
            mRvCateContent.setAdapter(new CommonAdapter<CategoryBean.DataBean.DataListBean>(mContext, R.layout.shop_cate_content_item_place, mCategoryBeans.get(sequence).getDataList()) {
                @Override
                protected void bind(CommonViewHolder holder, CategoryBean.DataBean.DataListBean dataListBean) {
                    TextView tvContentName = holder.getView(R.id.cate_content_tv_place);
                    tvContentName.setText(dataListBean.getTitle());
                }
            });
            GridLayoutManager mLayoutManager = new GridLayoutManager(mContext, 4);
            //添加ItemDecoration，item之间的间隔
            int leftRight = SizeUtils.dp2px(mContext, 10);
            int topBottom = SizeUtils.dp2px(mContext, 10);
            addDecoration(mLayoutManager,leftRight,topBottom);
        } else if (CATE_CONTENT_STYLE_BRAND.equals(type)) {
            mRvCateContent.setAdapter(new CommonAdapter<CategoryBean.DataBean.DataListBean>(mContext, R.layout.shop_cate_content_item_brand, mCategoryBeans.get(sequence).getDataList()) {

                @Override
                protected void bind(CommonViewHolder holder, CategoryBean.DataBean.DataListBean dataListBean) {
                    ImageView imageView = holder.getView(R.id.iv_cate_content_icon);
                    GlideOptionsFactory.init(mContext, R.drawable.circle);
                    GlideUtil.display(mContext, imageView, dataListBean.getImgURL(), GlideOptionsFactory.get(GlideOptionsFactory.Type.DEFAULT));
                    TextView tvHomeName = holder.getView(R.id.item_home_name);
                    tvHomeName.setText(dataListBean.getTitle());
                }
            });
            mRvCateContent.setLayoutManager(new GridLayoutManager(mContext, 3));

        } else if (CATE_CONTENT_STYLE_RWINE.equals(type)) {
            if (mCateRWineAdapter == null) {
                mCateRWineAdapter = new CateRWineAdapter(makeRWineGenre(mCategoryBeans.get(sequence).getDataList()));
            }
            //添加ItemDecoration，item之间的间隔
            int leftRight = SizeUtils.dp2px(mContext, 10);
            int topBottom = SizeUtils.dp2px(mContext, 10);
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            addDecoration(mLayoutManager,leftRight,topBottom);
            mRvCateContent.setAdapter(mCateRWineAdapter);
            mCateRWineAdapter.toggleGroup(0);

        } else if (CATE_CONTENT_STYLE_BEER.equals(type)) {
            if (mCateBeerAdapter == null) {
                mCateBeerAdapter = new CateRWineAdapter(makeRWineGenre(mCategoryBeans.get(sequence).getDataList()));
            }
            LinearLayoutManager mLayoutManager = new LinearLayoutManager(mContext);
            //添加ItemDecoration，item之间的间隔
            int leftRight = SizeUtils.dp2px(mContext, 10);
            int topBottom = SizeUtils.dp2px(mContext, 10);
            addDecoration(mLayoutManager,leftRight,topBottom);
            mRvCateContent.setAdapter(mCateBeerAdapter);
        }
    }

    private void addDecoration(RecyclerView.LayoutManager layoutManager,int leftRight,int topBottom) {
        mRvCateContent.removeItemDecoration(mSpacesItemDecoration);
        mRvCateContent.setLayoutManager(layoutManager);
        mSpacesItemDecoration = new SpacesItemDecoration(leftRight, topBottom);
        mRvCateContent.addItemDecoration(mSpacesItemDecoration);
    }

    /**
     * 填充酒品假数据
     * @param categoryBeans
     * @return
     */
    private List<Genre> makeRWineGenre(List<CategoryBean.DataBean.DataListBean> categoryBeans) {
        List<Genre> genres = new ArrayList<>();
        for (int i = 0; i < categoryBeans.size(); i++) {
            genres.add(new Genre(categoryBeans.get(i).getTitle(), makeWineList()));
        }
        return genres;
    }

    private List<WineBean> makeWineList() {
        WineBean ganhong = new WineBean("干红");
        WineBean banganhong = new WineBean("半干红");
        WineBean tianhong = new WineBean("甜红");
        WineBean bantianhong = new WineBean("半甜红");
        return Arrays.asList(ganhong, banganhong, tianhong, bantianhong);
    }

    /**
     * 底部点击bufen
     *
     * @param cateId
     * @param isrefresh
     */
    public void getCate(String cateId, boolean isrefresh) {
        if (!isrefresh) {
            return;
        }
        Toasty.normal(getActivity(), "下拉刷新啦").show();
    }

    @Override
    public void fillCateList(ArrayList<CategoryBean.DataBean> categoryBeans) {
        mCategoryBeans = categoryBeans;
        mShopCateListAdapter.addData(categoryBeans);
        mShopCateListAdapter.notifyDataSetChanged();
        matchAdapterForContent(CATE_CONTENT_STYLE_PLACE, 0);
    }
}
