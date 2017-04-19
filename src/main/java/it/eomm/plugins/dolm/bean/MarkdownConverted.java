package it.eomm.plugins.dolm.bean;

import java.io.File;

public class MarkdownConverted {

   private boolean converted;
   private File outputFile;
   private String outputString;

   public boolean isConverted() {
      return converted;
   }

   public void setConverted(boolean converted) {
      this.converted = converted;
   }

   public File getOutputFile() {
      return outputFile;
   }

   public void setOutputFile(File outputFile) {
      this.outputFile = outputFile;
   }

   public String getOutputString() {
      return outputString;
   }

   public void setOutputString(String outputString) {
      this.outputString = outputString;
   }

}
