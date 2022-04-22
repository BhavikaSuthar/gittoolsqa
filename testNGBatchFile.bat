set projectLocation=E:\toolsqa
cd %projectLocation%
set classpath=%projectLocation%\src\test\java\demo\toolsqa\alertsframesandwindows;%projectLocation%\Maven Dependencies\*
java org.testng.TestNG %projectLocation%\testng.xml
pause