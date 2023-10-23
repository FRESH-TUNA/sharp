# \#

<img src="https://img.shields.io/badge/Kotlin-0095D5?&style=for-the-badge&logo=kotlin&logoColor=white"/> <img src="https://img.shields.io/badge/Spring Boot-6DB33F?style=for-the-badge&logo=Spring Boot&logoColor=white"/> <img src="https://img.shields.io/badge/MariaDB-003545?style=for-the-badge&logo=mariaDB&logoColor=white"> <img src="https://img.shields.io/badge/SWAGGER-1DB33A?style=for-the-badge&logo=swagger&logoColor=white">

## 데모 배포 주소
- 데모 jwt 키: [링크](https://github.com/FRESH-TUNA/sharp/wiki/%EB%8D%B0%EB%AA%A8-jwt-%ED%82%A4)
- 재고관리: [링크](https://inventory.sharp.freshtuna.store/swagger-ui/index.html)

## 프로젝트 개요
오늘날의 IT 시대에 이커머스는 리테일 산업에서 필수적인 부분이 되었습니다. 하지만 이커머스를 위한 어플리케이션 개발은 복잡하고 어려울수 있으므로, 비즈니스에 어려움을 겪는 기업이 있을수 있습니다.

그래서 이커머스 비즈니스가 필요로 하는 기능들을 빠르게 도입할수 있도록 돕기위해 오픈소스 API 프로젝트를 시작했습니다.

현재 재고관리(Inventory management) 기능부터 개발중이며, 상품관리, 주문관리등의 이커머스에 필요한 기능을 추가로 개발할 예정입니다.

## 프로젝트 구성
### [api](./api)
웹 요청을 처리하기 위한 엔드포인트(어댑터)를 관리합니다. 현재 재고관리를 재공하는 1개의 APP을 제공합니다.

API 앱들엔 swagger를 통해 (접근방법: /swagger-url/index.html) API 문서를 보실수 있습니다.

- [inventory-api](./api/inventory-api) (재고 관리)

### [common](./common)
유틸, 공통으로 사용되는 클래스를 관리합니다.
- [api-common](./common/api-common) (api에 사용되는 유틸, 응답)
- [security](./common/security) (스프링 시큐리티 관련 bean, 유틸)

### [domain](./domain)
육각형 아키텍처의 핵심인 도메인과, 도메인과의 요청을 위한 인터페이스(유스케이스, 포트)를 관리합니다.

### [infrastructure](./infrastructure)
도메인에서 어댑터에 요청(outgoing)하기 위한 포트의 구현체들을 관리합니다.
- [sharp-mariadb](./infrastructure/sharp-mariadb) (mariadb 데이터베이스 접근을 위한 어댑터)

### [service](./service)
어댑터에서 도메인에 요청하기 위한 유스케이스의 구현체들을 관리합니다.

## 프로젝트 배경 지식

### REST
로이필링씨가 고안한 유연성있고 확장가능한 아키텍처 스타일을 말합니다.
대표적인 REST를 따른 프로토톨인 HTTP가 있습니다.REST의 특징은 다음과 같습니다.

- 클라이언트 서버 모델: 클라이언트(웹브라우저)와 서버를 분리합니다. 클라이언트는 응답을 처리하여 뷰로 만드는일에 서버는 요청을 처리하여 응답을 만드는데 집중할수 있다.(유연성)

- 무상태: 매 요청은 전역 상태를 가지지 않고, 전달되는 인자들을 활용해서만 진행합니다. 상태관리에 대한 책임이 분리되므로 확장성있는 설계가 용이하고, 캐싱에 유리합니다.

- 캐싱

- 계층형 아키텍처: 여러계층으로 아키텍처를 구성할수 있다. 시큐리티 관련처리를 한다던지, 캐싱처리를 한다던지, 로드밸런싱을 구성할수 있다. (유연성, 확장성)

- 유니폼 인터페이스: 요청와 응답의 통일된 양식을 의미합니다. 자원, 행위, 메시지의 형식을 조직에서 정의하고 이를 통일된 양식으로 사용하기 위해 API문서를 만들고 공유합니다. 제가 생각하는 좋은 Restful API이란 유니폼 인터페이스를 따르는 API를 말합니다.

### 객체지향 프로그래밍
프로그램을 역활을 가진 객체로 나누고, 객체간의 통신(메시지 교환)을 통해 프로그램의 목적을 달성하는 기법입니다.

각각의 객체를 설계할때 객체의 수정 사유를 하나가 될수 있도록 만들고(단일책임 원칙), 객체가 주고 받는 메시지들을 최대한 쪼개서 설계하며(인터페이스 분리원칙) 잘바뀌는 코드 보다 잘 바뀌지 않은 코드에 의존을 시켜(의존관계 역전원칙), 코드 변경/추가시 추가적인 소스의 변경을 최소화하고(개방패쇄원칙), 하위타입의 객체를 생성할때 상위타입에서 의도한 동작대로 설계하여 유지보수 및 재사용성을 높힐수 있습니다.

### 3 tier 구조
3계층 아키텍처는 애플리케이션을 프레젠테이션 계층, 비즈니스 로직 계층, 데이터 저장 계층의 세 가지 계층으로 분리하는 웹 애플리케이션 설계의 일반적인 접근 방식입니다. 애플리케이션을 각각 고유한 책임이 있는 세 개의 계층으로 명확하게 분리하므로 애플리케이션을 유지 관리하고 확장하기가 더 쉬운장점이 있습니다.
수년 동안 웹 애플리케이션 개발에 사용되어 온 잘 정립된 패턴이므로, 사용 방법을 배우고자 하는 개발자가 사용할 수 있는 리소스를 쉽게 찾을수 있는 장점이 있습니다.

3계층 아키텍처에서는 프리젠테이션 계층은 비즈니스 로직에 의존하고, 비즈니스 로직은 데이터 저장 계층에 의존하고 있습니다. 즉 우리의 어플리케이션(비즈니스코드)는 웹과 데이터베이스를 다루는 코드에 의존할 가능성이 높습니다. 웹이나 데이터베이스를 다루는 기술스택은 기술의 발전에 따라 쉽게 변화할수 있으므로 이는 의존관계 역전원칙을 위배하게 되어 유지보수를 방해할수 있습니다.

예를 들어 만약 RDB를 사용하다 NOSQL로 전환한다면, 어플리케이션의 많은 수정사항이 발생할수 있습니다. 또한 아키텍처간 의존관계에 대한 개발팀의 규칙을 정하지 않으면, 웹계층에서 데이터계층에 의존하는등의 계층이 꼬이는문제가 발생하고, 이는 테스트와 계층간 분업의 복잡도를 증가시킬수 있습니다.

### Hexagonal 아키텍처와 DDD 도입
![Hexagonal 아키텍처](./docs/hexagonal.png)

Hexagonal 아키텍처는 중심에 있는 도메인과, 비즈니스로직을 담당하는 유스케이스와 요청을 받거나(ex: 컨트롤러) 외부에 요청하는(다른 API나 데이터베이스 jdbc, nosql) 엔드포인트인 어댑터로 구성됩니다. 어댑터가 유스케이스에 요청할때와 유스케이스에서 다른 데이터베이스나 API등의 어댑터에 요청할때 인터페이스를 사용합니다.

육각형 아키텍처는 고도로 분리되어 있어 애플리케이션의 한 부분을 변경해도 다른 부분에 영향을 미치지 않습니다. 따라서 애플리케이션을 테스트하고 유지 관리하기가 더 쉬운장점이 있습니다.

Hexagonal 아키텍처의 도입은 자연스럽게 DDD를 유도하여 개발을 할수 있게 합니다. 도메인(상품관리, 진열관리, 재고관리 등등)에 집중하면서 모델링함으로써 개발자는 기술스택보다 비즈니스를 더 깊게 이해하는것이 가능합니다. 데이터베이스, 다른 API으로의 요청, 유스케이스(비즈니스로직)은 모두 도메인에 의존하도록 설계됩니다. 기술스택과 요구사항은 빠르게 변화하지만, 도메인은 가장 중심이 되고 쉽게 변하지 않으므로 원할한 유지보수와 기능개발을 돕습니다.

유스케이스는 모두 인터페이스 입니다. 어댑터가 유스케이스를 요청할때, 혹은 도메인에서 어댑터에 요청할때 모두 도메인에서 지정하는 인터페이스를 통해 요청하므로 모듈간 결합도를 낮출수 있습니다. 이는 어댑터의 기술스택의 교체(ex: rdb -> nosql)를 용이하게 하여 지속가능한 개발을 진행할수 있게 도와줍니다.

Hexagonal 아키텍처를 도입하며 인터페이스에 의존하면서 의존관계 역전원칙을 지킬수 있고, 개방폐쇄원칙을 지켜 유연한 객체지향 설계를 할수 있다고 생각합니다.
저는 인터페이스 분리 원칙을 준수하려고 노력하여 필요없는 메소드를 구현하기 않게 하여 재사용성을 높히고, 1인터페이스 1클래스구현을 통해 단일책임원칙을 지켜 재사용성을 높히고자 노력했습니다. 

이커머스 플랫폼은 결제 게이트웨이(PG), 배송업체, 재고 관리 시스템 등 다양한 타사 시스템과 통합할 수 있어야 합니다. 육각형 아키텍처는 모듈(구현체)의 교체가 용이하므로 이커머스 플랫폼의 지속적인 개발에 도움을 줄수 있습니다.

### 도커(컨테이너 가상화)를 이용한 프로젝트 배포
저는 프로젝트의 소개 및 공부 목적으로 컨테이너 가상화 서비스인 도커를 사용하여 AWS 환경에 프로젝트를 배포했습니다.

가상화 기술을 사용하면 서버들이 완전히 격리되어 있기 때문에 한 서버가 침입당해도 다른 서버에 영향을 미치지 않습니다. 이를 통해 시스템의 보안을 강화할 수 있습니다. 두번째로 각 서버의 리소스를 더욱 효율적으로 관리할 수 있습니다. 예를 들어, 메일서버와 웹서버를 하나의 OS에서 구축하는 경우, 서버 간에 리소스 충돌이 발생할 수 있습니다. 하지만 가상화를 사용하면 각 서버가 필요로하는 리소스(CPU, 메모리)를 조정하거나 할당할 수 있는 장점이 있습니다. 가상화 기술은 스냅샷 기능을 제공하여 서버를 다른 물리적인 서버로 쉽게 이동할 수 있어 서버 유지보수나 장애 대응 등에서 유용하게 사용됩니다. 이를 통해 인적 오류를 줄일 수 있습니다.

가상화를 사용하는 방법으로는 하이퍼바이저, 소프트웨어 설치, 컨테이너 방식이 있습니다. 컨테이너 방식은 가상 머신 생성 시 스냅샷을 복사하는 대신 이미지를 참조하기 때문에 차지하는 용량이 적습니다. 또한 리눅스 네임스페이스 기능을 사용하여 OS 자체적으로 격리를 구현하기 때문에 성능이 우수하며 마이크로서비스 배포에도 적합합니다.

빠르고 효율적인 배포를 위해서는 도커 이미지 용량의 최적화가 필요합니다. 저는 이를 위해 멀티스테이지 빌드 기법을 사용했고, gradle 이미지에서 어플리케이션을 빌드하여 jre 이미지에 복사하는 방식을 사용하여 이미지의 용량을 줄이기 위해 노력했습니다.

```
# BUILDER
FROM gradle:7.6.1-jdk17 AS builder
ADD ./ /sharp
WORKDIR /sharp
RUN gradle :api:inventory-api:bootJar --no-daemon

# RUNNING
FROM azul/zulu-openjdk-alpine:17-jre
WORKDIR /sharp
COPY --from=builder /sharp/api/inventory-api/build/libs/inventory-api-0.0.1-SNAPSHOT.jar inventory.jar
CMD java -Dspring.profiles.active=default -Dserver.port=$PORT $JAVA_OPTS -Dspring.config.location=application.yaml -jar inventory.jar
```
### 어떻게 JWT를 처리할것인가?
프로젝트에 요청을 하기 위해서는 판매자 정보를 가지고 있는 JWT 토큰을 같이 보내줘야 합니다. 저는 JWT 토큰을 어떻게 처리하여 어플리케이션에 공급할지에 대한 고민이 있었습니다.
처음 배포한 버전에서는 스프링 시큐리티의 도움을 받아서 해결을 했는데 몇가지 단점이 있었습니다. 

일단 로딩이 좀더 긴시간이 필요해 무거웠고, 스프링시큐리티를 공부하기 위한 러닝커브와 그로인한 협업의 어려움이 있겠다는 단점이 있었습니다.
그리고 jwt토큰의 값을 검증하여 어플리케이션에 제공하기에는 스프링시큐리티는 너무 많은 기능을 제공하고 있습니다.
그래서 스프링 web에서 제공하는 인터셉터와 리졸버를 활용했습니다. 

인터셉터에서는 토큰을 검증후 토큰에 있는 판매자 id를 sharp 내부에서 사용하는 id로 교체해주는 역활을 합니다. id를 교체하는 이유는 다음과 같습니다.
id를 교체하면서 데이터베이스에 쿼리가 1번 발생하는 단점이 있지만, 인증시스템과 sharp시스템과의 id를 분리함으로써, 향후 인증시스템의 id 타입, 길이등의 정책변경시 유연하게 대응하고자 함이 있습니다.

리졸버에서는 교체된 id를 어노테이션을 통해 컨트롤러에 주입해줍니다. 사실 리졸버 하나만 있어도 토큰을 검증하여 어플리케이션에 넘겨줄수 있지만, 검증하는 책임과 어플리케이션에 주입하는 책임을 분리하여 SRP원칙을 지키도록 하였습니다.

```java
// 어노테이션
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.VALUE_PARAMETER)
annotation class SharpIDInjection()
```

```java
// 리졸버
@Component
class SharpIDResolver : HandlerMethodArgumentResolver{

    override fun supportsParameter(parameter:MethodParameter):Boolean{
        return parameter.hasParameterAnnotation(SharpIDInjection::class.java)
    }

    ...
}
```

```java
@Tag(name = "재고 입고")
@RestController
class InventoryInController(
    private val useCase: InventoryInUseCase
) : InventoryInSpec {

    // 리졸버와 @SharpIDInjection 어노테이션을 통해 내부 ID 주입
    @PostMapping(Url.EXTERNAL.SKU_ID_INVENTORY_IN)
    override fun new(@RequestBody request: InventoryRequest,
                     @Parameter(description = "입/출고 SKU 아이디") @PathVariable id: Long,
                     @SharpIDInjection sellerID: SharpID): BasicResponse {

        val skuId = SharpID(id)

        useCase.new(request.toCommandOf(skuId), sellerID)

        return MessageResponse.OK
    }
}
```
