@echo off
call mvn clean install
del /s /f /q g:\papermc\plugins\*.jar
copy target\BoomHalt*.jar g:\papermc\plugins\
cd g:\papermc
java -Xdebug -Xnoagent -Djava.compiler=NONE -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -Xms512M -Xmx1024M -XX:MaxPermSize=128M -jar g:\papermc\paper.jar