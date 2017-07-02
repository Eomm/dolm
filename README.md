# DOLM

DOLM _(Document Lifecycle Management)_ is a maven plugin for managing documentation across developers and manager using markdown.

## Purpose

The AGILE MANIFESTO says 
> Working software over comprehensive documentation

but this is interpreted like "no more documentation"!

So DOLM wants to give to the team the possibilities to maintain the project's docs in a easy way, leaving out the boring process of opening a text-editor, modifying the content and then send the file via email to the work group.

DOLM thinks that integrate the documentation's writing in a typical software project:
* you now can define a flowing and easy process that include update the documentation
* gives to the all the team the power to update a docs
* the source code can be versioned with the exact versions of the documentation
* all the functional's changes are well traced
* with a plain-text-document you can view the difference between docs versions
* all the team is constantly informed of the changes: a mail can be ignored, but you must update your VCS before commit

The analyst can create and update the functional documentation using his VCS and build a nice PDF (for now) for the customers o the top management that don't know what markdown is.

The software developer's team can create and update the technical documentation using his VCS and build a nice PDF (for now) for the software's analyst.

### Process
We explain a simple example process that can fit to your organization.
+ TODO

### Feature
1. Convert all the `.md` files in a directory to `*-converted.pdf` files in a output directory
2. **[TODO]** Versioning the output name of the pdfs _(es: README-v1.0.0.pdf)_
3. **[TODO]** Add a template system to the `.md` files
4. **[TODO]** Customize the output file format (`docx`, `odt`, ecc...)



### Know Limits
+ The images path on the `.md` file must be absolute
+ The tables aren't styled

## Usage

### Installation
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

### Goals
Goal | Default Phase | Description |
--- | --- | --- |
`draft`|`pre-site`|Generate |
`build`|`site`||

example: `dolm:draft`


### Parameters
Name | Description | Default value
--- | --- | --- |
sourcePath | Directory where the `md` files are read | src\main\resources\docs
outputPath | Directory where the output files are stored  | target\docs
filter | Pattern to ignore some filename | `(.)*md$`

### Test
Simply run `mvn test`

### Next steps
+ :white_check_mark: JUnit 
+ :white_check_mark: Better integration in maven lifecycle
+ Deploy on [mvnrepository](https://mvnrepository.com/)
+ Define License

#### Dependancies
+ [com.atlassian.commonmark](https://github.com/atlassian/commonmark-java) for convert markdown to html
+ [com.itextpdf](http://itextpdf.com/) for build the PDF from an html string
