# 재고관리 (Inventory Management)

## 기능
- SKU 추가/수정/조회
- 입/출고

## 재고를 어떻게 테이블로 구현할것인가?

크게 3가지방법이 머리에 떠올랐습니다.

### 재고 하나당 테이블상의 행(튜플) 하나 전략 (채택)
데이터베이스에 재고를 담당하는 테이블 Inventory 를 만들고, 입고를 하면 행을 추가하고, 출고를 하면 행을 삭제합니다.
예를 들어 재고가 5개 입고 되면 테이블의 행의 갯수는 5개가 증가하고, 5개가 출고되면 행의 갯수는 5개가 감소합니다.
이를 위해 재고 출고시 mysql이 지원하는 select for update skip locked 쿼리를 활용하여 출고 로직을 구현했습니다.

- 장점
    - 동시에 재고 출고가 발생해도 데드락을 예방할수 있습니다.
- 단점
    - 입/출고 해야 할 재고의 갯수가 크다면 insert/delete 부하가 있을수 있습니다. 따라서 한번에 입/출고할 양의 제한을 두거나 비동기 처리 구현이 필요할수도 있습니다.

### 카운트 속성 사용
SKU 테이블에 재고의 갯수를 나타내는 컬럼을 통해 관리합니다. 예를 들어 기본키가 1인 SKU의 재고를 2개 입고하면, 해당 행의 컬럼속성(예, count: BigINT)을 증가시킵니다.

- 장점
    - 구현이 상대적으로 간편합니다.
- 단점
    - 각각의 입/출고 마다 사유를 작성하기 힘듭니다. 사유를 기록할 로그 테이블을 생성하면 좋을것 같습니다.
    - Concurrency 문제로 입/출고시 배타락(select for update)이 필요합니다. 이는 병행성을 떨어뜨릴수 있습니다.
