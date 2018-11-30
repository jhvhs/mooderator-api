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
    day_of_the_week=$(date +%-u)
    day=$(date -v-1d +%F)

    if [[ day_of_the_week -eq 1 ]]
    then
        day=$(date -v-3d +%F)
    fi

    echo ${day}

    payload=$(load_daily_stats)

    question=$(echo ${payload} | jq '.[0] | .question')

    filter="map(select(.day == \"${day}\")) | map(\"\(.answer) \(.results)\") | join(\" - \")"

    msg=$(echo ${payload} | jq "${filter}")

    if [[ ${msg//\"} == '' ]]
    then
        msg="No votes entered :sleeping: :zzz: "
    fi

    request_body="\"${msg//\"}\""
    links="{\"color\": \"#2eb886\", \"text\" : \"Stats available at http://mooderator-web.cfapps.io/stats\"}"
    content="{\"color\": \"#2eb886\", \"title\" : \"Results from ${day}\", \"text\" : ${request_body}}"

    curl -X POST -H 'Content-type: application/json' \
     --data "{\"text\": ${question}, \"attachments\" : [${content}] }" \
     https://hooks.slack.com/services/$WEBHOOK_SUFFIX

    echo "Notification sent"
}

main