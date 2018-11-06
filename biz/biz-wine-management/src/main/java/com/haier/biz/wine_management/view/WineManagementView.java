package com.haier.biz.wine_management.view;

import com.haier.biz.wine_management.bean.WineManageBean;
import com.haier.cellarette.libmvp.mvp.IView;

import java.util.List;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/18.
 * Description:
 */
public interface WineManagementView extends IView {
    void onSuccess(List<WineManageBean.DataBean> result);
    void onError(String string);
}


