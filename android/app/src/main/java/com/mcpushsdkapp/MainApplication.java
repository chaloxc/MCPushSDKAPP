package com.mcpushsdkapp;

import android.app.Application;
import android.content.Context;
import com.facebook.react.PackageList;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.soloader.SoLoader;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.salesforce.marketingcloud.R;
//import com.yourcompanyname.product.R;
import android.util.Log;
import com.salesforce.marketingcloud.MarketingCloudConfig;
import com.salesforce.marketingcloud.MarketingCloudSdk;
import com.salesforce.marketingcloud.notifications.NotificationCustomizationOptions;


public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost =
      new ReactNativeHost(this) {
        @Override
        public boolean getUseDeveloperSupport() {
          return BuildConfig.DEBUG;
        }

        @Override
        protected List<ReactPackage> getPackages() {
          @SuppressWarnings("UnnecessaryLocalVariable")
          List<ReactPackage> packages = new PackageList(this).getPackages();
          // Packages that cannot be autolinked yet can be added manually here, for example:
          // packages.add(new MyReactNativePackage());
          return packages;
        }

        @Override
        protected String getJSMainModuleName() {
          return "index";
        }
      };

  @Override
  public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }

  @Override
  public void onCreate() {
    super.onCreate();
    MarketingCloudSdk.init(this,
            MarketingCloudConfig.builder()
                    .setApplicationId("4d085cb0-ada7-49e1-b728-1e93abb5d22d") // {MC_APP_ID}
                    .setAccessToken("cjZq6k43aB6NjRBE0qnko5f6") // {MC_ACCESS_TOKEN}
                    .setSenderId("1072974159859") // {FCM_SENDER_ID_FOR_MC_APP} en firebase - settings - cloudmessaging - sender ID
                    .setMarketingCloudServerUrl("https://mcpk8yjlr38c-gnlfdjh-1t51c81.device.marketingcloudapis.com/") // {MC_APP_SERVER_URL}
                    .setMid("100021709")
                    .setNotificationCustomizationOptions( NotificationCustomizationOptions.create(1) ) // NotificationCustomizationOptions.create(R.drawable.ic_notification)
                        // https://salesforce-marketingcloud.github.io/MarketingCloudSDK-Android/javadocs/6.0/reference/com/salesforce/marketingcloud/notifications/NotificationCustomizationOptions.html ID for the notification's small icon
                        // customiza notifications https://salesforce-marketingcloud.github.io/MarketingCloudSDK-Android/notifications/customize-notifications.html
                    .setAnalyticsEnabled(true)
                    .build(this),
            initializationStatus -> Log.e("INIT", initializationStatus.toString()));

    SoLoader.init(this, /* native exopackage */ false);
    initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
  }

  /**
   * Loads Flipper in React Native templates. Call this in the onCreate method with something like
   * initializeFlipper(this, getReactNativeHost().getReactInstanceManager());
   *
   * @param context
   * @param reactInstanceManager
   */
  private static void initializeFlipper(
      Context context, ReactInstanceManager reactInstanceManager) {
    if (BuildConfig.DEBUG) {
      try {
        /*
         We use reflection here to pick up the class that initializes Flipper,
        since Flipper library is not available in release mode
        */
        Class<?> aClass = Class.forName("com.mcpushsdkapp.ReactNativeFlipper");
        aClass
            .getMethod("initializeFlipper", Context.class, ReactInstanceManager.class)
            .invoke(null, context, reactInstanceManager);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      } catch (NoSuchMethodException e) {
        e.printStackTrace();
      } catch (IllegalAccessException e) {
        e.printStackTrace();
      } catch (InvocationTargetException e) {
        e.printStackTrace();
      }
    }
  }
}
