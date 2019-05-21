**Spring Boot Kubernetes ConfigMap**

This project descibes how to setup configuration for a spring Boot cloud app using Kubernest ConfigMap.
Instead of loading configurations from the application.properties, the properties will be store in Kubernetes ConfigMap.
The application will then poll the ConfigMap for Configurations and updates.

See pom.xml file for dependencies used as well as the application.properties for necessary config to get this up.
