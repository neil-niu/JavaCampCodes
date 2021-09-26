week2：

打印GC日志：-XX:+PrintGCDetails 

配置堆内存： -Xmx1g -Xms1g 

打印时间：    -XX:PrintGCDateStamps

选择并行GC： -XX:+UseParallelGC

选择串行GC：-XX:+UseSerialGC

选择CMSGC:  -XX:+UseConcMarkSweepGC

GC日志打印到文件：  -Xloggc:GClogParaWithParallelGC.txt

打日志，但不需要detailed：  -XX:+PrintGC

自适应参数，动态调整各种阈值：

默认堆内存配置参数，如果大于1G，一般默认为 物理内存 的四分之一

蓄水池作用， 大的堆内存相对来说GC的次数和频率都会低

同样，大的堆内存会导致，单次GC所消耗的时间也会增加。

GC日志解读:

```plain
[0.023s][info][gc] Using Parallel
[0.024s][info][gc,heap,coops] Heap address: 0x0000000703600000, size: 4042 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
[0.625s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.632s][info][gc,heap      ] GC(0) PSYoungGen: 64984K->10732K(75776K)
[0.632s][info][gc,heap      ] GC(0) ParOldGen: 0K->9589K(173568K)
[0.632s][info][gc,metaspace ] GC(0) Metaspace: 15005K->15005K(1062912K)
[0.632s][info][gc           ] GC(0) Pause Young (Allocation Failure) 63M->19M(243M) 7.173ms
[0.632s][info][gc,cpu       ] GC(0) User=0.11s Sys=0.00s Real=0.01s
```
1. 使用的并行GC策略
2. 堆地址，大小等信息这里是 4G
3. gc start： GC 发生的原因：分配内存失败导致。
4. Young区的大小由 65M -》 10M左右， 压缩了55M左右，young区容量为76M左右
5. OLD区的大小由 0-》9.6M
```plain
[1.123s][info][gc,start       ] GC(9) Pause Full (Ergonomics)
[1.123s][info][gc,phases,start] GC(9) Marking Phase
[1.127s][info][gc,phases      ] GC(9) Marking Phase 4.229ms
[1.127s][info][gc,phases,start] GC(9) Summary Phase
[1.127s][info][gc,phases      ] GC(9) Summary Phase 0.019ms
[1.127s][info][gc,phases,start] GC(9) Adjust Roots
[1.129s][info][gc,phases      ] GC(9) Adjust Roots 1.838ms
[1.129s][info][gc,phases,start] GC(9) Compaction Phase
[1.159s][info][gc,phases      ] GC(9) Compaction Phase 29.688ms
[1.159s][info][gc,phases,start] GC(9) Post Compact
[1.161s][info][gc,phases      ] GC(9) Post Compact 1.953ms
[1.162s][info][gc,heap        ] GC(9) PSYoungGen: 136183K->0K(1152512K)
[1.162s][info][gc,heap        ] GC(9) ParOldGen: 295714K->297098K(533504K)
[1.162s][info][gc,metaspace   ] GC(9) Metaspace: 15013K->15013K(1062912K)
[1.162s][info][gc             ] GC(9) Pause Full (Ergonomics) 421M->290M(1646M) 39.059ms
[1.162s][info][gc,cpu         ] GC(9) User=0.23s Sys=0.02s Real=0.04s
```

fullGC 从 421M 回收至 290M，用了39ms左右。

同时young区从136M直接降到了0

但是OLD区基本没有变化，这个是个疑问，就是经过一个fullgc后， old区和meta区基本没有变化。

如果持续减少堆内存，比如xmx  xms配置为256m，则开始报java.lang.OutOfMemoryError.

堆内存起始内存和最大内存配置一致：

堆内存起始内存和最大内存配置不一致：


```plain
[0.038s][info][gc] Using Concurrent Mark Sweep
[0.038s][info][gc,heap,coops] Heap address: 0x0000000700000000, size: 4096 MB, Compressed Oops mode: Zero based, Oop shift amount: 3
[0.728s][info][gc,start     ] GC(0) Pause Young (Allocation Failure)
[0.728s][info][gc,task      ] GC(0) Using 8 workers of 8 for evacuation
[0.774s][info][gc,heap      ] GC(0) ParNew: 545344K->68095K(613440K)
[0.774s][info][gc,heap      ] GC(0) CMS: 0K->83495K(3512768K)
[0.774s][info][gc,metaspace ] GC(0) Metaspace: 14979K->14979K(1062912K)
[0.774s][info][gc           ] GC(0) Pause Young (Allocation Failure) 532M->148M(4029M) 46.509ms
[0.774s][info][gc,cpu       ] GC(0) User=0.14s Sys=0.06s Real=0.05s
[0.842s][info][gc,start     ] GC(1) Pause Young (Allocation Failure)
[0.842s][info][gc,task      ] GC(1) Using 8 workers of 8 for evacuation
[0.877s][info][gc,heap      ] GC(1) ParNew: 613439K->68096K(613440K)
[0.877s][info][gc,heap      ] GC(1) CMS: 83495K->224022K(3512768K)
[0.877s][info][gc,metaspace ] GC(1) Metaspace: 14981K->14981K(1062912K)
[0.877s][info][gc           ] GC(1) Pause Young (Allocation Failure) 680M->285M(4029M) 34.579ms
[0.877s][info][gc,cpu       ] GC(1) User=0.06s Sys=0.19s Real=0.03s
[0.937s][info][gc,start     ] GC(2) Pause Young (Allocation Failure)
[0.937s][info][gc,task      ] GC(2) Using 8 workers of 8 for evacuation
[1.003s][info][gc,heap      ] GC(2) ParNew: 613440K->68096K(613440K)
[1.003s][info][gc,heap      ] GC(2) CMS: 224022K->360679K(3512768K)
[1.003s][info][gc,metaspace ] GC(2) Metaspace: 14981K->14981K(1062912K)
[1.003s][info][gc           ] GC(2) Pause Young (Allocation Failure) 817M->418M(4029M) 65.553ms
[1.003s][info][gc,cpu       ] GC(2) User=0.44s Sys=0.06s Real=0.07s
[1.066s][info][gc,start     ] GC(3) Pause Young (Allocation Failure)
[1.067s][info][gc,task      ] GC(3) Using 8 workers of 8 for evacuation
[1.132s][info][gc,heap      ] GC(3) ParNew: 613440K->68096K(613440K)
[1.132s][info][gc,heap      ] GC(3) CMS: 360679K->490601K(3512768K)
[1.132s][info][gc,metaspace ] GC(3) Metaspace: 14981K->14981K(1062912K)
[1.132s][info][gc           ] GC(3) Pause Young (Allocation Failure) 951M->545M(4029M) 65.249ms
[1.132s][info][gc,cpu       ] GC(3) User=0.45s Sys=0.02s Real=0.07s
[1.194s][info][gc,start     ] GC(4) Pause Young (Allocation Failure)
[1.194s][info][gc,task      ] GC(4) Using 8 workers of 8 for evacuation
[1.262s][info][gc,heap      ] GC(4) ParNew: 613440K->68096K(613440K)
[1.262s][info][gc,heap      ] GC(4) CMS: 490601K->640473K(3512768K)
[1.262s][info][gc,metaspace ] GC(4) Metaspace: 14981K->14981K(1062912K)
[1.262s][info][gc           ] GC(4) Pause Young (Allocation Failure) 1078M->691M(4029M) 67.971ms
[1.262s][info][gc,cpu       ] GC(4) User=0.44s Sys=0.06s Real=0.07s
[1.324s][info][gc,start     ] GC(5) Pause Young (Allocation Failure)
[1.324s][info][gc,task      ] GC(5) Using 8 workers of 8 for evacuation
[1.398s][info][gc,heap      ] GC(5) ParNew: 613440K->68096K(613440K)
[1.399s][info][gc,heap      ] GC(5) CMS: 640473K->802616K(3512768K)
[1.399s][info][gc,metaspace ] GC(5) Metaspace: 14981K->14981K(1062912K)
[1.399s][info][gc           ] GC(5) Pause Young (Allocation Failure) 1224M->850M(4029M) 74.124ms
[1.399s][info][gc,cpu       ] GC(5) User=0.56s Sys=0.05s Real=0.07s
[1.462s][info][gc,start     ] GC(6) Pause Young (Allocation Failure)
[1.462s][info][gc,task      ] GC(6) Using 8 workers of 8 for evacuation
[1.527s][info][gc,heap      ] GC(6) ParNew: 613440K->68096K(613440K)
[1.527s][info][gc,heap      ] GC(6) CMS: 802616K->945078K(3512768K)
[1.527s][info][gc,metaspace ] GC(6) Metaspace: 14981K->14981K(1062912K)
[1.527s][info][gc           ] GC(6) Pause Young (Allocation Failure) 1382M->989M(4029M) 65.159ms
[1.527s][info][gc,cpu       ] GC(6) User=0.44s Sys=0.05s Real=0.06s
[1.539s][info][gc,heap,exit ] Heap
[1.539s][info][gc,heap,exit ]  par new generation   total 613440K, used 107080K [0x0000000700000000, 0x0000000729990000, 0x0000000729990000)
[1.539s][info][gc,heap,exit ]   eden space 545344K,   7% used [0x0000000700000000, 0x00000007026121a0, 0x0000000721490000)
[1.539s][info][gc,heap,exit ]   from space 68096K, 100% used [0x0000000725710000, 0x0000000729990000, 0x0000000729990000)
[1.539s][info][gc,heap,exit ]   to   space 68096K,   0% used [0x0000000721490000, 0x0000000721490000, 0x0000000725710000)
[1.539s][info][gc,heap,exit ]  concurrent mark-sweep generation total 3512768K, used 945078K [0x0000000729990000, 0x0000000800000000, 0x0000000800000000)
[1.539s][info][gc,heap,exit ]  Metaspace       used 15186K, capacity 15535K, committed 15616K, reserved 1062912K
[1.539s][info][gc,heap,exit ]   class space    used 1641K, capacity 1758K, committed 1792K, reserved 1048576K
```
CMS GC 配置为4G的情况下，么有发生CMSgc，只发生了6次yongGc
```plain
[0.028s][info][gc] Using Concurrent Mark Sweep
[0.734s][info][gc] GC(0) Pause Young (Allocation Failure) 273M->98M(989M) 22.932ms
[0.802s][info][gc] GC(1) Pause Young (Allocation Failure) 371M->178M(989M) 27.427ms
[0.884s][info][gc] GC(2) Pause Young (Allocation Failure) 451M->260M(989M) 48.291ms
[0.970s][info][gc] GC(3) Pause Young (Allocation Failure) 533M->346M(989M) 49.951ms
[1.057s][info][gc] GC(4) Pause Young (Allocation Failure) 619M->441M(989M) 54.803ms
[1.058s][info][gc] GC(5) Pause Initial Mark 447M->447M(989M) 0.130ms
[1.058s][info][gc] GC(5) Concurrent Mark
[1.062s][info][gc] GC(5) Concurrent Mark 4.453ms
[1.062s][info][gc] GC(5) Concurrent Preclean
[1.064s][info][gc] GC(5) Concurrent Preclean 1.957ms
[1.064s][info][gc] GC(5) Concurrent Abortable Preclean
[1.144s][info][gc] GC(6) Pause Young (Allocation Failure) 714M->532M(989M) 53.460ms
[1.235s][info][gc] GC(7) Pause Young (Allocation Failure) 805M->628M(989M) 57.352ms
[1.272s][info][gc] GC(5) Concurrent Abortable Preclean 207.661ms
[1.333s][info][gc] GC(9) Pause Full (Allocation Failure) 901M->317M(989M) 61.242ms
[1.333s][info][gc] GC(8) Pause Young (Allocation Failure) 901M->317M(989M) 61.345ms
[1.383s][info][gc] GC(10) Pause Young (Allocation Failure) 590M->420M(989M) 13.891ms
[1.383s][info][gc] GC(11) Pause Initial Mark 425M->425M(989M) 0.103ms
[1.384s][info][gc] GC(11) Concurrent Mark
[1.388s][info][gc] GC(11) Concurrent Mark 4.619ms
[1.388s][info][gc] GC(11) Concurrent Preclean
[1.390s][info][gc] GC(11) Concurrent Preclean 2.108ms
[1.390s][info][gc] GC(11) Concurrent Abortable Preclean
[1.439s][info][gc] GC(12) Pause Young (Allocation Failure) 693M->509M(989M) 17.061ms
[1.491s][info][gc] GC(13) Pause Young (Allocation Failure) 782M->595M(989M) 17.382ms
[1.567s][info][gc] GC(14) Pause Young (Allocation Failure) 868M->681M(989M) 39.659ms
[1.569s][info][gc] GC(11) Concurrent Abortable Preclean 178.401ms
[1.571s][info][gc] GC(11) Pause Remark 694M->694M(989M) 2.115ms
[1.571s][info][gc] GC(11) Concurrent Sweep
[1.573s][info][gc] GC(11) Concurrent Sweep 1.373ms
[1.573s][info][gc] GC(11) Concurrent Reset
[1.573s][info][gc] GC(11) Concurrent Reset 0.528ms
```
如果配置为1G的情况下，会发生4次yonggc， 中间有一次cmsGC，中间有两次yongGC，还有穿插一次full GC。
CMS GC步骤：

1. initial mark (初始标记)
2. Concurrent Mark（并发标记）
3. Cocncurrent Preclean (并发预清理)
4. Final Remark  (最终标记)
5. Concurrent Sweep （并发清除）
6. Concurrent Reset   （并发重置）


G1 GC 步骤：

Evacuation Pause:  young(纯年轻代模式转移暂停)

Concurrent marking:  并发标记

1. initial mark  （初始标记）
2. Root Region Scan （Root区扫描）
3. Concurrent mark (并发标记)
4. Remark  （再次标记）
5. Cleanup  （清理）

Evacuation pause (mixed)  转移暂停：混合模式

Full GC  （Allocation Failure）


G1 GC最坏的情况话会退化为串行GC，对性能影响很大

GC日志解读工具

GCEasy：   gceasy.io

GCViewer:   jar包







