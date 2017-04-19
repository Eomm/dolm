# DOLM

DOLM _(Document Lifecycle Management)_ is a maven plugin for managing documentation across developers and manager using markdown.

The software developer's team can create and update the documentation using his VCS and build a nice PDF (for now) for the customers o the top management that don't know what is it markdown.

### Feature
1. Convert all the `.md` files in a directory to `*-converted.pdf` files in a output directory
2. **[TODO]** Versioning the output name of the pdfs _(es: README-v1.0.0.pdf)_
3. **[TODO]** Add a template system to the `.md` files
4. **[TODO]** Customize the output file format (`docx`, `odt`, ecc...)

### Next steps
+ JUnit
+ Deploy on [mvnrepository](https://mvnrepository.com/)
+ Define License
+ Better integration in maven lifecycle

### Know Limits
+ The images path on the `.md` file must be absolute
+ The tables aren't styled

### Usage
Once [installed the lib](https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html) on your local machine simply add:
```
<build>
  <plugins>
    <plugin>
      <groupId>it.eomm.plugins</groupId>
      <artifactId>dolm-maven-plugin</artifactId>
      <version>0.0.1-SNAPSHOT</version>
      <executions>
        <execution>
        <id>build-nice-docs</id>
        <goals>
          <goal>build-docs</goal>
        </goals>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

And then run `mvn site`.

### Parameters
Name | Description | Default value
--- | --- | --- |
sourcePath | Directory where read the `md` files | src\main\resources\docs
outputPath | Directory where save the PDF files | target\docs
filter | Pattern to ignore some filename | `(.)*md$`

#### Dependancies
+ [com.atlassian.commonmark](https://github.com/atlassian/commonmark-java) for convert markdown to html
+ [com.itextpdf](http://itextpdf.com/) for build the PDF from an html string
