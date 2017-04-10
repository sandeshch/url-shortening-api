# URL Shortener 



## 1. Overview

URL Shortener is a Tiny URL generation and redirection REST API application which enables API users to create Short URLs for their long URLs. User needs to first  register himself for using this service. After successfull registration user can register URLs which he needs to shorten. After succesful URL registration user will be provided a short URL mapped to his long URL. Also there is an additional API for checking the statistcs of all his registered URLs i.e. how many hits each of his URL have received.

##### Technology Stack
 * Spring Boot 1.5.2 (embedded Tomcat 7)
 * Spring REST (Spring MVC)
 * Spring Data JPA (H2 embedded Database)
 * Servlet API 3.1
 * Maven 3.2.1
 * JDK 1.7
 

## 2. RESTful APIs

Basically there are **4** RESTful APIs which gets Hosted by this Library . All APIs endpoints are prefixed with **/tiny**  
e.g. for API URI, **/account**, then corresponding URL would be **__http://{ip_address:port}/tiny/account__**

Below is the list of Hosted services.

* User Account Registration API  
* URL Registration API
* User Account's URLs Statistics API
* URL Redirection API

### 2.1 User Account Registration API 

It is the primary api which is to be used for User registration. Upon successful registration User will get 


Field   |  Value/ Description
--------|-----
HTTP method   |  POST
URI | /account
Request type | application/json
Request Body | JSON object with the following parameters  * AccountId (String, mandatory) Example: { AccountId : 'myAccountId'} 
Reponse Type | application/json
Response | We distinguish the successful from the unsuccessful registration. Unsuccessful registration occurs only if the concerned account ID already exists. The parameters are as follows:  success: true | false description: Description of status, for example: account with that ID already exists  password: Returns only if the account was successfully created. Automatically generated password length of 8 alphanumeric characters Example {success: 'true', description: 'Your account is opened', password: 'xC345Fc0'}


### 2.2 URL Registration API

A valid user (the one who is already registered) can register hi URLs to get the corresponding short URLs. Same URLs can be tested in browser, to know if they are working or not.
**Please Note - valid accountId Header should be sent in header**


Field   |  Value/ Description
--------|-----
HTTP method   |  POST
URI| /register
Request type | application/json
Request Headers| accountId=**registered username**
Request Body |JSON object with the following parameters: * url (mandatory, url that needs shortening) * redirectType : 301 | 302 (not mandatory, default 302)Example: { url: 'http://stackoverflow.com/questions/1567929/website-safe-data-access-architecture-question?rq=1',redirectType : 301}
Reponse Type | application/json 
Response | Response parameters in case of successful registration are as follows: shortUrl (shortened URL) Example: { shortUrl: 'http://short.com/xYswlE'}

### 2.3 User Account's URLs Statistics API
After Succes URL registration, tiny URLs are ready to hit. The hit stats can be check using statistic service which provides all URLs for the correspnding users and hits for the URL.

**Please Note - valid accountId Header should be sent in header**

Field   |  Value/ Description
--------|-----
HTTP method |GET
URI | /statistic/{AccountId} 
Request Headers | accountId=**registered username**
Reponse Type| application/json
Response | The server responds with a JSON object, key:value map, where the key is the registered URL, and the value is the number              of this URL redirects.. Example: {  	'http://myweb.com/someverylongurl/thensomedirectory/: 10, 'http://myweb.com/someverylongurl2/thensomedirectory2/: 4, 	 'http://myweb.com/someverylongurl3/thensomedirectory3/: 91, }

### 2.4 URL Redirection API
This is default API which takes the tiny URL and redirects to the long URL which has been registered.


Field   |  Value/ Description
--------|-----
HTTP method   |  GET


## 3. Usage

The library short-url-api-1.0.jar can be run as normal jar. it has embedded Tomcat 7 and H2 DB. No external configuration need for setting up these.
````cmd 
java -jar short-url-api-1.0.jar
````
This will start the service at port 8080 and you should be able to use above APIs.


