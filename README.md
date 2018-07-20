vaadingridsortindicators
========================

Derived demo from Vaadin Template for breaking a ComboBox not accepting new values when entering a non-existing value.

Demonstrate the problem in vaadin GH issue #10549 - ComboBox with setPageLength=0 can be rendered useless when quickly entering value #10549

Problem is fixed in [Pull Request #11068 by tsuoanttila](https://github.com/vaadin/framework/pull/11068) in Vaadin 8.6-SNAPSHOT in the meanwhile


Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"
