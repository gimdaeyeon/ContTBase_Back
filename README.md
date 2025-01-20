# ConTBase 프로젝트

## 최종 목표
1. 악보 이미지에서 가사를 추출하여 가사ppt를 자동생성해주는 사이트
2. 팀 단위로 악보를 등록하고 관리하여 콘티를 생성
3. 생성된 악보 콘티를 기반으로 ppt와 pdf 생성
4. 2,3 부터는 회원제로 운영할 계획

## 단계별 목표
1. 텍스트를 기반으로 ppt를 생성
   1. 기본적으로 텍스트 블럭간의 중간의 빈 줄(엔터)를 기준으로 슬라이드 페이지를 생성
   2. 한 슬라이드, 한 라인에 많은 입력값이 있을 경우 ppt의 텍스트박스의 크기와 사용자단에서 설정한 글씨의 크기를 계산한다.
   3. 계산한 값을 바탕으로 한 라인에 들어갈 수 있는 최대 글자겟수를 구하고 해당 값에 따라서 텍스트를 분리
2. 구분 값등으로 텍스트 위치, 색, 크기, 배경 등 조절
3. 단순 텍스트이미지에서 글자 추출
4. 악보에서 글자 추출
   1. 해당 부분은 ai 사용 고려
   2. chatGPT와 같은 ai를 사용하거나
   3. 직접 ai관련 기술을 공부하여 악보에서 글자만 추출하는 ai모델 만들기
5. 팀 단위별로 악보를 관리할 수 있는 시스템 구축
6. 월 구독의 형태로 결제 연동

2번 내용까지 구현이 완료되면 1차적으로 배포할 계획   
나머지는 순차적으로 추가하며 업데이트

## 개발환경
- BackEnd
  - java
  - Spring boot
  - Spring DataJPA
  - MYSQL
- Front
  - Vue
  - typescript
  - Nuxt

프론트의 경우 단계별로 vue -> typescript + Nuxt 로 전환할 예정   
시작부터 Nuxt로 하지 않는 이유는 마이그레이션 과정을 경험해보기 위함


기본적인 디자인 틀, 초안 같은 경우 V0 AI를 사용 할 계획
