# 🖍 E2I3 Team Project
네터스 23-1 팀 프로젝트 백엔드 개발

## Skill
- Back-end : Spring Boot, Spring Security, Spring Data JPA
- DevOps : MySQL, H2Databases
- Deployment : AWS

## RDB Table
![e2i3 데이터베이스 다이어그램](https://user-images.githubusercontent.com/46291115/236372611-366ad6bd-878c-4d09-a76a-5aaabd81ff41.png)

## API
BOARD
|method|URL|
|------|-----------------------------------|
|GET|/api/board/detail/{id}|
|GET|/api/board/list|
|POST|/api/board|
|DELETE|/api/board/{id}|
|PUT|/api/board/{id}|

COMMENT
|method|URL|
|------|-----------------------------------|
|GET|/api/comment/{boardID}|
|GET|/api/comment/{commentID}|
|POST|/api/comment/{boardID}|
|PUT|/api/comment/{commentID}|
|DELETE|/api/comment/{commentID}|

HEART
|method|URL|
|------|-----------------------------------|
|GET|/api/heart/{boardID}/{email}|
|POST|/api/heart/{boardID}/{email}|
|DELETE|/api/heart/{boardID}/{email}|

LOGIN
|method|URL|
|------|-----------------------------------|
|POST|/api/login/login|
|POST|/api/login/save|
|POST|/api/login/logout|

MEMBER
|method|URL|
|------|-----------------------------------|
|PUT|/api/member/{id}|
|DELETE|/api/member/{id}|
