set projectLocation=E:\toolsqa
cd %projectLocation%
set classpath=%projectLocation%\bin;%projectLocation%\Maven Dependencies\*
java org.testng.TestNG %projectLocation%\app.smoke.xml
pause