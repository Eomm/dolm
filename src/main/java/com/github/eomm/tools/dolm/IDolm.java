package com.github.eomm.tools.dolm;

import org.apache.maven.plugin.Mojo;

/**
 * Created by Manuel Spigolon on 09/07/2017.
 */
public interface IDolm extends Mojo {
    void setSourcePath(String sourcePath);

    void setFilter(String filter);

    void setOutputPath(String outputPath);

    void setDocumentVersion(String documentVersion);

    void setFilenamePatternOutput(String filenamePatternOutput);
}
