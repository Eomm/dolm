package it.eomm.plugins.dolm.utils;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Iterator;

public class FileSystemIterator implements Iterator<File> {

   private String path;

   private FilenameFilter filter;

   private File[] directory;
   private int index;

   public FileSystemIterator(String path) {
      super();
      this.path = path;
      this.index = 0;
      this.directory = null;
   }

   private void openDirectory() {
      // if is already open don't read
      if (this.directory != null) {
         return;
      }

      File dir = new File(path);

      // TODO
      // if (!dir.isDirectory())
      // throw new IOException(path + " isn't a directory");

      this.directory = dir.listFiles(filter);

      // if nothing was found listFiles return null
      if (this.directory == null) {
         this.directory = new File[0];
      }
   }

   @Override
   public boolean hasNext() {
      openDirectory();
      return index < directory.length;
   }

   @Override
   public File next() {
      if (!hasNext()) {
         return null;
      }
      return directory[index++];
   }

   @Override
   public void remove() {
      // TODO NOT IMPLEMENT YET
   }

   public String getPath() {
      return path;
   }

   public FilenameFilter getFilter() {
      return filter;
   }

   public void setFilter(FilenameFilter filter) {
      this.filter = filter;
   }

}
