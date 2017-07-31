package com.github.eomm.tools.dolm.utils;

import org.commonmark.Extension;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel Spigolon on 02/07/2017.
 */
public class ConvertFactory {

    private List<Extension> extensions;

    private Parser parser;

    private Renderer renderer;

    public ConvertFactory() {
        this.extensions = new ArrayList<>();
    }

    public void addExtension(Extension extension) {
        extensions.add(extension);
    }

    public Parser buildParser() {
        if (parser == null) {
            parser = Parser.builder().extensions(extensions).build();
        }
        return parser;
    }

    public Renderer buildRenderer() {
        if (renderer == null) {
            renderer = HtmlRenderer.builder().extensions(extensions).build();
        }
        return renderer;
    }
}