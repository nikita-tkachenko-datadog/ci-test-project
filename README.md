### Running with Datadog Agent

```
DD_AGENT_VERSION=... ./run-agentFUL.sh
```

where `DD_TRACER_VERSION` is the version of the tracer you want to use (e.g. `1.11.0`, or `1.12x.0-SNAPSHOT`).

----
 
### Running with debug logs

Set `DD_TRACE_DEBUG` property:

```
DD_TRACE_DEBUG=true DD_TRACER_VERSION=... ./run-agentFUL.sh
```

----
 
### Running in Agentless mode

Set the `DD_API_KEY` environment and use `run.sh` script:

```
DD_API_KEY=... DD_TRACER_VERSION=... ./run.sh
```

----

### Running in debug mode

Use `run-debug.sh` script

```
DD_TRACER_VERSION=... ./run-debug.sh
```

----

### Verifying results

The results will be reported to [staging](https://dd.datad0g.com/ci/test-runs?query=test_level%3Atest%20%40test.service%3Aciapp-test-java-maven-junit4) 
with service name `ciapp-test-java-maven-junit4`.
