# 사용기술
- JAVA 17, Spring Boot 2.3, MyBatis, MySQL, Redis

---
# 성능테스트 툴
- Python 3.9, Locust
- 성능테스트 툴 (https://docs.locust.io/en/stable/index.html)
  - 로커스트 설치. pip3 install locust
  - 성능 테스트 시나리오에 따른 .py 파일 작성
  - locust -f BoardServer.py 파일 실행
  - http://localhost:8089/ 접속 후 성능 테스트 진행
- 사전준비
  - 게시글 카테고리 10개 생성
  - 게시글 10만개 생성
  - 게시글 검색 API 각 시나리오 테스트별 진행
- 목표: 서버의 지표 CPU, RAM, DISK 와 목표 TPS 확인
- 시나리오
  - STRESS 테스트: 500명의 동시 사용자가 초당 50번을 호출하여 분당(5분) 사용자를 50씩 늘려 서버의 지표를 확인
  - Endurance 테스트: 100명의 동시 사용자가 초당 100번을 호출하였을때 10분동안 서버의 지표를 확인
  - PEAK 테스트: 100명의 동시 사용자가 초당 50번씩 호출하다 1분에 1000명으로 사용자를 한번에 늘려 서버의 지표를 확인
---
# 프로그램 주요 기능
- 회원
  - 가입, 탈퇴
  - 아이디 및 닉네임 중복체크
  - 비밀번호 암호화
  - 로그인, 로그아웃
- 게시판
  - 카테고리 관리
    - 추가, 삭제, 수정
  - 게시글 관리
    - 게시글 & 파일 추가, 삭제, 수정, 조회
    - 유저 정보, 게시글 제목, 게시글 내용 등
  - 게시글 검색 기능
    - 작성 유정 아이디
    - 게시글 제목, 게시글 내용 등을 통해 검색
    - 태크 작성 및 조회 기능
  - 댓글 작성 기능
- 어드민
  - 공지글 추가 기능

---
# ERD(Entity Relationship Diagram)
![image](https://github.com/ccommit-dev/Board-Server/assets/77635521/7fb0ec6b-1317-4911-9315-067244a8dd9e)

---
# 시퀀스
- 게시글 등록 시퀀스
![image](https://github.com/ccommit-dev/Board-Server/assets/77635521/7791db61-97cc-4ad8-a90c-2e0a572049c5)

- 게시글 검색 시퀀스
![image](https://github.com/ccommit-dev/Board-Server/assets/77635521/c5f228fd-ca8f-4144-a407-30e2647f9159)

---
# 아키텍처
![image](https://github.com/ccommit-dev/Board-Server/assets/77635521/62e053a4-51a4-4387-90c4-f5e450441f2f)



