# RetrofitRxjavaDemo
rxjva+retrofit+mvp
下面是我的博客地址，demo是集成了RXjava+retrofit+mvp,可以作为基本的代码模板进行开发
https://blog.csdn.net/qq_16177199/article/list/1

#添加抓包神器Stetho

*1.添加jar包*
<pre>
    <code>
    implementation 'com.facebook.stetho:stetho:1.5.0'
    implementation 'com.facebook.stetho:stetho-okhttp3:1.5.0'
    
     </code>
    </pre>   
*2.初始化，推荐在application中进行初始化*
 
<pre>
    <code>
 Stetho.initialize(
             Stetho.newInitializerBuilder(this)
             .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
              .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
              build());
         StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder() //
                 .detectAll() //
                 .penaltyLog() //
                 .penaltyDeath() //
                 .build());
 </code>
</pre>        
                 
                 
 *3.添加拦截器addNetworkInterceptor*
<pre>
    <code>
 OkHttpClient mOkHttpClient=new OkHttpClient.Builder()
                .connectTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .readTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .writeTimeout(HttpConfig.HTTP_TIME, TimeUnit.SECONDS)
                .addInterceptor(InterceptorUtil.HeaderInterceptor())
                .addInterceptor(InterceptorUtil.LogInterceptor())//添加日志拦截器
                .addNetworkInterceptor(new StethoInterceptor()) //添加抓包工具
                .build();
 </code>
</pre> 
    
 *4.添加自动打包，自动打debug包或者release包*
 
 
 
 
  *5.添加自动刷博客的流量的代码*
