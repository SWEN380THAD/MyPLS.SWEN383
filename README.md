# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.3/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-developing-web-applications)
* [List of Spring annotations](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/bind/annotation/)
* [Apache Freemarker](https://docs.spring.io/spring-boot/docs/2.4.3/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)


# Basic Structure
all coding takes place in the src folder.  there are two basic catagories within the src folder
Java and Recources.  The Java folder contains all of the Java code such as the "Handlers" 
or "Controllers". The resources folder contains  all of the FTLH(version of FTL) files, CSS, JS, 
and any Media related to the web page.  each web page is held in the "templates" folder within the 
"resources" folder.

# Functionality
the very basic way that all of this works is that Spring Boot pass data back and forth
between the FTLH pages and the Java Controllers.  within the FTLH page, we use the Maven FreeMarker
language to specify and user variables created within the Java Code.  Take the index.ftlh page for
example, upon initial creation, the java code uses the @GetMApping spring boot annotation 
to create the page and sends no variables along.  within the index page there is Maven code that looks
for a variable by the name of "msg".  If it finds it, it embeds that variable into the header
if not, it displays the normal header.  Once the user fills out the form and clicks the Submit button,
Spring Boot, passes the input values back to the indexController.  at that point we can use those variables
however we see fit.

# Running application in InteliJ
run the Application java file in inteliJ.  it should compile and list a bunch of initilizations in teh run window.
once it is up and running, open a web browser to http://localhost:8080.  it should be teh fully functional website 
you just created. have fun.