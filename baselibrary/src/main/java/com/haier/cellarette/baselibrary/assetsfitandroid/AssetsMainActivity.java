package com.haier.cellarette.baselibrary.assetsfitandroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.haier.cellarette.baselibrary.R;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileandroid.FitAndroidActivity;
import com.haier.cellarette.baselibrary.assetsfitandroid.fileassets.FileAssetsActivity;

public class AssetsMainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assetsmain);

    }

    public void FileAssetsActivity(View view) {
        startActivity(new Intent(this, FileAssetsActivity.class));
    }

    public void FitAndroidActivity(View view) {
        startActivity(new Intent(this, FitAndroidActivity.class));
    }

}
