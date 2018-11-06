package com.haier.jiuzhidao.biz2_phone_pay_wx2.view;

import com.haier.cellarette.libmvp.mvp.IView;
import com.haier.jiuzhidao.biz2_phone_pay_wx2.bean.WxBean;

public interface GetWxView extends IView {
    void onWxSuccess(WxBean str);
    void onWxError(String str);

}
