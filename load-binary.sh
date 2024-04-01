#!/bin/bash

# Unless explicitly stated otherwise all files in this repository are licensed under the the Apache License Version 2.0.
# This product includes software developed at Datadog (https://www.datadoghq.com/).
# Copyright 2021 Datadog, Inc.


##########################################################################################
# The purpose of this script is to download the latest development version of a component.
#
# Binaries sources:
# 
# * C++:    Circle CI      (needs auth)
# * .NET:   windows.net
# * Golang: github repo
# * Java:   Circle CI      (needs auth)
# * NodeJS: github repo
# * PHP:    Circle CI      (needs auth)
# * Python: github actions
# * Ruby:   github repo
#
##########################################################################################

set -eu

get_circleci_artifact() {

    SLUG=$1
    WORKFLOW_NAME=$2
    JOB_NAME=$3
    ARTIFACT_PATTERN=$4
    BRANCH_NAME=$5

    echo "CircleCI: https://app.circleci.com/pipelines/$SLUG?branch=$BRANCH_NAME"
    PIPELINES=$(curl --silent "https://circleci.com/api/v2/project/$SLUG/pipeline?branch=$BRANCH_NAME" -H "Circle-Token: $CIRCLECI_TOKEN" | sed -e 's:\\n: :g' -e 's:\\r: :g')

    for i in {0..30}; do
        PIPELINE_ID=$(echo $PIPELINES| jq -r ".items[$i].id")
        PIPELINE_NUMBER=$(echo $PIPELINES | jq -r ".items[$i].number")

        echo "Trying pipeline #$i $PIPELINE_NUMBER/$PIPELINE_ID: https://circleci.com/api/v2/pipeline/$PIPELINE_ID/workflow"
        WORKFLOWS=$(curl --silent https://circleci.com/api/v2/pipeline/$PIPELINE_ID/workflow -H "Circle-Token: $CIRCLECI_TOKEN" | sed -e 's:\\n: :g' -e 's:\\r: :g')

        QUERY=".items[] | select(.name == \"$WORKFLOW_NAME\") | .id"
        WORKFLOW_IDS=$(echo $WORKFLOWS | jq -r "$QUERY")

        if [ ! -z "$WORKFLOW_IDS" ]; then

            for WORKFLOW_ID in $WORKFLOW_IDS; do
                echo "=> https://app.circleci.com/pipelines/$SLUG/$PIPELINE_NUMBER/workflows/$WORKFLOW_ID"

                JOBS=$(curl --silent https://circleci.com/api/v2/workflow/$WORKFLOW_ID/job -H "Circle-Token: $CIRCLECI_TOKEN" | sed -e 's:\\n: :g' -e 's:\\r: :g')

                QUERY=".items[] | select(.name == \"$JOB_NAME\" and .status==\"success\")"
                JOB=$(echo $JOBS | jq "$QUERY")

                if [ ! -z "$JOB" ]; then
                    break
                fi
            done

            if [ ! -z "$JOB" ]; then
                break
            fi
        fi
    done

    if [ -z "$JOB" ]; then
        echo "Oooops, I did not found any successful pipeline"
        exit 1
    fi

    JOB_NUMBER=$(echo $JOB | jq -r ".job_number")
    JOB_ID=$(echo $JOB | jq -r ".id")

    echo "Job number/ID: $JOB_NUMBER/$JOB_ID"
    echo "Job URL: https://app.circleci.com/pipelines/$SLUG/$PIPELINE_NUMBER/workflows/$WORKFLOW_ID/jobs/$JOB_NUMBER"

    ARTIFACTS=$(curl --silent https://circleci.com/api/v2/project/$SLUG/$JOB_NUMBER/artifacts -H "Circle-Token: $CIRCLECI_TOKEN" | sed -e 's:\\n: :g' -e 's:\\r: :g')
    QUERY=".items[] | select(.path | test(\"$ARTIFACT_PATTERN\"))"
    ARTIFACT_URL=$(echo $ARTIFACTS | jq -r "$QUERY | .url")
    ARTIFACT_NAME=$(echo $ARTIFACTS | jq -r "$QUERY | .path" | sed -E 's/libs\///')
    echo "Artifact URL: $ARTIFACT_URL"
    echo "Artifact name: $ARTIFACT_NAME"
    echo "Downloading artifact..."

    curl --silent -L $ARTIFACT_URL --output $ARTIFACT_NAME
}

rm -rf *.jar
OWNER=DataDog
REPO=dd-trace-java
WORKFLOW_NAME=build_test

BRANCH=${1:-master}
echo "Branch is $BRANCH"

cd $DD_TRACER_FOLDER

get_circleci_artifact "gh/DataDog/dd-trace-java" $WORKFLOW_NAME "build_lib" "libs/dd-java-agent-.*.jar" $BRANCH

mv dd-java-agent-*.jar dd-java-agent.jar
