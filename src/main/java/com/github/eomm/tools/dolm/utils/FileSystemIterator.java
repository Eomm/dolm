package com.github.eomm.tools.dolm.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class FileSystemIterator implements Iterator<File> {

    private String path;

    private FilenameFilter filter;

    private File[] directoryContent;
    private int index;

    /**
     * Iterate all the files and directory in a dir path
     *
     * @param path to read
     */
    public FileSystemIterator(String path) {
        super();
        this.path = path;
        this.index = 0;
        this.directoryContent = null;
    }

    /**
     * Read the directory using <code>File.listFiles</code>
     */
    private void openDirectory() {
        // if is already open don't read
        if (this.directoryContent != null) {
            return;
        }

        File dir = new File(path);

         if (!dir.isDirectory())
             throw new IllegalStateException(path + " isn't a directory");

        this.directoryContent = dir.listFiles(filter);

        // if nothing was found listFiles return null
        if (this.directoryContent == null) {
            this.directoryContent = new File[0];
        }
    }

    /**
     * @see Iterator#hasNext()
     */
    @Override
    public boolean hasNext() {
        openDirectory();
        return index < directoryContent.length;
    }

    /**
     * @see Iterator#next()
     * @throws IllegalStateException if the path is not a directory
     */
    @Override
    public File next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return directoryContent[index++];
    }

    /**
     * @see Iterator#remove()
     */
    @Override
    public void remove() {
        throw new UnsupportedOperationException("remove");
    }

    public String getPath() {
        return path;
    }

    public FilenameFilter getFilter() {
        return filter;
    }

    /**
     * @param filter an optional filter to apply before iterate the directory
     */
    public void setFilter(FilenameFilter filter) {
        this.filter = filter;
    }

}
