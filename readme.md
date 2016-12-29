### #阿里andFix热修复框架使用说明

~~~java
1.下载apkpatch工具，该工具主要用于比较old.apk,new.apk差异并生成补丁包
2.app中的build.gradle 配置compile 'com.alipay.euler:andfix:0.5.0@aar'
3.Application onCreate 中初始化PatchManage
4.生成测试apk并安装 old.apk 
5.生成正式apk，此apk无需安装
6.进入apkpatch目录 执行apkpatch.bat -f new.apk -t old.apk -o output -k debug.keystore -p android -a androiddebugkey -e android生成补丁包
7.修复bug时调用patchManager.addPatch("本地补丁包路径")，完全修复
备注：
  由于andFix对补丁包有安全验证,old.apk,new.apk签名必须一致，否则无法修复
  -f <new.apk> ：新版本
  -t <old.apk> : 旧版本
  -o <output> ： 输出目录
  -k <keystore>： 打包所用的keystore
  -p <password>： keystore的密码
  -a <alias>： keystore 用户别名
  -e <alias password>： keystore 用户别名密码  
    
Demo目录介绍：
test目录为测试文件，安装即可体验，old.apk待修复版本，new.apk已修复版本，fix.apatch补丁包，如需体验请安装old.apk,并在/mnt/sdcard/目录下创建hotfix文件夹，然后把fix.apatch复制到hostfix目录下.安装完成后点击THROW EXCEPTION按钮会提示“发现一个bug,赶紧解决”，点击HOT FIX按钮则会查找/mnt/sdcard/hotfix/目录下fix.patch补丁包，并替换MainActivity中test测试方法，再次点击THROW EXCEPTION则会提示bug 已修复。说明成功加载补丁包，并完成修复，修复过程应用无需重启。
~~~


