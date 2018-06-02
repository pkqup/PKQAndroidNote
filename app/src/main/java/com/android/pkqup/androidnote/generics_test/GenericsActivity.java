package com.android.pkqup.androidnote.generics_test;

import android.os.Bundle;
import android.util.Log;

import com.android.pkqup.androidnote.R;
import com.android.pkqup.androidnote.abase.BaseActivity;

/**
 * Created by LiuCun on 2017/11/20.<br>
 * Describe
 */

public class GenericsActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_generics);

        // 泛型的类型参数只能是类类型（包括自定义类），不能是简单类型
        // 传入的实参类型需与泛型的类型参数类型相同，即为Integer.
        GenericsClass<Integer> genericInteger = new GenericsClass<Integer>(123456);
        // 传入的实参类型需与泛型的类型参数类型相同，即为String.
        GenericsClass<String> genericString = new GenericsClass<String>("key_vlaue");

//        Log.d("泛型测试", "key is " + genericInteger.getKey());
//        Log.d("泛型测试", "key is " + genericString.getKey());

        showKeyValue(new GenericsClass<>("string"));
        showKeyValue(new GenericsClass<>(123));
    }


    //泛型通配符？
    public void showKeyValue(GenericsClass<?> obj){
        Log.d("泛型测试","key value is " + obj.getKey());
    }

    /**
     * 泛型方法的基本介绍
     * @param tClass 传入的泛型实参
     * @return T 返回值为T类型
     * 说明：
     *     1）public 与 返回值中间<T>非常重要，可以理解为声明此方法为泛型方法。
     *     2）只有声明了<T>的方法才是泛型方法，泛型类中的使用了泛型的成员方法并不是泛型方法。
     *     3）<T>表明该方法将使用泛型类型T，此时才可以在方法中使用泛型类型T。
     *     4）与泛型类的定义一样，此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型。
     */
    public <T> T genericMethod(Class<T> tClass)throws InstantiationException ,
            IllegalAccessException{
        T instance = tClass.newInstance();
        return instance;
    }

    /**
     * 这才是一个真正的泛型方法。
     * 首先在public与返回值之间的<T>必不可少，这表明这是一个泛型方法，并且声明了一个泛型T
     * 这个T可以出现在这个泛型方法的任意位置.
     * 泛型的数量也可以为任意多个
     *    如：public <T,K> K GenericsClass(Generic<T> container){
     *        ...
     *        }
     */
    public <T> T showKeyName(GenericsClass<T> container){
        Log.d("泛型测试","key value is " + container.getKey());
        T test = container.getKey();
        return test;
    }
}
