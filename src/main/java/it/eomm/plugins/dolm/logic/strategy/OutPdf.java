package it.eomm.plugins.dolm.logic.strategy;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.commonmark.Extension;
import org.commonmark.renderer.Renderer;
import org.commonmark.renderer.html.HtmlRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;

import it.eomm.plugins.dolm.bean.MarkdownConverted;
import it.eomm.plugins.dolm.logic.IOutputStrategy;

public class OutPdf implements IOutputStrategy {

   private String outputPath;

   public OutPdf(String outputPath) {
      super();
      this.outputPath = outputPath;
   }

   @Override
   public Renderer buildRenderer(final List<Extension> extensions) {

      // TODO input checks
      File outPath = new File(outputPath);
      if (!outPath.exists()) {
         outPath.mkdirs();
      }

      return HtmlRenderer.builder().extensions(extensions).build();
   }

   /**
    * TODO under development
    */
   @Override
   public MarkdownConverted convert(final File inputFile, final String renderedContent) throws Exception {
      MarkdownConverted result = new MarkdownConverted();

      Document document = new Document();
      File out = new File(outputPath, inputFile.getName() + "-converted.pdf");
      try {
         // document will close the stream
         FileOutputStream outStream = new FileOutputStream(out);
         PdfWriter writer = PdfWriter.getInstance(document, outStream);

         document.open();
         XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(renderedContent.getBytes()));

         result.setConverted(true);
         result.setOutputFile(out);

      } finally {
         if (document.isOpen())
            document.close();
      }

      return result;
   }

   public String getOutputPath() {
      return outputPath;
   }

   public void setOutputPath(String outputPath) {
      this.outputPath = outputPath;
   }

}
