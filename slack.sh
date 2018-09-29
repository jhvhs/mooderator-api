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
    questionId=$(load_questions)
    payload=$(load_stats)

    question=$(echo ${payload} | jq '.[0] | .question' | tr -d "\"")
    answers=$(echo ${payload} | jq '.[] | .answer' | tr "\n" " " | tr -d "\"")
    results=$(echo ${payload} | jq '.[] | .results' | tr "\n" " ")

    IFS=' ' read -r -a resultsArray <<< ${results}
    IFS=' ' read -r -a answersArray <<< ${answers}

    content="${answersArray[0]} ${resultsArray[0]} - ${answersArray[1]} ${resultsArray[1]} - ${answersArray[2]} ${resultsArray[2]}"

    curl -X POST -H 'Content-type: application/json' --data "{\"text\":\"${question}\", \"attachments\" : [{\"text\" : \"${content}\"}] }" https://hooks.slack.com/services/$WEBHOOK_SUFFIX
}

main