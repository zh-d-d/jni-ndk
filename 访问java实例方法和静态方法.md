访问java实例方法和静态方法
==
访问静态方法实现说明
--
1. 方法原型

  void (JNICALL \*CallStaticVoidMethod)(JNIEnv \*env,jclass cls,jmethodId methodId, ...);

  该方法接收四个参数:

  env: JNI函数表指针

  cls: 调用该静态方法的Class对象

  jmethodId: 方法id(一个类中有多个方法,需要一个唯一的标识来确定类中的哪个方法)

  ... : 调用的方法的参数列表

2. FindClass();

  根据传入的参数(Class描述符,JVM会从classpath路径下搜索该类,并返回jclass类型)

3. GetStaticMethodID();

  根据传入的参数确定到哪个类下去查找,根据参数的方法签名确定找哪个方法,该方法返回一个jmethodId.

4. 在获取到jclass 和jmethodId之后就可以调用CallStaticVoidMethod().

>JVM针对所有的数据类型的返回值都定义了相关函数.上面CallStaticVoidMethod()方法的返回值为void.根据返回值类型不同,JNI提供了一系列不同返回值的函数,如:CallStaticIntMethod(),CallStaticFloatMethod(),CallStaticShortMethod(),CallStaticObjectMethod()等.分别表示返回值为int ,float ,short ,Object 类型的函数.引用数据类型统一调用CallStaticObjectMethod().

访问实例方法实现说明
--

1. 方法原型

  void(JNICALL \*CallVoidMethod)(JNIEnv \*env,jobject obj,jmethodId methodId, ...);

  env: JNI函数表指针

  obj: 调用该方法的实例

  methodId: 方法id

  ...: 方法的参数列表

2. 同样需要先通过FindClass()获得Class对象

3. GetMethodID();

  获取方法id,首先获取构造方法id .然后在获取需要调用的方法的id

4. NewObject()
  创建类的实例对象

5. 在获取到jobject 对象实例和方法id之后就可以调用CallVoidMethod()

> 同JNI调用java静态方法一样,jvm针对所有数据类型的返回值也都定义了相对应额函数(CallXXXMethod).如: CallIntMethod() CallFloatMethod() CallObjectMethod()等.

方法签名
--
从上面的例子中可以发现,无论是调用静态方法还是实例方法,都必须传入一个jmethodId的参数.因为在java中存在方法重载,所以需要告诉jvm调用的类或者实例的哪一个方法.在调用GetMethodID()的时候需要传入一个方法名和方法签名,方法名就是在java中定义的方法名,方法签名的格式为:(形参参数类型列表)返回值. 形参参数列表中引用类型以L开头,后面紧跟类的全路径名(需将.全部换成/),以分号结尾.下面是一些示例:

|方法描述|java 类型|
|:-----:|:------:|
|"()Ljava/lang/String;"|String f();|
|"(ILjava/lang/Class;)J"|long f(int i ,Class c);|
|"([B)V"|String (byte[] bytes )|

java基本类型与方法签名中参数类型和返回值类型的映射关系如下:

|Field Descriptor|Java Language Type|
|:--:|:--:|
|Z|boolean|
|B|byte|
|C|char|
|S|short|
|I|int|
|J|long|
|F|float|
|D|double|
