## 🔀 Git-Flow
- 각자의 feature branch에서 작업한 후, develop branch로 merge합니다.
- {브랜치 종류}/{이슈 번호}/{대표적인 내용 or 기능 도메인명} 순으로 작명합니다.
- `ex) feat/3/login`

## 💬 Commit Convention
Commit Convention을 따르는 것을 지향하되 유동적으로 변경하셔도 됩니다.

|작업 태그|설명|
|------|---|
|`feat`|새로운 기능 추가 / 일부 코드 추가 / 일부 코드 수정 (리팩토링과 구분) / 디자인 요소 수정|
|`fix`|버그 수정|
|`refactor`|코드 리팩토링|
|`style`|코드 의미에 영향을 주지 않는 변경사항 (코드 포맷팅, 오타 수정, 변수명 변경, 에셋 추가)|
|`chore`|빌드 부분 혹은 패키지 매니저 수정 사항 / 파일 이름 변경 및 위치 변경 / 파일 삭제|
|`docs`|문서 추가 및 수정|
|`rename`|패키지 혹은 폴더명, 클래스명 수정 (단독으로 시행하였을 시)|
|`remove`|패키지 혹은 폴더, 클래스를 삭제하였을 때 (단독으로 시행하였을 시)|

- `ex) [feat] : 로그인 기능 구현`

[//]: # (## Deploy)

[//]: # (Ec2 + Github Actions + Docker + Nginx - Blue/Green으로 진행합니다.)