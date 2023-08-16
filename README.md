# wanted-pre-onboarding-backend

지원자 : 조성재

애플리케이션 실행 방법 - (Postman 사용)

1. application.properties의 spring.datasource.url을 사용하고자 하는 DB주소를 입력해준다. (MySQL 기준)
   ex_ spring.datasource.url=jdbc:mysql://{DB 엔드포인트}:{포트넘버}/{DB이름}

2. DB의 username과 password를 입력해준다.
   ex_ spring.datasource.username={DB 생성 시 설정한 이름}
       spring.datasource.password={DB 생성 시 설정한 비밀번호}

3. 어플리케이션을 실행해준다.

4. 회원가입
    - POST Mapping 사용, Endpont : localhost:8080/api/user/signup 입력
    - Form Data 타입으로하여 Key 값 = email, password 과 Value 값 = 설정하고자 하는 email과 password 입력하여 회원가입을 진행한다.
    - 성공 시 임의로 넣어놓은 HTML 화면으로 이동, 실패 시에는 해당 문제가 되는 message를 표출
    - email 기입 시에는 올바른 email 형식을 기입하고, 비밀번호는 8자 이상의 비밀번호를 사용
    - ex_ test@test.com / 12345678

5. 로그인
    - POST Mapping 사용, Endpont : localhost:8080/api/user/login 입력
    - JSON 형식으로 email과 password를 입력하여 login (주의사항 : 비밀번호가 암호화되어 DB에 저장되어, 로그인 시 암호화 된 비밀번호를 입력할 것) 호출
    - 로그인 성공 시 '로그인 성공' message와 함께 Header에 토큰 부여
    - 로그인 실패 시 해당 문제가 되는 message 표출

6. 게시물 작성
    - POST Mapping 사용, Endpont : localhost:8080/api/notice 입력
    - 로그인 시 받은 jwt 토큰을 Header에 담아 Key 값 = Authorization 을 입력하고 Value 값에 로그인 시 부여받은 토큰 번호를 입력한다.
    - JSON 형식으로 title와 content를 입력하여 호출
    - 성공 시 해당 작성된 게시물 표출

7. 전체 게시물 조회
    - GET Mapping 사용, Endpont : localhost:8080/api/notices 입력하여 전체 게시물 호출
    - 작성된 게시물이 시간순으로 조회되어 표출

8. 특정 게시물 조회
    - GET Mapping 사용, Endpont : localhost:8080/api/notice/{게시물 id} 입력하여 특정 게시물 호출
    - 입력한 id의 게시물이 조회되어 표출

9. 게시물 수정
    - PUT Mapping 사용, Endpont : localhost:8080/api/notice/{게시물 id} 입력
    - 로그인 시 받은 jwt 토큰을 Header에 담아 Key 값 = Authorization 을 입력하고 Value 값에 로그인 시 부여받은 토큰 번호를 입력한다.
    - JSON 형식으로 id와 title, content를 key 값으로 value 값에 수정하고자 하는 내용으로 수정한 뒤 호출
    - 수정된 게시물 표출

10. 게시물 삭제
    - DELETE Mapping 사용, Endpont : localhost:8080/api/notice/{게시물 id} 입력
    - 로그인 시 받은 jwt 토큰을 Header에 담아 Key 값 = Authorization 을 입력하고 Value 값에 로그인 시 부여받은 토큰 번호를 입력한다.
    - JSON 형식으로 email 값을 key 값으로 하여 value 값에 로그인 시 사용하는 email을 입력하여 호출
    - 게시물 삭제 성공 시 '삭제 성공' message 표출
    - 게시물 삭제 실패 시 '삭제 실패' message 표출

데이터베이스 테이블 구조

1. user Table

|id|email|password|
|-----|---|---|
|1|user1|암호화된 password|
|2|user2|암호화된 password|
|3|user3|암호화된 password|

2. notice Table

|id|created_at|modified_at|title|contents|user_id|
|-----|---|---|---|---|---|
|1|2023-08-15 21:14:10.853634|2023-08-15 21:14:10.853634|title_1|content_1|게시물 작성 user id|
|2|2023-08-15 21:14:10.853634|2023-08-15 21:14:10.853634|title_2|content_2|게시물 작성 user id|
|3|2023-08-15 21:14:10.853634|2023-08-15 21:14:10.853634|title_3|content_3|게시물 작성 user id|

구현한 API의 동작 데모 영상 링크

구현 방법 및 이유 간략 설명

API 명세
