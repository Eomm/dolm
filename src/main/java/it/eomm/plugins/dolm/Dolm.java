package it.eomm.plugins.dolm;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.commonmark.ext.gfm.tables.TablesExtension;

import it.eomm.plugins.dolm.bean.MarkdownConverted;
import it.eomm.plugins.dolm.logic.MarkdownConverter;
import it.eomm.plugins.dolm.logic.strategy.OutPdf;
import it.eomm.plugins.dolm.utils.FileNamePatternFilter;
import it.eomm.plugins.dolm.utils.FileSystemIterator;

@Mojo(name = "build-docs", defaultPhase = LifecyclePhase.SITE)
public class Dolm extends AbstractMojo {

   @Parameter(defaultValue = "${project.build.resources[0].directory}/docs/")
   private String sourcePath;

   @Parameter(defaultValue = "(.)*md$")
   private String filter;

   @Parameter(defaultValue = "${project.build.directory}/docs/")
   private String outputPath;

   private Log log;

   public Dolm() {
      super();
      log = getLog();
   }

   @Override
   public void execute() throws MojoExecutionException, MojoFailureException {
      try {

         FileSystemIterator mdFilesIterator = new FileSystemIterator(sourcePath);
         mdFilesIterator.setFilter(new FileNamePatternFilter(filter));

         MarkdownConverter converter = new MarkdownConverter();
         converter.addExtension(TablesExtension.create());
         // converter.build(new OutHtmlString());
         converter.build(new OutPdf(outputPath));

         while (mdFilesIterator.hasNext()) {

            File from = mdFilesIterator.next();
            MarkdownConverted converted = converter.parseFile(from);

            if (converted.isConverted()) {
               log.info("Converted[" + from.getName() + "] to [" + converted.getOutputFile().getAbsolutePath() + "]");
            } else {
               log.warn("Cannot convert[" + from.getName() + "]");
            }
         }

      } catch (Exception ex) {
         throw new MojoExecutionException("Unexpected Error", ex);
      }
   }
}
