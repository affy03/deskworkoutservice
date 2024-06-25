DROP TABLE IF EXISTS deskworkouts;

CREATE TABLE deskworkouts (
  id int unsigned AUTO_INCREMENT,
  name VARCHAR(20) NOT NULL,
  howto VARCHAR(200) NOT NULL DEFAULT '未設定',
  repetition INT NOT NULL DEFAULT 0,
  bodyparts VARCHAR(100)  NOT NULL DEFAULT '未設定',
  difficulty VARCHAR(20)  NOT NULL DEFAULT '未設定',
  PRIMARY KEY(id)
);

INSERT INTO deskworkouts (name, howto, repetition, bodyparts, difficulty ) VALUES ("シットアップ", "直角に曲げた膝を机裏にタッチする", 10, "お腹", "初級");
INSERT INTO deskworkouts (name, howto, repetition, bodyparts, difficulty) VALUES  ("バックエクステンション", "お尻を背もたれに付けたまま、デコルテを机に近づける", 10, "背中", "中級");
INSERT INTO deskworkouts (name, howto, repetition, bodyparts, difficulty ) VALUES ("ショルダーダウン", "肘で背もたれを押したまま、肩を引き下げる", 1, "肩", "初級");
INSERT INTO deskworkouts (name, howto, repetition, bodyparts, difficulty) VALUES  ("ニーエクステンション", "内ももをくっ付けたまま、膝を伸ばす", 10, "脚", "初級");
INSERT INTO deskworkouts (name, howto, repetition, bodyparts, difficulty ) VALUES ("スクワット", "片膝を斜め横に向けたまま、お尻を椅子から持ち上げる", 10, "お尻", "上級");
