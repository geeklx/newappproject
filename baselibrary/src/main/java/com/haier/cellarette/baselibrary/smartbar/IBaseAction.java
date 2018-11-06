package com.haier.cellarette.baselibrary.smartbar;

import android.app.Activity;

public interface IBaseAction {
    void onBackPressed();
    void onHomePressed();
    Activity who();
}
