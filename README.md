# demo-b-template

Insurance platform PoC, B안의 비즈니스 모노레포 템플릿. 플랫폼 코드는 GitHub Packages 아티팩트(`io.github.khkim6040.platform:*`)로 가져온다.

## 새 프로젝트 만들기

1. `gh repo create khkim6040/<이름> --template khkim6040/demo-b-template --public --clone`
2. 패키지 치환: `io.github.khkim6040.sample` → `io.github.khkim6040.<이름>` (`apps/*/src`, `libs`)
3. 레포 이름 치환: `demo-b-template` → `<이름>` (`settings.gradle.kts`, `deploy/`, 이 README)
4. 로컬 자격 증명(개발자당 1회): `~/.gradle/gradle.properties`에 `gpr.user`, `gpr.key`(`read:packages` 토큰)
5. push → `ci`. 릴리스: `deploy/overlays/dev`의 `newTag` 수정 후 `git tag api-v0.1.0 && git push origin api-v0.1.0`

## 플랫폼 버전 올리기

`gradle.properties`의 `platformVersion`과 `.github/workflows/release.yml`의 `@vX.Y.Z`를 올린다. BOM·카탈로그·스타터는 `platformVersion`을 따라오고, Dockerfile·릴리스 단계는 `@v`를 따라온다.

## 구조

- `apps/{api,admin,consumer,batch}`, `libs/domain`: Phase 1 참조 구현과 동일
- `build-logic`: 컨벤션 플러그인 복사본. 플랫폼 BOM(`libs.platform.bom`)을 적용한다. 중앙 업데이트되지 않는다
- `deploy/`: Kustomize. 전부 프로젝트 소유
- `Dockerfile`은 없다. 릴리스 워크플로가 플랫폼 레포에서 가져온다
