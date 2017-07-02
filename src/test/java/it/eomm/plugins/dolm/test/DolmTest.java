package it.eomm.plugins.dolm.test;

import it.eomm.plugins.dolm.Dolm;
import org.apache.maven.plugin.testing.AbstractMojoTestCase;

import java.io.File;

/**
 * Created by Manuel Spigolon on 21/04/2017.
 */
public class DolmTest extends AbstractMojoTestCase {

    /**
     * {@inheritDoc}
     */
    protected void setUp() throws Exception {
        // required
        super.setUp();
    }

    public void testCorrectLoading() throws Exception {
        File pom = getTestFile("src/test/resources/pom-test.xml");

        assertNotNull(pom);
        assertTrue(pom.exists());

//        Dolm mojo = (Dolm) lookupMojo("build-docs", pom);
//        assertNotNull(mojo);
    }


    /**
     * TODO add assertions after execution
     *
     * @throws Exception
     */
    public void testExecution() throws Exception {
        File pom = getTestFile("src/test/resources/pom-test.xml");

        assertNotNull(pom);
        assertTrue(pom.exists());

//        Dolm mojo = (Dolm) lookupMojo("build-docs", pom);
//        assertNotNull(mojo);
//
//        mojo.execute();
    }
}
