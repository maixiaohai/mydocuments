#notes
==============

### MYSQL:

* when IF encounter strings

    ``IF(somestring, x, y)``  
    what we want to see: somestring exits, return x, or, return y  
    ``correct: IF(somestring='', y, x)``

* ALTER 表结构（同时ADD多个字段的方法）

    因为每次使用ALTER时，会把原表复制一遍，所以尽量使用一个ALTER，添加多行字段，如下：
    ``ALTER TABLE xxx``
      ``ADD `duv` int(10) NOT NULL DEFAULT '0' COMMENT '日独立uv',``
      ``ADD `goal_1_visits` int(10) NOT NULL DEFAULT '0' COMMENT '目标1完成访问数'；``

### 代码重构的基本方法list

* 封装成员变量（Encapsulate Field） — 将仅限于本类使用的变量重写成私有（private）成员变量，并提供访问方法（accessor method）。这种重构方式可以将与外部调用者无关的变量隐藏起来，减少代码的耦合性，并减少意外出错的概率。
* 提取方法 （Extract Method） — 意思是将大段代码中的一部分提取后，构成一个新方法。这种重构可以使整段程序的结构变得更清晰，从而增加可读性。这也对函数（Function）通用。
* 一般化类型 （Generalize Type） — 将多个类/函数共用的类型抽象出可以公用的基类（base class），然后利用多态性追加每个类/函数需要的特殊函数。这种重构可以让结构更加清晰，同时可以增加代码的可维护性。
* 函数归父 （Pull Up） — 或译函数上移，指的是方法从子类移动到父类。
* 函数归子 （Push Down） — 或译函数下移，指的是方法从父类移动到子类。
* 方法更名 （Rename Method） — 将方法名称以更好的表达它的用途。 