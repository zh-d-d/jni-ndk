# JNICallback

```
1. 功能：本地代码实现计时器功能
2. POSIX线程的使用
```
![run_detail.png](run_detail.png)

**流程：**

1. [创建线程](#create_thread)
2. [本地线程完成计时功能](#thread_word)
3. [线程销毁](#thread_destroy)

---
## <a name="create_thread"></a>创建线程


>核心方法`pthread_create(pthread_t *thread, pthread_attr_t const* attr,
void*(*start_routine)(void*),void *arg)`
参数:

>* `pthread_t *thread` :指向thread_t类型变量的指针，函数用该指针返回线程的句柄。
* `pthread_attr_t const*attr`: 指向pthread\_attr\_t结构的指针形式存在的新线程属性，可以通过该属性设置新线程的栈基址，栈大小，守护大小，调度策略和调度优先级。
* 指向线程启动程序的函数指针。启动程序函数签名的格式为 `void* start_rountine (void *arg)` 启动线程函数将线程参数看成void 指针，返回void指针类型结果。
* 当线程以空指针的形式运行时，参数都需要传给启动函数。如果不需要传递参数则它可以为NULL

>**pthread_create()函数成功时返回0，失败时返回一个错误代码**

### pthread\_attr_t 线程属性
#### 初始化

线程具有属性用`pthread_attr_t`表示在对该结构进行设置之前必须先对其初始化，在使用后需要去除初始化方法分别为`pthread_attr_init`和`pthread_attr_destroy`

| | |
|:--|:--|
|名称|pthread\_arrt\_init / pthread\_arrt\_destroy|
|功能|对线程属性初始化/去除初始化|
|头文件|#include\<pthread.h\>|
|函数原型|int pthread\_attr\_init(pthread\_arrt\_t* attr) / int pthread\_arrt\_destroy(pthread\_attr\_t *arrt)|
|参数|线程属性变量|
|返回值|成功返回0，失败返回-1|

调用pthread\_arrt\_init()之后，pthread_attr_t结构所包含的内容就是操作系统实现支持的线程所有属性的默认值。

如果要去除对pthread\_arrt\_t结构的初始化，可以调用pthread\_attr\_destroy()方法，如果在初始化的时候为属性对象分配了动态内存空间，那么在调用pthread\_attr\_destroy()这个方法的时候还会调用无效的值对属性对象进行初始化.因此如果经过去除初始化之后的pthread\_attr\_t的被pthread_create()调用，将会返回错误.

线程属性结构如下：

```
typedef struct
{
       int                               detachstate;   线程的分离状态
       int                               schedpolicy;  线程调度策略
       structsched_param              schedparam;  线程的调度参数
       int                               inheritsched;  线程的继承性
       int                                scope;       线程的作用域
       size_t                           guardsize;   线程栈末尾的警戒缓冲区大小
       int                                stackaddr_set;
       void*                          stackaddr;   线程栈的位置
       size_t                           stacksize;    线程栈的大小
}pthread_attr_t;
```

----
#### 设置分离状态
线程的分离状态是指线程以什么样的方式来终止自己。默认情况下线程是非终止状态的，这种情况下原有的线程等待创建的线程结束。只有当pthread_jion()函数返回时，创建的线程才算终止，才能释放占用的系统资源.

而分离状态不是这样的，它没有被其他的线程所等待，自己运行结束了线程也就结束了，马上释放系统资源。

| | |
|:--|:--|
|名称|pthread\_attr\_getdetachstate / pthread\_attr\_setdetachstate|
|功能|获取／修改线程的分离状态属性|
|函数原型|int pthread\_attr\_getdetachstate(const pthread\_attr\_t \*arrt, int \*detachstate) / int pthread\_attr\_setdetachstate(const pthread\_attr\_t\* attr,int \*detachstate)|
|参数|线程属性变量，线程的分离状态属性|
|返回值|成功返回0，失败返回-1|


**pthread\_attr\_t[更多介绍](http://blog.csdn.net/pbymw8iwm/article/details/6721038)**
---
### 互斥锁实现线程同步
POSIX线程API从原生代码提供一组交互功能的互斥，使用前互斥变量应先被初始化。
#### 初始化互斥锁
POSIX提供两种初始化互斥锁的方法，pthread\_mutex\_init和PTHREAD_MUTEX_INIALIZER宏。

| | |
|:--|:--|
|名称|pthread\_mutex\_init|
|功能|初始化互斥锁|
|函数原型|int pthread\_mutex\_init(pthread\_mutex\_t \*mutex, const pthread\_mutexattr\_t\* attr)|
|参数|初始化的互斥变量的指针，互斥锁属性结构体的指针|
|返回值|成功返回0互斥锁处于打开状态，失败返回错误代码|

#### 锁定互斥锁
pthread_mutex_lock函数通过对一个已经初始化的互斥锁紧型封锁操作达到互斥操作的目的。

| | |
|:--|:--|
|函数原型|int pthread\_mutex\_lock(pthread\_mutex\_t *mutex)|
|返回值|成功返回0，失败返回错误代码|
#### 解锁互斥锁
| | |
|:--|:--|
|函数原型|int pthread\_mutex\_unlock(pthread\_mutex\_t*mutex)|
|功能|在临界区代码执行完成时，使用该方法解锁互斥锁|
|返回值|成功返回0，失败返回错误代码|
#### 销毁互斥锁
一旦不需要互斥锁可以将互斥锁进行销毁

| | |
|:--|:--|
|函数原型|int pthread\_mutex\_destroy(pthread\_mutext\_t *mutex)|

