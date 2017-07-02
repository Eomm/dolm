package it.eomm.plugins.dolm.logic.converter;

import it.eomm.plugins.dolm.bean.Converted;
import it.eomm.plugins.dolm.exceptions.ConvertException;
import it.eomm.plugins.dolm.logic.IConverter;
import it.eomm.plugins.dolm.utils.ConvertFactory;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import java.io.File;
import java.io.FileReader;

/**
 * Created by Manuel Spigolon on 02/07/2017.
 */
public class MarkdownFileConverter implements IConverter<File, Converted<File, Node>> {

    private ConvertFactory factory;

    public MarkdownFileConverter() {
        this(new ConvertFactory());
    }

    public MarkdownFileConverter(ConvertFactory factory) {
        this.factory = factory;
    }

    @Override
    public Converted<File, Node> convert(File input) throws ConvertException {
        Parser parser = factory.buildParser();
        try (FileReader reading = new FileReader(input)) {
            Converted<File, Node> converted = new Converted<>(input, parser.parseReader(reading));
            converted.setConverted(true);
            return converted;

        } catch (Exception e) {
            throw new ConvertException(e);
        }
    }
}
