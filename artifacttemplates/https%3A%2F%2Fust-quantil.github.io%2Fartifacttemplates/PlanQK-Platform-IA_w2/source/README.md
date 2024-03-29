* Interface Name: io_github_ust_quantil_artifacttemplates_PlanQKPlatform__ContainerManagementInterface
* Namespace: https://ust-quantil.github.io/artifacttemplates
* Java Package: io.github.ust.quantil.artifacttemplates

## Preconditions
To develop and build this Implementation Artifact you can use IntelliJ IDEA and need to have a Java 17 JDK installed e.g. Temurin 17.
IntelliJ already comes bundled with Maven.

## Preparations
Unzip the archive generated by Winery into a location of your choice.

## Create IntelliJ project

*The following steps use the menu structure on MacOS. On another OS they might be different*

- File -> New -> Project from Existing Sources...
- navigate to the folder that you have unziped, select the folder "source" and click on "open"
- in the "Import Project" dialog, select "Import project from external model", select "Maven" and click on "create"
- if you are asked whether to trust and open the project, click on "Trust Project"
- wait until the syncing and indexing is done
- open Run -> Edit Configurations...
- add a new configuration with the "+" button
- select "Maven"
- enter "clean package" in the run command line text field
- open Edit -> Project Structure, go to "Project" and select your Java 17 JDK under "SDK"
- in the main window select the Maven run configuration in the top right and run it, this will build the WAR artifact in the folder ia/target/
- the editor will show errors like "cannot resolve symbol" because it doesn't know the locations of the source files that were generated during the previous step
- right click on the folder api-client/target/generated-sources/openapi/src/main/java and choose Mark Directory as -> Source Root
- right click on the folder ia/target/generated-sources/src/main/java and choose Mark Directory as -> Source Root

## Test your Implementation Artifact
- the default port is 8080, if you want to run it on another port add the following line to ia/src/main/resources/application.properties: `server.port=<other port>`, replace `<other port>` with your preferred port
- select the "OpenToscaIASpringApplication" run configuration, that was automatically generated when you created the project, and run it
- now you can get the WSDL from `http://127.0.0.1:8080/io_github_ust_quantil_artifacttemplates_PlanQKPlatform__ContainerManagementInterfacePort?wsdl`, replace the port if you configured a different one
- with the WSDL you are able to test your IA using SoapUI or other tools

## Upload your Implementation Artifact
You have two options to do this:

### 1) Automatically (to the Winery instance this IA project was generated with)
- Run `mvn deploy`
- The WAR is directly uploaded into the correct ArtifactTemplate. Previous versions are overwritten.

### 2) Manually
- Run `mvn clean package` (or the Maven run configuration that you have created)
- Locate the WAR file in the /target folder
- Open Winery in your browser, locate the NodeType and then click through to the Implementation of the respective Interface, its Implementation Artifacts and lastly to the ArtifactTemplate representing this IA.
- Upload the WAR file by clicking on the "Attach file..." button
