package com.gw.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;

/**
 * des: 为什么需要路由框架
 * 我们所使用的原生路由方案一般是通过显式intent和隐式intent两种方式实现的，
 * 而在显式intent的情况下，因为会存在直接的类依赖的问题，导致耦合非常严重；
 * 而在隐式intent(跨进程调用,A应用调用B应用的某个activity)情况下
 * 则会出现规则集中式管理，导致协作变得非常困难。而且一般而言配置规则都是在Manifest中的，这就导致了扩展性较差。 *
 * <p>
 * 主要是:一方面是组件化，而另一方面就是Native和H5的问题
 * <p>
 * 注解反射:
 * 使用注解时会遇到的第一个问题就是需要找到处理注解注解的时机，
 * 如果在运行期处理注解则会大量地运用反射，而这在软件开发中是非常不合适的，
 * 因为反射本身就存在性能问题，如果大量地使用反射会严重影响APP的用户体验，而又因为路由框架是非常基础的框架，
 * 所以大量使用反射也会使得跳转流程的用户体验非常差。所以ARouter最终使用的方式是在编译期处理被注解的类，
 * 而可以做到在运行中尽可能不使用反射。其实这一部分就是注解处理器，注解处理器其实是作用在JVM上的，可以通过插入一部分代码来处理被注解标注的类。
 * <p>
 * <p>
 * 分布式管理:可以将所有的配置都放在目标页面，这样就实现了“All In One”，就是一个页面中所有的配置都要聚合在该页面中，不同的页面由不同的配置负责,而不需要将配置散落在整个APP四处。
 * author:lucas
 * date:2021/1/21 2:49 PM
 * address:https://developer.aliyun.com/article/71687
 */
@Route(path = Module.MODULE_ONE)
public class ARouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a_router);

        String name=getIntent().getStringExtra("name");
        Toast.makeText(ARouterActivity.this,name,Toast.LENGTH_LONG).show();
        findViewById(R.id.onArouter).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(ARouterActivity.this,"你点击了按钮",Toast.LENGTH_LONG).show();
               ARouter.getInstance().build(Module.MODULE_TWO).navigation();
//                ISkill impl=
//                if(impl!=null){
//                    impl.getActivity();
//                }
            }
        });
    }

}