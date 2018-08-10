# codeStudy  
   
本仓库主要用于研究各种实现机制，粒度多样，根据网上看到的内容或者想到的问题。  

 - 细的比如Java语法糖(如i++, for等)的字节码、String.intern()、Integer等Java原生实现，动态代理等基本技术，  
 - 中的，小玩具比如自己实现的简单 json parser  
 - 大的比如Disruptor源代码（为了方便查看和记录，这里直接导入了源代码，然后加了注释）。   
 ----
**update 2018/08/02** : 部分代码从codeExercise迁移过来，codeExercise作为算法题的专用仓库。   

-----

1、System.gc()是否立即执行？  
测试版本：HotSpot JDK 1.8  
测试代码：Tutorial.essential.TestSystemGc  
测试过程与结论：分别打印调用和不调用System.gc()时进行的gc信息，结论是立即执行，另外java doc里也描述说When control returns from the method call, the Java Virtual Machine has made a best effort to reclaim space from all discarded objects.  
  
不调用System.gc()  
```
Java HotSpot(TM) 64-Bit Server VM (25.45-b02) for windows-amd64 JRE (1.8.0_45-b15), built on Apr 30 2015 12:40:44 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 8257216k(2231256k free), swap 18742976k(6884924k free)
CommandLine flags: -XX:InitialHeapSize=132115456 -XX:MaxHeapSize=2113847296 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
Heap
 PSYoungGen      total 37888K, used 4714K [0x00000000d6000000, 0x00000000d8a00000, 0x0000000100000000)
  eden space 32768K, 14% used [0x00000000d6000000,0x00000000d649a878,0x00000000d8000000)
  from space 5120K, 0% used [0x00000000d8500000,0x00000000d8500000,0x00000000d8a00000)
  to   space 5120K, 0% used [0x00000000d8000000,0x00000000d8000000,0x00000000d8500000)
 ParOldGen       total 86016K, used 0K [0x0000000082000000, 0x0000000087400000, 0x00000000d6000000)
  object space 86016K, 0% used [0x0000000082000000,0x0000000082000000,0x0000000087400000)
 Metaspace       used 3294K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 354K, capacity 388K, committed 512K, reserved 1048576K
```
调用System.gc()  
```
Java HotSpot(TM) 64-Bit Server VM (25.45-b02) for windows-amd64 JRE (1.8.0_45-b15), built on Apr 30 2015 12:40:44 by "java_re" with MS VC++ 10.0 (VS2010)
Memory: 4k page, physical 8257216k(2684248k free), swap 18742976k(7393960k free)
CommandLine flags: -XX:InitialHeapSize=132115456 -XX:MaxHeapSize=2113847296 -XX:+PrintGC -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCTimeStamps -XX:+UseCompressedClassPointers -XX:+UseCompressedOops -XX:-UseLargePagesIndividualAllocation -XX:+UseParallelGC 
2018-08-10T23:09:10.805+0800: 0.164: [GC (System.gc()) [PSYoungGen: 4323K->904K(37888K)] 4323K->912K(123904K), 0.0012514 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
2018-08-10T23:09:10.806+0800: 0.165: [Full GC (System.gc()) [PSYoungGen: 904K->0K(37888K)] [ParOldGen: 8K->809K(86016K)] 912K->809K(123904K), [Metaspace: 3327K->3327K(1056768K)], 0.0077709 secs] [Times: user=0.00 sys=0.00, real=0.01 secs] 
Heap
 PSYoungGen      total 37888K, used 1920K [0x00000000d6000000, 0x00000000d8a00000, 0x0000000100000000)
  eden space 32768K, 5% used [0x00000000d6000000,0x00000000d61e00d0,0x00000000d8000000)
  from space 5120K, 0% used [0x00000000d8000000,0x00000000d8000000,0x00000000d8500000)
  to   space 5120K, 0% used [0x00000000d8500000,0x00000000d8500000,0x00000000d8a00000)
 ParOldGen       total 86016K, used 809K [0x0000000082000000, 0x0000000087400000, 0x00000000d6000000)
  object space 86016K, 0% used [0x0000000082000000,0x00000000820ca530,0x0000000087400000)
 Metaspace       used 3346K, capacity 4496K, committed 4864K, reserved 1056768K
  class space    used 361K, capacity 388K, committed 512K, reserved 1048576K

```
