package it.eomm.plugins.dolm.logic.converter;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import it.eomm.plugins.dolm.bean.Converted;
import it.eomm.plugins.dolm.exceptions.ConvertException;
import it.eomm.plugins.dolm.logic.IConverter;
import it.eomm.plugins.dolm.utils.ConvertFactory;
import org.commonmark.node.Node;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

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
            String fileOutFileName = String.format(outputFileNamePattern, input.getFrom().getName(), outputFileVersion, "pdf", new Date());
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