## 개발 환경

```
Language : Java 22
Framework : Spring boot 3.3.1
DB : H2
```

## 구현 범위에 대한 설명
```
구현1,2,3,4 API 개발 및 테스트 코드(1,2,3) 작성
```
## 테스트
```
./gradlew clean test
open build/reports/tests/test/index.html
```

## 코드 빌드 및 실행
```
./gradlew clean build bootRun 
```

## API 호출 샘플
```
구현1
curl http://localhost:8080/price/category/low-price

구현2
curl http://localhost:8080/price/brand/low-price

구현3
curl http://localhost:8080/price/search?categoryName=상의

구현4
상품 추가
curl -X POST http://localhost:8080/product/item \
 -H "Content-Type: application/json" \
 -d '{
       "id": 200,
       "brand_id": 5,
       "category_id": 10,
       "price": 25000
     }'

상품 수정
curl -X PUT http://localhost:8080/product/item \
 -H "Content-Type: application/json" \
 -d '{
       "id": 30,
       "brand_id": 5,
       "category_id": 10,
       "price": 25000
     }'

상품 삭제
curl -X DELETE http://localhost:8080/product/item/5

브랜드 추가
curl -X POST http://localhost:8080/product/brand \
 -H "Content-Type: application/json" \
 -d '{
       "id": 200,
       "name": "Z"
     }'

브랜드 수정
curl -X PUT http://localhost:8080/product/brand \
 -H "Content-Type: application/json" \
 -d '{
       "id": 200,
       "name": "ZZ"
     }'

브랜드 삭제
curl -X DELETE http://localhost:8080/product/brand/200
```


