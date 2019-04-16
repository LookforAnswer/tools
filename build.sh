#!/bin/sh
echo update source code...
git branch
git pull

echo stop application...

PID=$(ps -ef | grep tools-0.0.1-SNAPSHOT.jar | grep -v grep | awk '{ print $2 }')
if [ -z "$PID" ]
then
    echo application is already stopped.
else
    echo killing $PID.
    kill $PID
    echo done.
fi

echo package application...

mvn -U -T 1C clean package

echo start application...

cd target

nohup mvn spring-boot:run -Drun.profiles=default &
tail -f nohup.out

