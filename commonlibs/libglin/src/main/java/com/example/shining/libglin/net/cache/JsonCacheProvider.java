package com.example.shining.libglin.net.cache;

import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.shining.libglin.glin.RawResult;
import com.example.shining.libglin.glin.Result;
import com.example.shining.libglin.glin.cache.ICacheProvider;
import com.example.shining.libglin.glin.helper.Helper;
import com.example.shining.libglin.glin.utils.IOUtils;
import com.example.shining.libglin.net.parser.JsonParser;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p class="note">JsonCacheProvider for Glin</p>
 */
public class JsonCacheProvider implements ICacheProvider {

    private static final String KEY = "data";
    private static final String SUFFIX = ".json";
    private static final String REGEXP = ".*\\\"?data\\\"?\\s*:\\s*\\{(.*?)\\}.*";

    private String mCachePath;
    private long mMaxCacheSize;

    public JsonCacheProvider(String cachePath, long maxCacheSize) {
        mCachePath = cachePath.endsWith("/") ? cachePath : cachePath + "/";
        mMaxCacheSize = maxCacheSize;

        File cacheFile = new File(mCachePath);
        if (!cacheFile.exists()) {
            for (int i = 0; i < 3; i++) {
                if (cacheFile.mkdirs()) {
                    break;
                }
            }
        }
    }

    // List<User>  User.class isList true
    @Override
    public <T> Result<T> get(String key, Class<T> klass, boolean isList) {
        Result<T> result = null;
        try {
            JSONObject baseObject = JSON.parseObject(readJSON(key));
            if (isList) {
                result = JsonParser.parseArray(baseObject, klass, KEY);
            } else {
                result = JsonParser.parseObject(baseObject, klass, KEY);
            }
            if (result != null) {
                result.setCache(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public <T> void put(String key, RawResult netResult, Result<T> result) {
        checkCacheSize();

        FileWriter fw = null;
        try {
            fw = new FileWriter(mCachePath + key + SUFFIX);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(netResult.getResponse());
            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeGracefully(fw);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getKey(String url, String params) {
        String key = "";
        if (params != null) {
            Pattern pattern = Pattern.compile(REGEXP);
            Matcher matcher = pattern.matcher(params);
            if (matcher.matches() && matcher.groupCount() == 1) {
                String matched = matcher.group(1).replaceAll("\\s*", "");
                key = Helper.md5(url + matched);
            }
        }

        if (TextUtils.isEmpty(key)) {
            key = Helper.md5(url);
        }

        return key;
    }

    private String readJSON(String key) {
        StringBuilder builder = new StringBuilder();
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(mCachePath + key + SUFFIX));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeGracefully(bufferedReader);
        }

        return builder.toString();
    }

    private void checkCacheSize() {
        File dir = new File(mCachePath);
        if (!dir.exists()) {
            return;
        }
        File[] files = dir.listFiles();

        long size = 0L;
        for (File item : files) {
            if (item == null) {
                continue;
            }
            size += item.length();
        }

        if (mMaxCacheSize <= size) {
            clearAllCache(files);
        }
    }

    private void clearAllCache(File[] files) {
        TAG:
        for (File file : files) {
            for (int i = 0; i < 3; i++) {
                if (file.delete()) {
                    continue TAG;
                }
            }
        }
    }
}
