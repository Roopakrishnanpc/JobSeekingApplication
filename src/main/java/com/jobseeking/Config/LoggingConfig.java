package com.jobseeking.Config;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.AfterFinally;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingConfig {
	private static final Logger LOGGER=LoggerFactory.getLogger(LoggingConfig.class);
	//@Before("execution(public List<Post> com.jobseeking.Controller.PostController.getAllPost())")
	@Before("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	public void logBefore()
	{
		LOGGER.info("Get method called from aspect using before");
	}
	@After("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	//@AfterReturning("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	public void logAfter()
	{
		LOGGER.info("Get method called from aspect using after");
	}
	//will get called even if error or exception happened - after
	//will be called only if success exception - after returnng
	//will be called if there s exception - aftertrowing
	//will be called finally irrespectve of exception - afterfinally
	//@After("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	@AfterReturning("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	public void logAfterReturning()
	{
		LOGGER.info("Get method called from aspect using after returning");
	}
	@AfterThrowing("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
	public void logAfterException()
	{
		LOGGER.info("Get method called from aspect using after throwing");
	}
//	AfterFinally("execution(public * com.jobseeking.Controller.PostController.getAllPost())")
//	public void logAfterFinally()
//	{
//		LOGGER.info("Get method called from aspect using after fially");
//	}
}
