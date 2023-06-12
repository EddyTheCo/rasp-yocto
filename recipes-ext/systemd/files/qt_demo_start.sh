#!/bin/sh

start() {
   nftminter 
}

stop() {
    /usr/bin/killall nftminter
}

case "$1" in
    start|restart)
        echo "Starting QtDemo"
        stop
        start
        ;;
    stop)
        echo "Stopping QtDemo"
        stop
        ;;
esac
