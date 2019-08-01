#!/usr/bin/env bash

set -euo pipefail

load_daily_stats() {
    stats=$(curl "${API_URL}"'/results/daily-statistics' -H 'Content-Type: application/json')
    echo ${stats}
}

previous_date() {
    day_of_the_week=$(date +'%-w')
    yesterday=$(date -I --date='yesterday')

    if [[ day_of_the_week -eq 1 ]]
    then
        yesterday=$(date -I --date='last Fri')
    fi

    printf "%s" "$yesterday"
}

main() {
    local payload question filter msg yesterday request_body content stats

    payload="$(load_daily_stats)"
    yesterday="$(previous_date)"
    question="$(echo ${payload} | jq '.[0] | .question')"
    filter="map(select(.day == \"${yesterday}\")) | map(\"\(.answer) \(.results)\") | join(\" - \")"

    msg=$(echo ${payload} | jq "${filter}")

    if [[ ${msg//\"} == '' ]]
    then
        msg="No votes entered :sleeping: :zzz: "
    fi

    request_body="\"${msg//\"}\""
    content="{\"color\": \"#2eb886\", \"title\" : \"Results from ${yesterday}\", \"text\" : ${request_body}}"
    stats="{\"color\": \"#4286f4\", \"title\" : \"Overall Stats\", \"text\" : \"${WEB_URL}/stats\"}"

    curl -X POST -H 'Content-type: application/json' \
     --data "{\"text\": ${question}, \"attachments\" : [${content}, ${stats}]}" \
     "https://hooks.slack.com/services/$WEBHOOK_SUFFIX"

    echo "Notification sent"
}

main
