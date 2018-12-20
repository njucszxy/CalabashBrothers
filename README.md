# 葫芦兄弟

葫芦七兄弟和老爷爷在一片草地上与蛇精、蝎子精率领的小喽啰们展开大战



### 功能

+ 当战斗未开始时按”S“键，游戏开始，存档将存放在jar文件同一目录下，按”L“键，选择特定存档复盘（也可以使用New与Load按钮）
+ 战斗开始前可通过主界面右侧两个选择框选择本次战斗葫芦娃、妖怪双方的阵型，葫芦娃处于上方，妖怪处于下方
+ 每次调整阵型，葫芦兄弟与妖怪会即时变换阵法
+ 程序运行时将播放背景音乐(bgm2.wav)
+ 每个单位具有独特的战斗属性，包括特有的战斗力范围、战斗力计算函数（定义在Creature.java 与 PowerPattern.java中）、在战斗力持平时将会随机决定胜负
+ 游戏开始后每个单位将并发向离自己最近的敌人移动、进攻，死亡单位将会留下尸体，单位与尸体在同一格上时将会覆盖尸体（把尸体踩在脚下），尸体若在同一方格内将会覆盖显示
+ 主界面下方有一个只读窗口，将会输出战斗中的信息，例如击杀信息、胜利信息等
+ 每次战斗将会重新开启一个时空，将葫芦兄弟与妖怪们带回到战斗前，战场也会被还原（无尸体）



### 设计

- 项目结构

  项目由.idea文件夹、src文件夹以及JavaFXTest3.iml、pom.xml组成

  项目结构如下

  │  JavaFXTest3.iml
  │  pom.xml
  │
  ├─.idea
  │  │  compiler.xml
  │  │  description.html
  │  │  encodings.xml
  │  │  gradle.xml
  │  │  misc.xml
  │  │  modules.xml
  │  │  uiDesigner.xml
  │  │  vcs.xml
  │  │  workspace.xml
  │  │
  │  ├─artifacts
  │  │      JavaFXTest3_jar.xml
  │  │
  │  └─libraries
  │          Maven__javax_media_jmf_2_1_1e.xml
  │          Maven__jaxen_jaxen_1_1_6.xml
  │          Maven__junit_junit_4_12.xml
  │          Maven__org_dom4j_dom4j_2_0_2.xml
  │          Maven__org_hamcrest_hamcrest_core_1_3.xml

  ├─src
  │  ├─main
  │  │  ├─java
  │  │  │  ├─battlefield
  │  │  │  │      BattleField.java
  │  │  │  │      Land.java
  │  │  │  │
  │  │  │  ├─gui
  │  │  │  │      Controller.java
  │  │  │  │      Main.java
  │  │  │  │      sample.fxml
  │  │  │  │
  │  │  │  ├─info
  │  │  │  │      Camp.java
  │  │  │  │      Commander.java
  │  │  │  │      Formation.java
  │  │  │  │      FormationType.java
  │  │  │  │      LoadThread.java
  │  │  │  │      PositionInfo.java
  │  │  │  │      PowerPattern.java
  │  │  │  │
  │  │  │  ├─lives
  │  │  │  │  │  Creature.java
  │  │  │  │  │
  │  │  │  │  ├─CB
  │  │  │  │  │      BlueBoy.java
  │  │  │  │  │      CyanBoy.java
  │  │  │  │  │      GrandPa.java
  │  │  │  │  │      GreenBoy.java
  │  │  │  │  │      OrangeBoy.java
  │  │  │  │  │      PurpleBoy.java
  │  │  │  │  │      RedBoy.java
  │  │  │  │  │      YellowBoy.java
  │  │  │  │  │
  │  │  │  │  └─MO
  │  │  │  │          Footman.java
  │  │  │  │          Scorpion.java
  │  │  │  │          Snake.java
  │  │  │  │
  │  │  │  └─META-INF
  │  │  │          MANIFEST.MF
  │  │  │
  │  │  └─resources
  │  │      ├─META-INF
  │  │      │      MANIFEST.MF
  │  │      │
  │  │      └─resource
  │  │              background.jpg
  │  │              bgm2.wav
  │  │              七娃.jpg
  │  │              三娃.jpg
  │  │              二娃.jpg
  │  │              五娃.jpg
  │  │              六娃.jpg
  │  │              四娃.jpg
  │  │              大娃.jpg
  │  │              小喽啰.jpg
  │  │              尸体.jpg
  │  │              爷爷.jpg
  │  │              蛇精.jpg
  │  │              蝎子精.jpg
  │  │
  │  └─test
  │      └─java
  │          └─info
  │                  PowerPatternTest.java



  其中battlefield包用于定义战场及其属性；

  gui包用于生成图形化界面；

  info包定义了程序的各种属性，并包含有全知全能的创世者；

  lives包定义了所有生物;resources文件夹下保存有本程序使用的所有文件资源;

  test文件夹下保存有程序的单元测试用例



### 运行

+ 运行方法

  在项目目录下执行mvn clean test package，在目录下target文件夹中将生成可执行jar包，其中分为两个jar包，分别为带依赖版与不带依赖版，使用java -jar 命令即可运行程序

+ 效果