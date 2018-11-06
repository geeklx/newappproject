package com.example.shining.libglin.juhenet;


import com.example.shining.libglin.glin.factory.ParserFactory;
import com.example.shining.libglin.glin.parser.Parser;

public class JuheParserFactory implements ParserFactory {
    @Override
    public Parser getParser() {
        return new JuheObjectParser();
    }

    @Override
    public Parser getListParser() {
        return new JuheListParser();
    }
}
