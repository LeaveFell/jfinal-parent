package com.Interceptor;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

public class KMInterceptor implements Interceptor {

    public void intercept(Invocation ai) {
        System.out.println("Before action invoking");
        ai.invoke();
        System.out.println("After action invoking");
    }


}