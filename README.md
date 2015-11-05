KSPay 거래내역통보 수신 모듈
========================================

kspay-receiver는 [KSNET](http://www.ksnet.co.kr)에서 제공하는 [KSPay](http://pg.ksnet.co.kr)의 거래내역통보를 수신하는 모듈입니다.

빌드
----------------------------------------

빌드는 Maven으로 수행합니다.

```
mvn package
```

소스 Jar 파일은 기본으로 생성하며, Javadoc Jar 파일을 생성하기 위해서는 `javadoc` 프로파일을 선택하여 빌드하면 됩니다.

```
mvn package -P javadoc
```
