package com.saltlux.mysite.security;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(RUNTIME)
@Target({ METHOD, TYPE })
public @interface Auth { // 접근제어를 하기위한 Annotation
	public String role() default "user";
	//public Role role() default Role.USER;
	
}
