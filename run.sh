SCRIPT_DIR=$( cd -- "$( dirname -- "${BASH_SOURCE[0]}" )" &> /dev/null && pwd )
pushd $SCRIPT_DIR

if [[ -z "$DD_TRACER_VERSION" ]]; then
    echo "Please set DD_TRACER_VERSION environment variable" 1>&2
    exit 1
fi

MAVEN_OPTS=-javaagent:$HOME/.m2/repository/com/datadoghq/dd-java-agent/$DD_TRACER_VERSION/dd-java-agent-$DD_TRACER_VERSION.jar=\
dd.api-key-file=$HOME/.dd.api.key,\
dd.civisibility.enabled=true,\
dd.civisibility.agentless.enabled=true,\
dd.env=local,\
dd.site=datad0g.com,\
dd.service=ciapp-test-java-maven-junit4 \
mvn clean verify -PintegrationTests

popd
