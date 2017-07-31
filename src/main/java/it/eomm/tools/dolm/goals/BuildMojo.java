package it.eomm.tools.dolm.goals;

import it.eomm.tools.dolm.Dolm;
import it.eomm.tools.dolm.bean.Converted;
import it.eomm.tools.dolm.logic.PipelineConverter;
import it.eomm.tools.dolm.logic.converter.MarkdownFileConverter;
import it.eomm.tools.dolm.logic.converter.OutPdf;
import it.eomm.tools.dolm.utils.ConvertFactory;
import it.eomm.tools.dolm.utils.FileNamePatternFilter;
import it.eomm.tools.dolm.utils.FileSystemIterator;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.commonmark.ext.gfm.tables.TablesExtension;

import java.io.File;

/**
 * Created by Manuel Spigolon on 01/07/2017.
 */
@Mojo(name = "build", defaultPhase = LifecyclePhase.PRE_SITE)
public class BuildMojo extends Dolm {

    /**
     * Convert all the files in @{sourcePath} matching the @{filter} filename's pattern to PDF files in the @{outputPath} with @{filenamePatternOutput}.<br>
     * If there aren't files in the directory the plugins does nothings.
     *
     * @throws MojoExecutionException when an unexpected error occurs
     * @throws MojoFailureException when a parameter is not valid
     */
    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        try {

            FileSystemIterator mdFilesIterator = new FileSystemIterator(sourcePath);
            mdFilesIterator.setFilter(new FileNamePatternFilter(filter));

            if (!mdFilesIterator.hasNext()) {
                log.warn("No files to convert on path " + mdFilesIterator.getPath());
                return; // nothing to do here..
            }

            ConvertFactory factory = new ConvertFactory();
            factory.addExtension(TablesExtension.create());

            PipelineConverter<File, Converted<?, File>> converter = new PipelineConverter<>();
            converter.addToPipeline(new MarkdownFileConverter(factory));
            converter.addToPipeline(new OutPdf(factory, outputPath, filenamePatternOutput, documentVersion));

            while (mdFilesIterator.hasNext()) {

                File from = mdFilesIterator.next();

                log.debug("Converting " + from.getAbsolutePath());
                Converted<?, File> converted = converter.parseFile(from);

                if (converted.isConverted()) {
                    log.info("Converted[" + from.getName() + "] to [" + converted.getTo().getAbsolutePath() + "]");
                } else {
                    log.warn("Cannot convert[" + from.getName() + "]");
                }
            }

        } catch (Exception ex) {
            throw new MojoExecutionException("Unexpected Error", ex);
        }
    }

}
