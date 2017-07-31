package it.eomm.tools.dolm.logic.converter;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import it.eomm.tools.dolm.bean.Converted;
import it.eomm.tools.dolm.exceptions.ConvertException;
import it.eomm.tools.dolm.logic.IConverter;
import it.eomm.tools.dolm.utils.ConvertFactory;
import org.commonmark.node.Node;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

/**
 * Convert a Markdown {@link Node} to a PDF {@link File}.
 */
public class OutPdf implements IConverter<Converted<File, Node>, Converted<Node, File>> {

    private ConvertFactory factory;

    private String outputPath;

    private String outputFileNamePattern;

    // TODO remove this logic from here
    private String outputFileVersion;

    public OutPdf(ConvertFactory factory, String outputPath, String outputFileNamePattern, String outputFileVersion) {
        this.factory = factory;
        this.outputPath = outputPath;
        this.outputFileNamePattern = outputFileNamePattern;
        this.outputFileVersion = outputFileVersion;
    }

    /**
     * Convert the input node to a PDF file using {@link PdfWriter}.<br>
     * If the @{outputPath} doesn't exist it will be created.
     *
     * @param input a {@link Converted} object with the original file read and the Markdown node.
     * @return a {@link Converted} object with the node from input and the {@link File} output
     * @throws ConvertException if some error occurs
     */
    @Override
    public Converted<Node, File> convert(Converted<File, Node> input) throws ConvertException {
        File outPath = new File(outputPath);
        if (!outPath.exists()) {
            outPath.mkdirs();
        }

        Converted<Node, File> output = new Converted<>();
        Document document = new Document();
        try {
            // prepare the output file
            String fileName = input.getFrom().getName();
            if (fileName.contains(".")) {
                fileName = fileName.substring(0, fileName.lastIndexOf('.'));
            }
            final String fileOutFileName = String.format(outputFileNamePattern, fileName, outputFileVersion, "pdf", new Date());
            File out = new File(outputPath, fileOutFileName);

            // convert the content of md file to string
            final String renderedContent = factory.buildRenderer().render(input.getTo());

            // document will close the stream
            FileOutputStream outStream = new FileOutputStream(out);
            PdfWriter writer = PdfWriter.getInstance(document, outStream);

            // write the content to the pdf file
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new ByteArrayInputStream(renderedContent.getBytes()));

            // write the result
            output.setFrom(input.getTo());
            output.setTo(out);
            output.setConverted(true);

        } catch (Exception e) {
            throw new ConvertException(e);
        } finally {
            if (document.isOpen())
                document.close();
        }

        return output;
    }

}