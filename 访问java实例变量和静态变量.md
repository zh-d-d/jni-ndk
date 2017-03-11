访问java实例变量和静态变量
==

访问实例变量
--

1. 方法原型 GetObjectField()

  jobject(JNICall \* GetObjectField)(JNIEnv \* env ,jobject obj, jfield field);

  env: JNI函数表指针

  obj: 实例变量所属对象

  field: 变量id(也称为属性描述符或者签名)

  > 在jni获取引用类型字段的值时,调用GetObjectField(),同样的jni还提供了获取其他类型变量时的相关方法:GetIntField() ,GetFloatField() ,GetBooleanField() ,GetDoubleField().

2. SetObjectField()

  修改实例属性的值.修改引用数据类型的值调用 SetObjectField() , 同样的对于基本数据类型的修改也有对应的方法函数: SetIntField() SetFloatField() SetBooleanField()

3. GetFieldId()

  获取属性的id
访问静态变量
--

1. 访问静态变量和访问实例变量使用的方法不同,获取字段id调用的是GetStaticFieldId()

2. 获取和访问静态属性的方法是

  Get/SetStaticXXXField()系列函数
