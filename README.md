# OpenShift Workshop App

OpenShift コンテナアプリケーション ハンズオンワークショップ用の Quarkus REST API アプリケーションです。

## 技術スタック

- Java 17
- Quarkus 3.x
- PostgreSQL (本番) / H2 (テスト)
- Flyway (DBマイグレーション)
- JUnit 5 + REST Assured (テスト)

## エンドポイント

| パス | メソッド | 説明 |
|------|----------|------|
| `/hello` | GET | 挨拶メッセージ（ConfigMapで設定可能） |
| `/info` | GET | アプリ情報・環境変数・デプロイ先環境 |
| `/notes` | GET | ノート一覧取得 |
| `/notes` | POST | ノート保存 |
| `/health` | GET | ヘルスチェック（DB接続チェック含む） |

## ローカルでのテスト実行

```bash
mvn test
```

テスト時は H2 in-memory データベースが使用されるため、PostgreSQL は不要です。

## ビルド

```bash
mvn package -DskipTests
```

## 環境変数

| 変数名 | 説明 | デフォルト値 |
|--------|------|-------------|
| `APP_GREETING` | 挨拶メッセージ | `Hello from OpenShift!` |
| `APP_ENVIRONMENT` | 環境名 (dev/prod) | `local` |
| `DB_URL` | PostgreSQL JDBC URL | `jdbc:postgresql://localhost:5432/workshop` |
| `DB_USERNAME` | DBユーザー名 | `workshop` |
| `DB_PASSWORD` | DBパスワード | `workshop` |
