package com.andy.xposed;

import android.content.pm.ApplicationInfo;
import android.widget.TextView;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * author: Andy
 * 2021/3/30
 * Describe:
 */
public class XposedInit implements IXposedHookLoadPackage {

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {

        if (lpparam.appInfo == null || (lpparam.appInfo.flags & (ApplicationInfo.FLAG_SYSTEM
                | ApplicationInfo.FLAG_UPDATED_SYSTEM_APP)) != 0) {
            return;
        }


        String packageName = lpparam.packageName;//应用包名
        String processName = lpparam.processName;//
        ClassLoader classLoader = lpparam.classLoader;
  /*   重写XC_MethodHook的两个方法beforeHookedMethod和afterHookedMethod，
    这两个方法会在原始的方法的之前和之后执行.
            您可以使用beforeHookedMethod 方法来打印/篡改方法调用的参数(通过param.args)
            ,甚至阻止调用原来的方法（发送自己的结果）.
    afterHookedMethod 方法可以用来做基于原始方法的结果的事情.您还可以用它来操纵结果 .
            当然，你可以添加自己的代码,它将会准确地在原始方法的前或后执行.*/
        if(BuildConfig.APPLICATION_ID.equals(packageName)){
            //om.xposed_wechat.xz.MainActivity   需要Hook类名
            //showIsXposedStart   需要Hook类名下面的方法名
            XposedHelpers.findAndHookMethod("com.andy.xposed.MainActivity", classLoader,
                    "showIsXposedStart", boolean.class, new XC_MethodHook() {
                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                            //
                        }

                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            param.args[0]=true;
                            super.beforeHookedMethod(param);
                        }
                    });
        }

        XposedBridge.log("AndyXB " + lpparam.packageName);
        if (lpparam.packageName.equals("com.andy.androinfo")) {
            XposedBridge.log("AndyXB 测试程序加载成功");
            XposedBridge.log("AndyXB 开始劫持com.andy.androinfo包");
            XposedHelpers.findAndHookMethod("android.widget.TextView", lpparam.classLoader, "setText", CharSequence.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB androinfo " + param.args[0].toString());
                    if(param.args[0].toString().startsWith("支付宝到账:")){
                        XposedBridge.log("AndyXB 成功Hook到setText方法");
                        param.args[0] = "支付宝到账: ￥10,000,000";
                        XposedBridge.log("AndyXB 修改成功！");
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    XposedBridge.log("AndyXB Hook Done!");
                }
            });
        }


        if (lpparam.packageName.equals("com.bandainamcoent.ninjaborutage_app")) {
            XposedBridge.log("AndyXB renzhe start");
            XposedHelpers.findAndHookMethod("jp.bnsi.n17appguardactivity.N17AppGuardActivity", lpparam.classLoader, "SetUserID", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB renzhe " + param.args[0].toString());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod("com.inca.security.Proxy.iIiIiIiIii", lpparam.classLoader, "Load", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB iIiIiIiIii load " + param.args[0].toString());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod("com.inca.security.Proxy.AppGuardFrontApplication", lpparam.classLoader, "iiiiiIIIii", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB secure res:  " + (String)param.getResult());
                    super.afterHookedMethod(param);
                }
            });

//            XposedHelpers.findAndHookMethod("com.inca.security.iIIiiiiiIi", lpparam.classLoader, "iiiiiIIIii", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log("AndyXB secure res2:  " + (String)param.getResult());
//                    super.afterHookedMethod(param);
//                }
//            });

            XposedHelpers.findAndHookMethod("com.inca.security.Proxy.JNISoxProxy", lpparam.classLoader, "loadSecureLibrary", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB loadSecureLibrary:  " + param.args[0]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });


            XposedHelpers.findAndHookMethod("android.app.AlertDialog.Builder", lpparam.classLoader, "setMessage", CharSequence.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB alert load " + param.args[0].toString());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });


            XposedHelpers.findAndHookMethod("com.inca.security.DexProtect.Binder", lpparam.classLoader, "setABI", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB abi " + param.args[0]);
                    //param.args[0] = 0;
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod("com.inca.security.DexProtect.Binder", lpparam.classLoader, "getABI", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("AndyXB abi " + (int)param.getResult());
                    super.afterHookedMethod(param);
                }
            });

        }
    }


}
