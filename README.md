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

This simplify the sharing of documentation improving the communication process between analyst and developers.

The scope is: all the team must be up-to-date and the requirements must be available to everyone, avoiding oversights or human's errors like forgot an attachment.

### Process
If your situation is this ("Agile" in this image is an ideal not a fact):

![Before Dolm](images/before.jpg)

You can switch to this (git in the example isn't mandatory):

![Before Dolm](images/after.jpg)

Adopting a better communication between analysts and developer's team, but without force your customers to change their mind (receive documentation via emails or something like that).

### Feature
1. Convert all the `.md` files in a directory to files in a output directory
2. Versioning the output name of the pdf's _(es: README-v1.0.0.pdf)_

#### Know Limits
+ The images path on the `.md` file must be absolute
+ The tables aren't styled

## Usage

### Installation
Once [installed the lib](https://maven.apache.org/guides/mini/guide-3rd-party-jars-local.html) on your local machine simply add to your pom's:
```
<build>
  <plugins>
    <plugin>
      <groupId>it.eomm.plugins</groupId>
      <artifactId>dolm-maven-plugin</artifactId>
      <version>LATEST</version>
      <executions>
        <execution>
        <id>build-nice-docs</id>
        <goals>
          <goal>build</goal>
        </goals>
        <configuration>
            <sourcePath>${project.basedir}/src/main/resources/docs/functional</sourcePath>
            <documentVersion>9.0.8</documentVersion>
            <filenamePatternOutput>%4$td_%1$s-%2$s.%3$s</filenamePatternOutput>
        </configuration>
        </execution>
      </executions>
    </plugin>
  </plugins>
</build>
```

### Run
After the installation you can run the build goal by:
    
    mvn dolm:build
    
or using the default maven lifecycle:

    mvn pre-site
    mvn site

or, of course, you can customize the phase executions of the plugin specifying the `<phase>` tag on the `<execution>` declaration.

### Goals
Goal | Default Phase | Description |
--- | --- | --- |
`build`|`pre-site`|Convert all the files in the `${sourcePath}` that match `${filter}` and save them in the `${outputPath}` with the `${filenamePatternOutput}` filename|


### Parameters
Name | Description | Default value
--- | --- | --- |
sourcePath | Directory where the `md` files are read | src\main\resources\docs
outputPath | Directory where the output files are stored  | target\docs
filter | Pattern to ignore some filename | `(.)*md$`
documentVersion|The document version to apply|`${project.version}`
filenamePatternOutput|Customize the file name of the output files by `String.format`|`%1$s.%3$s`

#### Filename Pattern
This table resume the parameters passed to the `String.format` method's.

Code | Description
--- | --- |
%1$s | source filename (with no extension)
%2$s | ${documentVersion} parameter
%3$s | output file format (eg: pdf)
%4$tD | date as MM/dd/yy
%4$td | current day
%4$tm | current month
%4$ty | current 2 digit's year
%4$tY | current 4 digit's year


### Test
Simply run `mvn test`

### Next steps
+ :white_check_mark: JUnit 
+ :white_check_mark: Better integration in maven lifecycle
+ :white_medium_square: Deploy on [mvn-repository](http://central.sonatype.org/)
+ :white_medium_square: Add a template system to the `.md` files
+ :white_medium_square: Customize the output file format (`docx`, `odt`, ecc...)

### Dependancies
+ [com.atlassian.commonmark](https://github.com/atlassian/commonmark-java) for convert markdown to html
+ [com.itextpdf](http://itextpdf.com/) for build the PDF from an html string
