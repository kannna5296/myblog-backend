# myblog-backend


## MEMO

* BctyptPasswordEncoder (org.springframework.security.crypto.bcrypt
* ハッシュかしてない適当パスワードだと付き合わせるまでもなくfalseで弾く

```
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        if (rawPassword == null) {
            throw new IllegalArgumentException("rawPassword cannot be null");
        } else if (encodedPassword != null && encodedPassword.length() != 0) {
            if (!this.BCRYPT_PATTERN.matcher(encodedPassword).matches()) {
                this.logger.warn("Encoded password does not look like BCrypt");
                return false;
            } else {
                return BCrypt.checkpw(rawPassword.toString(), encodedPassword);
            }
        } else {
            this.logger.warn("Empty encoded password");
            return false;
        }
    }
```


* ログインした時のREsponseに'Set-Cookie: XSRF-TOKEN=abcdef123456789'的なやつがない。。何したらいいんだっけ

```ChatGPT
CSRFトークンが含まれるHTTPレスポンスとは、初回のページ読み込みや認証後のレスポンスを指します。このレスポンスで、Spring BootはCSRFトークンをCookieにセットして、フロントエンド側に渡します。

具体的には、ユーザーが初めてアプリにアクセスする際や、ログイン後などにSpring BootがCSRFトークンを生成して、Set-Cookieヘッダーを使ってブラウザに送り、そのトークンをクライアント側で利用できるようにします。

流れの詳細
ユーザーが最初にアプリにアクセス

例えば、ユーザーがフロントエンドのVue.jsアプリケーションを最初に開くと、ブラウザがバックエンドのSpring Bootサーバーに対して初回リクエストを送信します。
Spring BootがCSRFトークンを生成してレスポンス

Spring Securityは、この初回リクエストのレスポンスでCSRFトークンを生成し、レスポンスヘッダーの Set-Cookie を使ってCSRFトークンをブラウザに送ります。このとき、トークンが XSRF-TOKEN という名前でクッキーに保存されます。
http
コードをコピーする
HTTP/1.1 200 OK
Set-Cookie: XSRF-TOKEN=abcdef123456789; Path=/; HttpOnly
Content-Type: application/json
ブラウザがCSRFトークンをクッキーに保存

このXSRF-TOKENというクッキーはブラウザに保存され、Vue.js側ではこのクッキーからCSRFトークンを読み取ることができます。
Vue.jsからの後続リクエスト

次に、ユーザーがフォーム送信やAPIリクエストを行うと、Vue.js側でこのクッキーからCSRFトークンを取得し、リクエストヘッダーに含めてサーバーに送信します。
javascript
コードをコピーする
axios.post('/api/some-endpoint', {
  data: 'someData'
}, {
  headers: {
    'X-XSRF-TOKEN': getCsrfToken()  // ここでクッキーから取得したCSRFトークンをセット
  }
});
どのタイミングでレスポンスが送られるか
最初のページロード：アプリを初めて開いたとき
ログイン成功時：ユーザーがログインして、サーバーが認証情報を返すとき
その他認証が必要なリクエストのレスポンス：例えば、特定の保護されたAPIエンドポイントにアクセスし、サーバーがCSRF保護を有効にしている場合
CSRFトークンは、これらのタイミングでセットされるHTTPレスポンスの一部としてブラウザに送信されます。そのため、Vue.jsアプリケーションはこのクッキーを利用してリクエストにCSRFトークンを含めることが可能になります。
```
* ![スクリーンショット 2024-10-03 9.01.52.png](..%2F..%2F..%2FDesktop%2F%E3%82%B9%E3%82%AF%E3%83%AA%E3%83%BC%E3%83%B3%E3%82%B7%E3%83%A7%E3%83%83%E3%83%88%202024-10-03%209.01.52.png)

## これなんで？？

chatGPTに聞いてみた

特徴	Cookie方式	ヘッダ方式
トークンの送信方法	CSRFトークンがCookieに保存され、リクエスト時に含める	カスタムヘッダにCSRFトークンを含める
クライアントの管理	ブラウザが自動的にCookieを管理	クライアント側でトークンを明示的に管理する必要がある
利用ケース	HTMLフォームを使う従来型のWebアプリ	JavaScriptフレームワークによるSPAなど、カスタムヘッダを使いやすい