package com.github.eomm.tools.dolm.logic;

import com.github.eomm.tools.dolm.exceptions.ConvertException;

/**
 * Created by Manuel Spigolon on 02/07/2017.
 */
public interface IConverter<I, O> {

    O convert(final I input) throws ConvertException;

}
