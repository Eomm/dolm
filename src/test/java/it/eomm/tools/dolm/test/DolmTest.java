package it.eomm.tools.dolm.test;

import it.eomm.tools.dolm.IDolm;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;
import java.util.Calendar;

/**
 * Created by Manuel Spigolon on 21/04/2017.
 */
public class DolmTest extends AbstractMojoTestCase {

//    private Logger log = Logger.getLogger("DolmTest");

    private String sourceDir;
    private String outputDir;

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        // required
        super.setUp();

        IDolm mojo = getDolmMojo();
        assertNotNull(mojo);

        // this is run as @Before JUnit's methods
        sourceDir = System.getProperty("sourcePath");
        outputDir = System.getProperty("outputPath");
    }

    public void testConvertFile() throws Exception {
        IDolm mojo = getDolmMojo();
        mojo.setSourcePath(sourceDir + "/functional");
        mojo.setOutputPath(outputDir);
        mojo.setFilter("(.)*md$");
        mojo.setFilenamePatternOutput("%1$s.%3$s");
        mojo.execute();

        assertFileExist(outputDir + "/FA-awsomeFunction.pdf");
    }

    public void testConvertVersion() throws Exception {
        IDolm mojo = getDolmMojo();
        mojo.setSourcePath(sourceDir + "/technical");
        mojo.setOutputPath(outputDir);
        mojo.setFilter("(.)*md$");
        mojo.setFilenamePatternOutput("%1$s-%2$s.%3$s");
        mojo.setDocumentVersion("1.2.3.4");
        mojo.execute();

        assertFileExist(outputDir + "/FT-awsomeTechDoc-1.2.3.4.pdf");
        assertFileExist(outputDir + "/FT-awsomeTechDocService-1.2.3.4.pdf");
    }

    public void testFilterName() throws Exception {
        IDolm mojo = getDolmMojo();
        mojo.setSourcePath(sourceDir + "/filter");
        mojo.setOutputPath(outputDir + "/filtered");
        mojo.setFilter("(.)*md$");
        mojo.setFilenamePatternOutput("%1$s-%2$s.%3$s");
        mojo.execute();

        // no-one created
        File out = new File(outputDir + "/filtered");
        assertFalse(out.exists());
    }

    public void testOutputFilename() throws Exception {
        IDolm mojo = getDolmMojo();
        mojo.setSourcePath(sourceDir + "/technical");
        mojo.setOutputPath(outputDir + "/name");
        mojo.setFilter("(.)*(Service)(.)*md$");
        mojo.setFilenamePatternOutput("%1$s-%2$s-%4$td-%4$tm-%4$ty-%4$tY.%3$s");
        mojo.setDocumentVersion("1.2.3.4");
        mojo.execute();

        Calendar today = Calendar.getInstance();
        String d = String.format("%02d", today.get(Calendar.DAY_OF_MONTH));
        String m = String.format("%02d", today.get(Calendar.MONTH) + 1);
        String y = String.valueOf(today.get(Calendar.YEAR));
        assertFileExist(outputDir + "/name/FT-awsomeTechDocService-1.2.3.4-" + d + "-" + m + "-" + y.substring(2) + "-" + y + ".pdf");
        assertFileNotExist(outputDir + "/name/FT-awsomeTechDoc-1.2.3.4-" + d + "-" + m + "-" + y.substring(2) + "-" + y + ".pdf");
    }

    public void testEmptySourceDirectory() throws Exception {
        IDolm mojo = getDolmMojo();
        mojo.setSourcePath(sourceDir + "/empty");
        mojo.setOutputPath(outputDir);
        mojo.setFilter("\\\\*");
        mojo.setFilenamePatternOutput("%1$s-%2$s.%3$s");
        mojo.execute();
        // no errors expected
    }

    private void assertFileExist(final String filePath) {
        File file = new File(filePath);
        assertTrue(file.exists());
        file.delete();
    }

    private void assertFileNotExist(final String filePath) {
        assertFalse(new File(filePath).exists());
    }

    private IDolm getDolmMojo() {
        File pom = getTestFile("src/test/resources/pom-test.xml");

        assertNotNull(pom);
        assertTrue(pom.exists());

        try {
            return (IDolm) lookupMojo("build", pom);
        } catch (Exception e) {
            assertTrue(false);
            return null;
        }
    }

}
