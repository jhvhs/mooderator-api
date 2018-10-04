#!/usr/bin/env bash

set -euox pipefail

load_questions() {
    rep=$(curl 'http://mooderator-api.cfapps.io/questions/latest' -H 'Content-Type: application/json' | jq -r '.id')
    echo ${rep}
}

load_stats() {
    stats=$(curl 'http://mooderator-api.cfapps.io/results/statistics' -H 'Content-Type: application/json')
    echo ${stats}
}

main() {
#    questionId=$(load_questions)
    payload=$(load_stats)

    question=$(echo ${payload} | jq '.[0] | .question')

    msg=$(echo ${payload} | jq 'map("\(.answer) \(.results)") | join(" - ")' )

    curl -X POST -H 'Content-type: application/json' --data "{\"text\": ${question}, \"attachments\" : [{\"text\" : ${msg}}] }" https://hooks.slack.com/services/$WEBHOOK_SUFFIX
}

main