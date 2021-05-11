# 에이치디정션 백엔드 개발자 채용 온라인 과제

## 에이치디정션
* [회사 홈페이지](https://www.hdjunction.co.kr/)

## 과제 소개
* 환자의 진료 정보를 관리할 수 있는 API 서비스

## 사용법

### 환자 정보 API (`/patients`)
* TODO(구현 예정): `PatientController` URI 및 설명 작성

### 환자 방문(진료) 정보 API (`/visit`)
* TODO(구현 예정): `VisitController` URI 및 설명 작성

## 개발환경
* OS
  * `Mac OS Big Sur` - `11.3.1(20E241)`
* 개발툴
  * `IntelliJ IDEA Ultimate 2021.1.1`
* DB
  * `H2` - `1.4.200`
  * 사용자명은 `sa`, 비밀번호는 없음
    * Mac `brew` 패키지로 설치
    * 최초에 접속하면 DB 파일을 찾지 못함
      * 따라서 최초에 `JDBC URL`을 `jdbc:h2:~/hdjunction`로 설정하여 접속 (파일로 직접 접근)
      * `hdjunction.mv.db` 파일 생성 확인
      * 다음부터 `jdbc:h2:tcp://localhost/~/hdjunction`으로 접속
        * `TCP 소켓`을 통해 접속해야 외부에서 동시에 접근했을 때 오류가 발생하지 않음
* 프로젝트
  * 언어
    * Java `zulu jdk 11`
  * 프레임워크 & 라이브러리
    * Spring Boot `2.4.5`
      * Spring Web
      * Spring Data JPA
      * Spring Rest Docs (Option)
    * H2 Database
  * 설정
    * `Jar` 패키징 설정
    * `application.yml`에서 `H2` 활성화, `datasource` 설정
      * `prod`, `dev` 프로필 분리 설정
        * 개발환경에서 로컬 `H2`를 사용하도록 변경 (기본은 `prod`)
        * IDEA에서 `-Dspring.profiles.active=dev` VM 옵션

## 요구사항 정리

### 진행 방식
* [과제 설명](https://www.notion.so/2b1b494f9ad140668438f26c1de5379f)
* 첨부된 내용(과제 설명)을 분석, 구현할 기능 정리
* 프로젝트를 생성, `java`, `Spring Boot` 등을 사용하여 기능 구현
  * 구현 요구사항 가이드 준수
    * 형상관리는 `Git` 사용
      * 가급적 단계별로 `commit` 할 것
      * 브랜치는 최대한 삭제하지 않고 보관할 것
    * `.md` 파일에 프로젝트 소개 기술
    * 개발 언어 `java`, `kotlin` 중 택일
    * 상황에 따라 **spring-restdocs** 적용
* 제출 방식에 따라 과제 제출
  * 깃허브 원격 저장소에 보관, 저장소 `URL`을 메일 전송
    * `recruit@hdjunction.co.kr`

### 구현할 기능 목록
* 기본
  * 환자 정보 등록
    * 환자 등록번호는 병원별로 중복되지 않도록 서버에서 생성
  * 환자 정보 수정
  * 환자 정보 삭제
  * 환자 정보 조회
    * 환자 `ID`를 통해 조회
    * 환자 엔티티의 모든 속성(프로퍼티), 내원 정보를 목록으로 함께 조회
  * 전체 환자 목록 조회
* 환자 목록 조회 API 확장
  * 동적 검색 조건
    * 환자이름, 환자 등록번호, 생년월일로 검색할 수 있는 API로 수정
    * `QueryDSL`을 사용하여 구현
  * 페이징
    * `pageSize`(한 번에 조회하는 최대 항목 수), `pageNo`(페이지 번호 - 1부터 시작)을 기준으로 페이징 처리
    * 동적 검색 조건 구현 방식에 따라 구현

### 엔티티
* 주의사항
  * `javax.persistence.Entity` 애너테이션으로 엔티티 정의
  * `@OneToMany`, `@ManyToOne` 등 관계 설정
  * 엔티티 구현 중 필요하다면 자유롭게 컬럼 추가
  * `코드`, `코드그룹` 엔티티는 적절히 구현
    
#### `Hospital` (병원)
* 병원 아이디
* 병원명
* 요양기관 번호
* 병원장명

#### `Patient` (환자)
* 환자 아이디
* 병원 아이디
* 환자명
* 환자등록 번호
* 성별코드
* 생년월일
* 휴대전화

#### `Visit` (환자방문)
* 환자방문 아이디
* 병원 아이디
* 환자 아이디
* 접수일시
* 방문상태코드

#### `CodeGroup` (코드그룹)
* 코드 그룹
* 코드그룹명
* 설명

#### `Code` (코드)
* 코드 그룹
* 코드
* 코드명

### 리포지토리
* `Repository`는 `JpaRepository` 상속하여 생성
* 기본적인 CRUD 기능 구현
  * `PatientRepository`
  * `VisitRepository`
    
### 컨트롤러
* 엔드포인트는 `Restful API` 또는 `GraphQL` 사용해 구현
* 기본적인 CRUD API 구현
  * `PatientController`
  * `VisitController`
