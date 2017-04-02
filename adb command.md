1. 查看cpu信息

  adb shell cat /proc/cpuinfo

2. 使环境变量生效

	source .bash_profile
	
3. 查看入口activity

	adb shell dumpsys activity top
