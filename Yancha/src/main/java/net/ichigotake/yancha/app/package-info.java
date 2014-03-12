/**
 * 安定したビルド時間を実現するために以下のような施策を打ってみる
 *
 *  - 基本的に :Yancha にはコードもリソースも置かない
 *  - :Yancha には現在進行中のタスクに関わるもののみを置いてゆく
 *      - 既存のものを編集する場合は、:Yancha へコピーしたり、フラグメントを差し替えるなどして運用でカバー
 *  - :Stock に完成した実装を置いてゆく
 *  - ただし、AndroidMnnifestの仕様で、初回起動アクティビティは :Yancha に置かないとダメ
 *      - AndroidManifest が参照するリソースも :Yancha に置く必要がある
 *      - その際、 :Stock との名前空間の衝突に注意する
 */
package net.ichigotake.yancha.app;