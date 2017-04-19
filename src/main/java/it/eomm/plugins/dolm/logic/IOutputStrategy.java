package it.eomm.plugins.dolm.logic;

import java.io.File;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.renderer.Renderer;

import it.eomm.plugins.dolm.bean.MarkdownConverted;

public interface IOutputStrategy {

   Renderer buildRenderer(final List<Extension> extensions);

   MarkdownConverted convert(final File inputFile, final String renderedContent) throws Exception;

}
