package com.haier.biz.pollorder.view;

import com.haier.biz.pollorder.bean.PollOrderBean;
import com.haier.cellarette.libmvp.mvp.IView;

/**
 * Author:sunnyhack
 * E-mail:sunnyhack@live.com
 * Date:on 18/5/11.
 * Description:
 */
public interface PollOrderView extends IView {
    void onSuccess(PollOrderBean.DatasBean result);
    void onError(String string);
}


