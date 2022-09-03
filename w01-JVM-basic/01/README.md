新建文件 `Hello.java`

```java
public class Hello {
    public static void main(String[] args) {
        boolean flag = true;
        char c = 'c';
        byte b = 0;
        short s = 0;
        int i = 0;
        long l = 0L;
        float f = 0F;
        double d = 0D;
        int a1 = 1 + 1;
        int a2 = 2 - 1;
        int a3 = 3 * 1;
        int a4 = 4/2;
        if (flag) {
            c = 'd';
        }
        int sum = 0;
        for (i=0;i<10;i++) {
            sum += i;
        }
    }
}
```

编译 `javac -g Hello.java`

查看字节码 `javap -c -v Hello.class`

```bytecode
Classfile /[path]/JavaCodeCamp/w01-JVM-basic/01/Hello.class
  Last modified 2022年9月2日; size 470 bytes
  MD5 checksum fe327a874dada681f2eb31c5e4c54173
  Compiled from "Hello.java"
public class Hello
  minor version: 0
  major version: 55
  flags: (0x0021) ACC_PUBLIC, ACC_SUPER
  this_class: #2                          // Hello
  super_class: #3                         // java/lang/Object
  interfaces: 0, fields: 0, methods: 2, attributes: 1
Constant pool:
   #1 = Methodref          #3.#14         // java/lang/Object."<init>":()V
   #2 = Class              #15            // Hello
   #3 = Class              #16            // java/lang/Object
   #4 = Utf8               <init>
   #5 = Utf8               ()V
   #6 = Utf8               Code
   #7 = Utf8               LineNumberTable
   #8 = Utf8               main
   #9 = Utf8               ([Ljava/lang/String;)V
  #10 = Utf8               StackMapTable
  #11 = Class              #17            // "[Ljava/lang/String;"
  #12 = Utf8               SourceFile
  #13 = Utf8               Hello.java
  #14 = NameAndType        #4:#5          // "<init>":()V
  #15 = Utf8               Hello
  #16 = Utf8               java/lang/Object
  #17 = Utf8               [Ljava/lang/String;
{
  public Hello();
    descriptor: ()V
    flags: (0x0001) ACC_PUBLIC
    Code:
      stack=1, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: return
      LineNumberTable:
        line 1: 0

  public static void main(java.lang.String[]);
    descriptor: ([Ljava/lang/String;)V
    flags: (0x0009) ACC_PUBLIC, ACC_STATIC
    Code:
      stack=2, locals=16, args_size=1
         0: iconst_1
         1: istore_1
         2: bipush        99
         4: istore_2
         5: iconst_0
         6: istore_3
         7: iconst_0
         8: istore        4
        10: iconst_0
        11: istore        5
        13: lconst_0
        14: lstore        6
        16: fconst_0
        17: fstore        8
        19: dconst_0
        20: dstore        9
        22: iconst_2
        23: istore        11
        25: iconst_1
        26: istore        12
        28: iconst_3
        29: istore        13
        31: iconst_2
        32: istore        14
        34: iload_1
        35: ifeq          41
        38: bipush        100
        40: istore_2
        41: iconst_0
        42: istore        15
        44: iconst_0
        45: istore        5
        47: iload         5
        49: bipush        10
        51: if_icmpge     67
        54: iload         15
        56: iload         5
        58: iadd
        59: istore        15
        61: iinc          5, 1
        64: goto          47
        67: return
      LineNumberTable:
        line 3: 0
        line 4: 2
        line 5: 5
        line 6: 7
        line 7: 10
        line 8: 13
        line 9: 16
        line 10: 19
        line 11: 22
        line 12: 25
        line 13: 28
        line 14: 31
        line 15: 34
        line 16: 38
        line 18: 41
        line 19: 44
        line 20: 54
        line 19: 61
        line 22: 67
      StackMapTable: number_of_entries = 3
        frame_type = 255 /* full_frame */
          offset_delta = 41
          locals = [ class "[Ljava/lang/String;", int, int, int, int, int, long, float, double, int, int, int, int ]
          stack = []
        frame_type = 252 /* append */
          offset_delta = 5
          locals = [ int ]
        frame_type = 19 /* same */
}
SourceFile: "Hello.java"
```