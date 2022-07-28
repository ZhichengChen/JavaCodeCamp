## 说明

第一个版本，完全基于内存queue的消息队列，实现了最基础的三个功能：

- 创建topic
- 订阅topic和poll消息
- 发送消息到topic

具体参见demo.KmqDemo

## 作业 

第二个版本 自定义 Queue

去掉内存 Queue，设计自定义 Queue，实现消息确认和消费 offset

1、自定义内存 Message 数组模拟 Queue
2、使用指针记录当前消息写入位置
3、对于每个命名消费者，用指针记录消费位置

因为数据没有真实的弹出，还在内存，容易 OOM