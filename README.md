# Issue #1016

- https://github.com/smallrye/smallrye-reactive-messaging/issues/1016

```bash
mvn quarkus:dev
curl -iX POST http://localhost:8080/test
```

## Expected
```
2021-02-19 09:38:52,973 INFO  [org.acm.TestResource] (executor-thread-1) tx=TransactionImple < ac, BasicAction: 0:ffff0a1f0d95:cfbf:602f791c:0 status: ActionStatus.RUNNING >
2021-02-19 09:38:52,981 INFO  [org.acm.TestConnector] (executor-thread-1) tx=TransactionImple < ac, BasicAction: 0:ffff0a1f0d95:cfbf:602f791c:0 status: ActionStatus.RUNNING >
```

## Actual
```
2021-02-19 09:38:52,973 INFO  [org.acm.TestResource] (executor-thread-1) tx=TransactionImple < ac, BasicAction: 0:ffff0a1f0d95:cfbf:602f791c:0 status: ActionStatus.RUNNING >
2021-02-19 09:38:52,981 INFO  [org.acm.TestConnector] (executor-thread-1) tx=null
```