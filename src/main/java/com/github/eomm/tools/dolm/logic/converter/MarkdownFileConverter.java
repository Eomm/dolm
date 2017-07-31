package com.github.eomm.tools.dolm.logic.converter;

import com.github.eomm.tools.dolm.bean.Converted;
import com.github.eomm.tools.dolm.logic.IConverter;
import com.github.eomm.tools.dolm.exceptions.ConvertException;
import com.github.eomm.tools.dolm.utils.ConvertFactory;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;

import java.io.File;
import java.io.FileReader;

/**
 * Convert a {@link File} to a Markdown {@link Node}
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

    /**
     * @param input a {@link File} in the markdown format
     * @return a {@link Converted} object with the original {@link File} and the converted {@link Node}
     * @throws ConvertException if some unexpected error occurs
     */
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
