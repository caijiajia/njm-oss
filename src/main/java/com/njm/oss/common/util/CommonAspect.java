/** 
 * Project Name:oss 
 * File Name:Aspect.java 
 * Package Name:com.njm.oss.common.util 
 * Date:2015-11-13上午10:25:59 
 * Copyright (c) 2015, bobo All Rights Reserved. 
 * 药药好（杭州）网络科技有限公司
*/  
  
package com.njm.oss.common.util;  

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.ThrowsAdvice;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class CommonAspect implements ThrowsAdvice{
	private static Logger logger = LoggerFactory.getLogger(CommonAspect.class);
	
	/**
	 * 
	 * @Title:  CommonAspect   
	 * @Description:  业务启动初始化切面
	 * @author bobo
	 * @date 2015-11-13 上午10:31:11
	 */
	 public CommonAspect(){
		 logger.info("初始化切面...");
	 }
	 
	 /**
	  * 
	  * @Title:	doBefore4Controller
	  * @Description:    controller类前置通知
	  * @param jp  void  
	  * @author bobo
	  * @date 2015-11-13 上午10:30:13
	  */
	 @Before(value="execution(* com.njm.oss..*Controller*.*(..))")
	 public void doBefore4Controller(JoinPoint jp){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 String data = df.format(new Date());
		 String joinPointClass = jp.getTarget().getClass().getName();
		 String joinPointMethod = jp.getSignature().getName();
		 logger.info(joinPointClass+"."+joinPointMethod +"(begin=====:"+data+")");
	 }
	 
	 /**
	  * 
	  * @Title:	doAfter4Controller
	  * @Description:    controller类后置通知
	  * @param jp  void  
	  * @author bobo
	  * @date 2015-11-13 上午10:32:19
	  */
	 @After(value="execution(* com.njm.oss..*Controller*.*(..))")
	 public void doAfter4Controller(JoinPoint jp){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 String data = df.format(new Date());
		 String joinPointClass = jp.getTarget().getClass().getName();
		 String joinPointMethod = jp.getSignature().getName();
		 logger.info(joinPointClass+"."+joinPointMethod +"(end=====:"+data+")");
	 }
	 
	 /**
	  * 
	  * @Title:	afterThrowing4Controller
	  * @Description:    controller类异常通知
	  * @param ex  void  
	  * @author bobo
	  * @date 2015-11-13 上午10:36:44
	  */
	 @AfterThrowing(value="execution(* com.njm.oss..*Controller*.*(..))",throwing="ex")
	 public void afterThrowing4Controller(Throwable ex){
		 logger.info("抛出异常通知=====:"+ex);
	 }
	 
	 /**
	  * 
	  * @Title:	doBefore4Service
	  * @Description:   service类前置通知
	  * @param jp  void  
	  * @author bobo
	  * @date 2015-11-13 上午10:38:30
	  */
	 @Before(value="execution(* com.njm.oss..*Service*.*(..))")
	 public void doBefore4Service(JoinPoint jp){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 String data = df.format(new Date());
		 String joinPointClass = jp.getTarget().getClass().getName();
		 String joinPointMethod = jp.getSignature().getName();
		 logger.info(joinPointClass+"."+joinPointMethod +"(begin=====:"+data+")");
	 }
	 
	/**
	 * 
	 * @Title:	doAfter4Service
	 * @Description:    service类后置通知
	 * @param jp  void  
	 * @author bobo
	 * @date 2015-11-13 上午10:38:51
	 */
	 @After(value="execution(* com.njm.oss..*Service*.*(..))")
	 public void doAfter4Service(JoinPoint jp){
		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
		 String data = df.format(new Date());
		 String joinPointClass = jp.getTarget().getClass().getName();
		 String joinPointMethod = jp.getSignature().getName();
		 logger.info(joinPointClass+"."+joinPointMethod +"(end=====:"+data+")");
	 }
	 
	 /**
	  * 
	  * @Title:	afterThrowing4Service
	  * @Description:    service类异常通知
	  * @param ex  void  
	  * @author bobo
	  * @date 2015-11-13 上午10:39:43
	  */
	 @AfterThrowing(value="execution(* com.njm.oss..*Service*.*(..))",throwing="ex")
	 public void afterThrowing4Service(Throwable ex){
		 logger.info("抛出异常通知=====:"+ex);
	 }
	 
//	 //环绕通知
//	 @Around(value="execution(* com.njm.oss..*Controller*.*(..))")
//	 public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
//		 Object retVal = pjp.proceed();
//		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		 String data = df.format(new Date());
//		 String joinPointClass = pjp.getTarget().getClass().getName();
//		 String joinPointMethod = pjp.getSignature().getName();
//		 logger.info(joinPointClass+"."+joinPointMethod +"(around:"+data+")");
//		 return retVal;
//	 }
//		 
//	 //获取返回后通知
//	 @AfterReturning(value="execution(* com.njm.oss..*Controller*.*(..))")
//	 public void doAfterReturning(JoinPoint jp){
//		 DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
//		 String data = df.format(new Date());
//		 String joinPointClass = jp.getTarget().getClass().getName();
//		 String joinPointMethod = jp.getSignature().getName();
//		 logger.info(joinPointClass+"."+joinPointMethod +"(afterend:"+data+")");
//	}
}
  