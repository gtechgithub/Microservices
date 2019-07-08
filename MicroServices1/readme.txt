Monolithic software architectures are really gone, hardware is not getting faster anymore,but internet traffic is still increasing.
platform must support scalability( the capacity to be changed in size or shape) and load must be distributed to several host.
Microservice based architectures can offer solutions for this requirement.

Microservices offers

a) scalability( the capacity to be changed in size or shape).
b) faster development.
c) improved failover behavior.
d) loose coupling which means changes in one process does not impact another process.
e) tight cohesion (together , unity). an individual service or process that deals with single view of data.

Microservices allow large systems to be build from a number of collaborating components.


                                              ------------------------
						Registration-service    
                                                  (Eureka)
                                              ------------------------
                                                        ^
                                                        |
                                                        |														
                                                        |
                                                        |
                                                        |
                                    |-------------------------------------|
                                    |                                     |
                                    | looks for "account-service"         | registers as "account-service"
                                    |                                     |
chrome                      ==================                     ===================                  ==================
                                               RESTful Requests                            JPA/SQL
internet explorer  ------>   Web-Service      ----------->           Account-Service     ------- ---->     Accounts (DB)

firefox                     -----------------                    ---------------------                  ---------------




Using an IDE
You can run the system in your IDE by running the three servers in order: 

* RegistrationService, 
* AccountsService and WebService.

As discussed in the Blog, open the Eureka dashboard http://localhost:1111 in your browser 
to see that the ACCOUNTS-SERVICE and WEB-SERVICE applications have registered. 

Next open the Demo Home Page http://localhost:3333 in and click one of the demo links.

The localhost:3333 web-site is being handled by a Spring MVC Controller in the WebService application, 
but you should also see logging output from AccountsService showing requests for Account data.

Command Line
You may find it easier to view the different applications by running them from a command line since you can place the three windows side-by-side and watch their log output

To do this, open three CMD windows (Windows) or three Terminal windows (MacOS, Linux) and arrange so you can view them conveniently.

In each window, change to the directory where you cloned the demo
1) In the first window, build the application using mvn clean package
2) In the same window run: java -jar target/microservice-demo-1.1.0.RELEASE.jar registration and wait for it to start up
3) Switch to the second window and run: java -jar target/microservice-demo-1.1.0.RELEASE.jar accounts and again wait for it to start up
4) In the third window run: java -jar target/microservice-demo-1.1.0.RELEASE.jar web

In your favorite browser open the same two links: http://localhost:1111 and http://localhost:3333
You should see servers being registered in the log output of the first (registration) window. As you interact with the web-application (http://localhost:3333) you should logging appear in the second and third windows.

In a new window, run up a second account-server using HTTP port 2223:
java -jar target/microservice-demo-1.1.0.RELEASE.jar accounts 2223
Allow it to register itself
Kill the first account-server and see the web-server switch to using the new account-server - no loss of service.




Project structure
-------------------
src/main/java
 com.example.microservices.accounts              ---> server side restful controller and restful uri
 com.example.microservices.services.accounts     ---> enable discovery client
 com.example.microservices.services.registration ---> enable eureka server
 com.example.microservices.services.web          ---> client side controller and enable eureka server
 
src/main/resouces
 accounts-server.yml                             ---> contains spring application name , eureka client service url and port no  
 registration-server.yml                         ---> contains eureka instance hostname and port no
 web-server.yml                                  ---> contains spring application name , eureka client service url and port no

 accounts-server/templates                       ---> thymeleaf templates for accounts-server restful server
 web-server/templates                            ---> thymeleaf templates for web-server restful client
 
 
essentail pom
--------------

		<dependency>
			<!-- Setup Spring Boot -->
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<dependency>
			<!-- Setup Spring MVC & REST, use Embedded Tomcat -->
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-web</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-thymeleaf</artifactId>
		</dependency>
		
		<dependency>
		    <groupId>org.springframework.cloud</groupId>
		    <artifactId>spring-cloud-starter</artifactId>
		    <version>1.3.3.RELEASE</version>
		</dependency>

       <dependency>
		    <groupId>org.springframework.cloud</groupId>
		    <artifactId>spring-cloud-starter-eureka</artifactId>
		    <version>1.4.4.RELEASE</version>
		</dependency>
	
  		<dependency>
		    <groupId>org.springframework.cloud</groupId>
		    <artifactId>spring-cloud-starter-eureka-server</artifactId>
		    <version>1.4.4.RELEASE</version>
		</dependency>
		
 
 
 
 
 
 
 
 


@JsonRootElement
@Link
@LoadBalanced
@EnableEurekaServer
@EnableDiscoveryClient
@ComponentScan(useDefaultFilters = false)
@InitBinder


