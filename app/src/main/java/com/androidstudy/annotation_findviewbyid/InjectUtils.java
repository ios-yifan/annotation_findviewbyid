package com.androidstudy.annotation_findviewbyid;

import android.app.Activity;
import android.view.View;

import java.lang.reflect.Field;

public class InjectUtils {

    public static void inject(Activity activity){

        // 获取到 class
        Class<? extends Activity> activityClass = activity.getClass();
        // 获取到所有的字段
        Field[] declaredFields = activityClass.getDeclaredFields();

        for (Field field : declaredFields) {

            // 判断此字段是否被设置 findview 注解
            if (field.isAnnotationPresent(FindView.class)) {

                // 获取到注解
                FindView annotation = field.getAnnotation(FindView.class);
                //拿到注解的值
                int id = annotation.viewId();
                //拿到 view
                View view = activity.findViewById(id);
                //设置字段的可访问权限
                field.setAccessible(true);
                try {
                    //设置字段的值。 设置到哪个类，和设置什么值
                    field.set(activity,view);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
