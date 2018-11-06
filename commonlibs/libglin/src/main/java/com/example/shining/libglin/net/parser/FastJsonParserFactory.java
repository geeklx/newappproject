/**
 * Copyright 2016,Smart Haier.All rights reserved
 */
package com.example.shining.libglin.net.parser;


import com.example.shining.libglin.glin.factory.ParserFactory;
import com.example.shining.libglin.glin.parser.Parser;

/**
 * <p class="note">FastJsonParserFactory for Glin</p>
 * */
public class FastJsonParserFactory implements ParserFactory {

    @Override
    public Parser getParser() {
        return new CommParser("data");
    }

    @Override
    public Parser getListParser() {
        return new ListParser("data");
    }
}