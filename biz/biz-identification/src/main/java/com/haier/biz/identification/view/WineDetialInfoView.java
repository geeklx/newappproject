package com.haier.biz.identification.view;

import com.haier.biz.identification.bean.WineItemDetail;
import com.haier.cellarette.libmvp.mvp.IView;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/4/13.
 * Description:
 */

public interface WineDetialInfoView extends IView {
    void getDetialSuccess(WineItemDetail wineItemDetail);
    void getDetialFailure(String msg);
}
