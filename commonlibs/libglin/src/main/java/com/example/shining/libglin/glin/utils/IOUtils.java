/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.shining.libglin.glin.utils;

import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * <p class="note">IO Utils</p>
 */
public class IOUtils {

    public static void closeGracefully(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeFile(File file, byte[] content) {
        DataOutputStream dos = null;

        try {
            dos = new DataOutputStream(new FileOutputStream(file));
            dos.write(content);
            dos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeGracefully(dos);
        }
    }

    public static String readFile(File file) {
        if (!file.exists() || file.isDirectory()) {
            return null;
        }

        DataInputStream dis = null;
        try {
            dis = new DataInputStream(new FileInputStream(file));
            return dis.readUTF();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            closeGracefully(dis);
        }

        return null;
    }
}
