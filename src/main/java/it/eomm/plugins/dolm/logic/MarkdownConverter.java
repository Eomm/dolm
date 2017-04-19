package it.eomm.plugins.dolm.logic;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.Renderer;

import it.eomm.plugins.dolm.bean.MarkdownConverted;
import it.eomm.plugins.dolm.exceptions.ConvertException;

public class MarkdownConverter {

   private List<Extension> extensions;

   private Parser parser;

   private Renderer renderer;

   private IOutputStrategy strategy;

   public MarkdownConverter() {
      super();
      extensions = new ArrayList<>();
   }

   public void addExtension(Extension extension) {
      extensions.add(extension);
   }

   public void build(IOutputStrategy strategy) {
      this.strategy = strategy;

      parser = Parser.builder().extensions(extensions).build();
      renderer = this.strategy.buildRenderer(extensions);
   }

   public MarkdownConverted parseFile(final File file) throws IOException, ConvertException {
      if (parser == null || renderer == null) {
         throw new IOException("Call build before parseFile!");
      }

      try (FileReader reading = new FileReader(file)) {
         Node ndoc = parser.parseReader(reading);
         return strategy.convert(file, renderer.render(ndoc));

      } catch (Exception e) {
         throw new ConvertException(e);
      }
   }

}
