# 開発ポリシー

## Activity と Fragment

Activity と Fragment 構成の詳細は各種 Activity に記しています。

全画面で共通のポリシーは以下の通り。

### Activity は Fragment の "コントローラ"

Activity の役割は以下の通り。

- Fragment, BroadcastIntent 等が発するイベントを受け取る
- 受け取ったイベントを利用して Fragment を制御する

### Fragment は画面のコンポーネント

Fragment は、画面を構成する一部品を実装する。

### Fragmentの責務を小さくする

1つの Fragment で「リストを表示する」「入力エリアを表示する」などの小さい単位に機能を分割する。

### Fragment 間通信を直接は行わない

Fragment 間通信は `Fragment -> Activity -> Fragment` のように、 Activityを経由して行う。

Fragment は Activity にイベントを送る事のみを考える。

Fragment 間通信のために Fragment から Activity へイベントを送信する際、IDE の支援をより強く受けるため、 `Bundle` や `Intent` を使わずに独自 Listener を定義する。

## 学習コストを減らす

半年ぶりにメンテする自分がすぐにメンテ出来るような実装を心がける。

多少ネストが深くても、「半年ぶりに見てすぐに実装を思い出して修正出来る」ような見通しのよさを重視していく。

## Java 7,8 の記法を利用する

[retrolambda](https://github.com/orfjackal/retrolambda) を導入し、Java 7,8 の省略記法を積極的に利用していく。

ただし、Java 7 以降に導入されたクラスやメソッドは利用しない。Android OSによっては該当APIが存在しない事もあるため。
