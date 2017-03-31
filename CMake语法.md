* [add_subdirectory](#add_subdirectory)

* [add_library](#add_library)

* [set_property](#set_property)

* [add_definitions](#add_definitions)

* [include_directories](#include_directories)

* [target\_include_directories](#target_include_directories)

* [link_libraries](#link_libraries)

* [target\_link_libraries](#target_link_libraries)

* [add_executable](#add_executable)


---

* <a name="add_subdirectory"> </a>add_subdirectory

	add_subdirectory(source\_dir [binary\_dir] [EXCLUDE\_FROM\_ALL])
	用于添加一个需要进行构建的子目录
	
* <a name="add_library"> </a>add_library

	add_library([STATIC | SHARED | MODULE] [EXCLUDE_FROM_ALL] source1source2 … sourceN)
	用于指定从一组源文件 source1 source2 ... sourceN编译出一个库文件且命名为name
	
* <a name="set_property"></a>set_property

	set_property(<GLOBAL                            |
                DIRECTORY [dir]                   |
                TARGET    [target1 [target2 ...]] |
                SOURCE    [src1 [src2 ...]]       |
                TEST      [test1 [test2 ...]]     |
                CACHE     [entry1 [entry2 ...]]>
               [APPEND][APPEND_STRING]
               PROPERTY <name>[value1 [value2 ...]])
               
在某个域中对零个或多个对象设置一个属性。第一个参数决定该属性设置所在的域。它必须为下面中的其中之一：

GLOBAL域是唯一的，并且不接特殊的任何名字。

DIRECTORY域默认为当前目录，但也可以用全路径或相对路径指定其他的目录（前提是该目录已经被CMake处理）。

TARGET域可命名零或多个已经存在的目标。

SOURCE域可命名零或多个源文件。注意：源文件属性只对在相同目录下的目标是可见的(CMakeLists.txt)。

TEST域可命名零或多个已存在的测试。

CACHE域必须命名零或多个已存在条目的cache.

必选项PROPERTY后面紧跟着要设置的属性的名字。

其他的参数用于构建以分号隔开的列表形式的属性值。如果指定了APPEND选项，则指定的列表将会追加到任何已存在的属性值当中。如果指定了APPEND_STRING选项，则会将值作为字符串追加到任何已存在的属性值。

* <a name="add_definitions"></a>add_definitions

	add_definitions(-DFOO -DBAR …)
	
	用于添加编译器命令行标志 通常的情况下我们使用其来添加预处理器定义
	
* <a name="include_directories"></a>	include_directories

	添加头文件目录
* <a name="target_include_directories"></a>target\_include_directories

	设置头文件目录
	
* <a name="link_libraries"></a> link_libraries

	添加需要链接的库文件路径
	
* <a name="target_link_libraries"></a>target\_link_libraries

	设置要链接的库文件的名称
	
* <a name="add_executable"></a> add_executable

	命令语法：add\_executable(\<name\> [WIN32] [MACOSX\_BUNDLE][EXCLUDE\_FROM\_ALL] source1 source2 … sourceN)
	命令简述：用于指定从一组源文件 source1 source2 … sourceN 编译出一个可执行文件且命名为 name

	
---
参考链接

1. [http://blog.csdn.net/dbzhang800/article/details/6314073](http://blog.csdn.net/dbzhang800/article/details/6314073)
2. [http://blog.csdn.net/fuyajun01/article/details/9036485](http://blog.csdn.net/fuyajun01/article/details/9036485)
3. [http://www.cnblogs.com/binbinjx/p/5626916.html](http://www.cnblogs.com/binbinjx/p/5626916.html)