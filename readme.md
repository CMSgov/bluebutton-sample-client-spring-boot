Blue Button Sample Client Application - Spring Boot Version
===========================================================

## Introduction

This client demonstrates authenticating to the Blue Buttom API and subsequent FHIR API calls.
It demonstrates the OAuth2 Server Side web application flow where a `client_secret` is used.

## Status and Contributing

This application is in active development so check back often for updates.
Please consider improving this code with your contributions. Pull requests welcome ;)

## Basic Setup

   https://github.com/CMSgov/bluebutton-sample-client-spring-boot.git


### Configuring Your Development Application

Your application will need to be set up to use the public OAuth service.  Go to  https://bluebutton.cms.gov/ for documentation on how to register for a sandbox account and register your application. 

Once you have your developer account created and you've verified your email address,
you'll need to register an application. Log in to your new account, and select
"Applications" -> "Applications You Created" -> "Register New Application". From
here, you can fill out the form with the following options:

    Scope: [you likely want to select all available]
    Name: [your choice]
    Client type: Confidential
    Authorization grant type: Authorization Code
    Redirect uris: http://localhost:8080/login
    
Once you've registered your application you can view the `client_id` and `client_secret` for your application. Copy these values and insert them into the respective fields in `src/main/resources/applications.yml`. With that, your application is ready to run.



### Final Steps

Finally, you're ready to execute

    mvn clean install (first time only)
    java -jar target/bluebutton-sample-client-spring-boot-0.0.1-SNAPSHOT.jar


And from here, you can navigate to http://localhost:8080/ and test your application.

You may use the following login information for testing the app:
	User: BBUser12345
	Password: PW12345!


