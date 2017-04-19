package it.eomm.plugins.dolm.utils;

import java.io.File;
import java.io.FilenameFilter;

public class FileNamePatternFilter implements FilenameFilter {

   private String pattern;

   public FileNamePatternFilter(String pattern) {
      super();

      if (pattern == null || pattern.isEmpty()) {
         throw new NullPointerException("pattern can't be null");
      }

      this.pattern = pattern;
   }

   @Override
   public boolean accept(File dir, String name) {
      return name.matches(pattern);
   }

}
