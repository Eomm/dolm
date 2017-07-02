package it.eomm.plugins.dolm;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.logging.Log;
import org.apache.maven.plugins.annotations.Parameter;

/**
 * This abstract class let you to easy add new goals to the plugin
 * <p>
 * Created by Manuel Spigolon on 01/07/2017.
 */
public abstract class Dolm extends AbstractMojo {

    @Parameter(defaultValue = "${project.build.resources[0].directory}/docs/")
    protected String sourcePath;

    @Parameter(defaultValue = "(.)*md$")
    protected String filter;

    @Parameter(defaultValue = "${project.build.directory}/docs/")
    protected String outputPath;

    @Parameter(defaultValue = "${project.version}", required = true)
    protected String documentVersion;

    /**
     * Define the filename output. Use String.format to generate the filename.
     * The available position's parameters are:
     * %1$s = source filename (with no extension)
     * %2$s = ${documentVersion} parameter
     * %3$s = output file format
     * %4$tD = date as MM/dd/yy
     * %4$td = current day
     * %4$tm = current month
     * %4$ty = current 2 digit's year
     * %4$tY = current 4 digit's year
     */
    @Parameter(defaultValue = "%1$s.%3$s", required = true)
    protected String filenamePatternOutput;

    protected Log log;

    public Dolm() {
        super();
        log = getLog();
    }

}
