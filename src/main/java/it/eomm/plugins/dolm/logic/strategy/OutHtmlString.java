package it.eomm.plugins.dolm.logic.strategy;

import java.io.File;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

import it.eomm.plugins.dolm.bean.MarkdownConverted;
import it.eomm.plugins.dolm.logic.IOutputStrategy;

public class OutHtmlString implements IOutputStrategy {

   @Override
   public Renderer buildRenderer(final List<Extension> extensions) {
      return HtmlRenderer.builder().extensions(extensions).build();
   }

   @Override
   public MarkdownConverted convert(final File inputFile, final String renderedContent) throws Exception {
      MarkdownConverted result = new MarkdownConverted();
      result.setConverted(true);
      // the render content is a html string thanks to buildOutRenderer
      result.setOutputString(renderedContent);

      return result;
   }

}
