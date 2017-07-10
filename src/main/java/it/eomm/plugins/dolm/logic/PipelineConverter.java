package it.eomm.plugins.dolm.logic;

import it.eomm.plugins.dolm.exceptions.PipeException;

import java.util.ArrayList;
import java.util.List;

public class PipelineConverter<I, O> {

    private List<IConverter> pipeline;

    public PipelineConverter() {
        this.pipeline = new ArrayList<>();
    }

    public boolean addToPipeline(IConverter converter) {
        return pipeline.add(converter);
    }

    public O parseFile(final I in) throws PipeException {
        try {
            Object result = in;
            for (IConverter converter : pipeline) {
                result = converter.convert(result);
            }
            return (O) result;
        } catch (Exception ex) {
            throw new PipeException(ex);
        }
    }

}
