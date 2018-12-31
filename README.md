# 葫芦兄弟

葫芦七兄弟和老爷爷在一片草地上与蛇精、蝎子精率领的小喽啰们展开大战



### 功能

+ 当战斗未开始时按”S“键，游戏开始，存档将存放在jar文件同一目录下，按”L“键，选择特定存档复盘（也可以使用New与Load按钮）
+ 战斗开始前可通过主界面右侧两个选择框选择本次战斗葫芦娃、妖怪双方的阵型，葫芦娃处于上方，妖怪处于下方
+ 每次调整阵型，葫芦兄弟与妖怪会即时变换阵法
+ 程序运行时将播放背景音乐(bgm2.wav)
+ 每个单位具有独特的战斗属性，包括特有的战斗力范围、战斗力计算函数（定义在Creature.java 与 PowerPattern.java中）、在战斗力持平时将会随机决定胜负
+ 游戏开始后每个单位将向离自己最近的敌人射击，同时随机移动躲避敌人的攻击，死亡单位将会留下尸体，单位与尸体在同一格上时将会覆盖尸体（把尸体踩在脚下），尸体若在同一方格内将会覆盖显示
+ 主界面下方有一个只读窗口，将会输出战斗中的信息，例如击杀信息、胜利信息等
+ 每次战斗将会重新开启一个时空，将葫芦兄弟与妖怪们带回到战斗前，战场也会被还原（无尸体）
+ 每个单位初始生命值为1000，葫芦娃方射击弹幕颜色为蓝色，妖怪方射击弹幕颜色为红色
+ 每个单位的中弹部位设定为头部（左上角的一定范围），弹幕若不在范围内则不会判定击中单位，弹幕飞行速度较快，超出战场范围后自动消失



### 结构

- 项目结构

  项目由.idea文件夹、src文件夹以及JavaFXTest3.iml、pom.xml组成

  项目结构如下
```

│  JavaFXTest3.iml
│  pom.xml
│  README.md
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
│
├─out
│  └─artifacts
│      └─JavaFXTest3_jar
│              dom4j-2.0.2.jar
│              JavaFXTest3.jar
│              jaxen-1.1.6.jar
│              jmf-2.1.1e.jar
│
├─src
│  ├─main
│  │  ├─java
│  │  │  ├─battlefield
│  │  │  │      BattleField.java
│  │  │  │      Land.java
│  │  │  │
│  │  │  ├─gui
│  │  │  │      Controller.java
│  │  │  │      FreshThread.java
│  │  │  │      Main.java
│  │  │  │      sample.fxml
│  │  │  │
│  │  │  ├─info
│  │  │  │      Bullet.java
│  │  │  │      Camp.java
│  │  │  │      Commander.java
│  │  │  │      Database.java
│  │  │  │      Formation.java
│  │  │  │      FormationType.java
│  │  │  │      ImageManager.java
│  │  │  │      LoadThread.java
│  │  │  │      MoveInfo.java
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
│
├─target
│  │  JavaFXTest3-1.0-SNAPSHOT-jar-with-dependencies.jar
│  │  JavaFXTest3-1.0-SNAPSHOT.jar
│  │
│  ├─archive-tmp
│  ├─classes
│  │  ├─battlefield
│  │  │      BattleField.class
│  │  │      Land.class
│  │  │
│  │  ├─gui
│  │  │      Controller$1.class
│  │  │      Controller$2.class
│  │  │      Controller$3.class
│  │  │      Controller.class
│  │  │      FreshThread.class
│  │  │      Main$1.class
│  │  │      Main$2.class
│  │  │      Main.class
│  │  │      sample.fxml
│  │  │
│  │  ├─info
│  │  │      Bullet.class
│  │  │      Camp.class
│  │  │      Commander$1.class
│  │  │      Commander.class
│  │  │      Database.class
│  │  │      Formation.class
│  │  │      FormationType.class
│  │  │      ImageManager.class
│  │  │      LoadThread.class
│  │  │      MoveInfo.class
│  │  │      PositionInfo.class
│  │  │      PowerPattern.class
│  │  │
│  │  ├─lives
│  │  │  │  Creature.class
│  │  │  │
│  │  │  ├─CB
│  │  │  │      BlueBoy.class
│  │  │  │      CyanBoy.class
│  │  │  │      GrandPa.class
│  │  │  │      GreenBoy.class
│  │  │  │      OrangeBoy.class
│  │  │  │      PurpleBoy.class
│  │  │  │      RedBoy.class
│  │  │  │      YellowBoy.class
│  │  │  │
│  │  │  └─MO
│  │  │          Footman.class
│  │  │          Scorpion.class
│  │  │          Snake.class
│  │  │
│  │  ├─META-INF
│  │  │      JavaFXTest3.kotlin_module
│  │  │
│  │  └─resource
│  │          background.jpg
│  │          bgm2.wav
│  │          七娃.jpg
│  │          三娃.jpg
│  │          二娃.jpg
│  │          五娃.jpg
│  │          六娃.jpg
│  │          四娃.jpg
│  │          大娃.jpg
│  │          小喽啰.jpg
│  │          尸体.jpg
│  │          爷爷.jpg
│  │          蛇精.jpg
│  │          蝎子精.jpg
│  │
│  ├─generated-sources
│  │  └─annotations
│  ├─generated-test-sources
│  │  └─test-annotations
│  ├─lib
│  │      dom4j-2.0.2.jar
│  │      hamcrest-core-1.3.jar
│  │      jaxen-1.1.6.jar
│  │      jmf-2.1.1e.jar
│  │      junit-4.12.jar
│  │
│  ├─maven-archiver
│  │      pom.properties
│  │
│  ├─maven-status
│  │  └─maven-compiler-plugin
│  │      ├─compile
│  │      │  └─default-compile
│  │      │          createdFiles.lst
│  │      │          inputFiles.lst
│  │      │
│  │      └─testCompile
│  │          └─default-testCompile
│  │                  createdFiles.lst
│  │                  inputFiles.lst
│  │
│  ├─surefire-reports
│  │      info.PowerPatternTest.txt
│  │      TEST-info.PowerPatternTest.xml
│  │
│  └─test-classes
│      └─info
│              PowerPatternTest.class
│
├─存档
│      0.xml
│      1.xml
│      2.xml
│
└─效果图
        1.jpg
        2.jpg
        3.jpg

```
  其中battlefield包用于定义战场及其属性；

  gui包用于生成图形化界面；

  info包定义了程序的各种属性，并包含有全知全能的创世者；

  lives包定义了所有生物;resources文件夹下保存有本程序使用的所有文件资源;

  test文件夹下保存有程序的单元测试用例



### 运行

+ 运行方法

  在项目目录下执行mvn clean test package，在目录下target文件夹中将生成可执行jar包，其中分为两个jar包，分别为带依赖版与不带依赖版，使用java -jar 命令即可运行程序

+ 效果
  <div align=center>
    <img src = "效果图\1.jpg" width='100%'>
  </div>
  <div align=center>
    <img src = "效果图\2.jpg" width='100%'>
  </div>
  <div align=center>
    <img src = "效果图\3.jpg" width='100%'>
  </div>

+ 存档

  见“存档”文件夹下xml文件
  
  
### 设计

+ 生物线程（Creature.java）
  每个生物线程在存活期间会呼唤上帝（Commander.java），并在上帝之眼的帮助下洞察战场信息，完成判断形势、射击、移动等操作

+ 刷新线程
  为了实现高FPS，本程序使用刷新线程（FreshThread.java）更新GUI画面，帧率设定为60FPS
  
+ 主线程
  每次新游戏都会重新初始化战场、生物信息（Database.java）、上帝（Commander.java）以及刷新线程（FreshThread.java）
  
+ 读档线程
  每次读档都会重新初始化战场、生物信息（Database.java）、上帝（Commander.java）以及刷新线程（FreshThread.java），并根据存档信息来执行操作
  
  
### 实现原理

+ 生物并发行动
  在呼唤上帝（Commander.java）的函数中（oneStep）设置锁，使得每次至多只有一个生物得到上帝的帮助
  
+ 刷新与计算
  刷新线程（FreshThread.java）与上帝（Commander.java）都可以访问数据信息（Database.java），在刷新线程执行过程中对数据信息上锁，每次刷新都动态计算当前每个弹幕的位置并显示，计算弹幕击中生物之后的判定，弹幕出界等
  
+ 数据保存
  数据信息（Database.java）中保存有所有生物、战场、所有弹幕、战斗信息、移动信息（保留待用）和时间线（保留待用）
  
+ 记录与重放
  使用XML文件记录战斗过程中每个生物单位初始化、移动、射击、获胜时的状态信息（包括与游戏开始时刻的时间差），在重放时一次读取这些记录，并初始化、修改数据信息使得生物单位执行移动、射击、获胜等操作，并由刷新线程显示GUI画面，在执行这些操作时比对执行时刻与开始时刻的时间差，通过睡眠或校正来保持与原战斗相对应
  
  
### 测试

+ 单元测试
  单元测试对计算生物单位攻击力的类与相关函数进行了测试
  
### 依赖

+ junit
  junit 4.2
  
+ org.dom4j
  dom4j 2.0.2
  
+ javax.media
  jmf 2.1.1e