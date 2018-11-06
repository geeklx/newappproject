package com.haier.biz.identification.view;

import com.haier.biz.identification.bean.WineDetial;
import com.haier.cellarette.libmvp.mvp.IView;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/10.
 * Description:
 */

public interface WineListInfoView extends IView {
    void getListSuccess(WineDetial wineDetial);
    void getListFailure(String msg);
}
