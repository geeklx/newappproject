package com.haier.cellarette.libwebview.hioscommon;

import com.haier.cellarette.libwebview.hois2.HiosAlias;

public class HiosRegister {

    private static final String PKG_SFNATION = "com.haier.cellarette.libwebview";

    public static void load() {

        HiosAlias.register("jump.HiosMainActivity", PKG_SFNATION, ".activity.HiosMainActivity");
//        HiosAlias.register("jump.webviewmainactivity", PKG_SFNATION, ".base.WebViewMainActivity");

    }
}
