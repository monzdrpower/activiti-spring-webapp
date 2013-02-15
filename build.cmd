call mvn clean package
rd /Q /S %CATALINA_HOME%\webapps
md %CATALINA_HOME%\webapps\Argos
copy target/Argos %CATALINA_HOME%/webapps
rename %CATALINA_HOME%/webapps/Argos/classes/process %CATALINA_HOME%/webapps/Argos/classes/process_