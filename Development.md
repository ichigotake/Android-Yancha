# 開発環境を整える

## 手順

1, Androidアプリの開発環境をセットアップする(IDE,Javaなど

2, ソースのダウンロード

    ### 例
    $ mkdir -p ~/Development/android && cd $_
    $ git clone https://github.com/ichigotake/Android-yancha yancha
    $ git clone https://github.com/ichigotake/Android-YanchaSDK YanchaSDK
    $ git clone https://github.com/ichigotake/ColorfulSweets

3, ダウンロードしたソースをIDEのプロジェクトへインポートする

4, android.support.v7.appcompatのプロジェクトをSDKからインポートする

5, 依存の設定

    Android-yancha: YanchaSDK,ColorfulSweets,appcompatへ依存
    ColorfulSweets: appcompatへ依存

## TODO

手順をシンプルにしたい

