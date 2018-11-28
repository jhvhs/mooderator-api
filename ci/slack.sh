#!/usr/bin/env bash

set -euox pipefail

load_stats() {
    stats=$(curl 'http://mooderator-api.cfapps.io/results/statistics' -H 'Content-Type: application/json')
    echo ${stats}
}

load_daily_stats() {
    stats=$(curl 'http://mooderator-api.cfapps.io/results/daily-statistics' -H 'Content-Type: application/json')
    echo ${stats}
}

main() {
    #TODO day should consider if today is Monday
    day=$(date -v-1d +%F)
    echo ${day}

    payload=$(load_daily_stats)

    question=$(echo ${payload} | jq '.[0] | .question')

    filter="map(select(.day == \"${day}\")) | map(\"\(.answer) \(.results)\") | join(\" - \")"

    msg=$(echo ${payload} | jq "${filter}")

#TODO fix this if
#    if [ $msg != '""' ]
#    then
        msg="\"${day} : ${msg//\"}\""
        curl -X POST -H 'Content-type: application/json' --data "{\"text\": ${question}, \"attachments\" : [{\"text\" : ${msg}}] }" https://hooks.slack.com/services/$WEBHOOK_SUFFIX
        echo "Notification sent"
#    else
#        echo "Nothing to notify"
#    fi
}

main