# RESTful API POC (Language Service)


This POC is designed to get you up and running with a project structure for developing a RESTful API language service in Spring Boot. Keep in mind that this project was done in a few half days and lots of improvements can be done.


This could be easy changed to make it as part of SDK instead of being a RESTfull API. 

Just need a few extras things

```
@Bean
    public LocaleResolver localeResolver() {
        return new CookieLocaleResolver();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
        localeChangeInterceptor.setParamName("lang");
        registry.addInterceptor(localeChangeInterceptor);
    }
    
    The LocaleResolver helps to identify which locale is being used. In this post, we still use CookieLocaleResolver as an example.
Besides, the LocaleChangeInterceptor allows the application to switch to another locale. Here, we will use the request parameter lang to decide the target locale.

```

If you like to use {0}...{1} to pass some parameters, then you will have to extend the service with AbstractMessageSource to implement resolveCode

Having said all of that, If the performance is still not good enough (added basics metrics at the end), this could be migrated to Golang and using gRPC instead of RESTfull API.

**NOTE:** Everything will depend on the requirements.


Improvements:

* Caching (file vs memory)
* Real DB and connection pool
* Authentication and Authorization (Roles based)
* Remove unnecessary spring filter and improving spring start up
* App specific languages taking over common languages
* Race condition 
* Swagger / Docs
* Logging
* Exception handlers
* Pageable 
* Case insensitive
* Refactoring (DTOs, requests and reponses)
* Batches on Delete, Create, update....
* PATCH, Health check....

Please don´t be too cruel if Im missing something XD.

## Project Layout
**TODO**

## Getting Started

* mvn clean install
* mvn spring-boot:run

**TODO**

The RESTful API server running at `http://127.0.0.1:8080`. It provides the following endpoints:

* `POST api/v1/languages/{appName}`: Create a new language set
* `GET api/v1/languages/{appName}`: Get a language set
* `PUT api/v1/languages/{appName}`: Update a language set
* `DELETE api/v1/languages/{appName}`: Delete a language set
 
**NOTE:** This is a bulk API, but it could be added a single API. For now, a single entry is treated as one element in the bulk.


If you have `cURL` or some API client tools (e.g. [Postman](https://www.getpostman.com/)), you may try the following 
more complex scenarios:

```shell
# An example of retrieving the translated language a user: POST api/v1/users
curl -X GET -H "Content-Type: application/json" -H "Accept-Language: en-UK" -d http://localhost:8080/api/v1/languages/backup?key=home.w*
# should return 200 and a list of languages
```
**NOTE:** If the query param (key) is not specified, then it will retrieve all the translated languages for that appName. Keep in mind that the query param could also be a regex. There is also a postman collection under resources folder.


**I´ve carried out a test without any improvement to retrieve 1000 translated languages using H2 as DB. The response time  is about 720ms.
I don´t think a single UI view will have more than 200 keys**
