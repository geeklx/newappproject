package com.example.shining.libglin.glin.factory;


import com.example.shining.libglin.glin.parser.Parser;

public interface ParserFactory {
    Parser getParser();
    Parser getListParser();
}
