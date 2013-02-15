package ru.argosgrp.docflow.service

import org.activiti.engine.ProcessEngine
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ActivitiService {

	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	protected ProcessEngine processEngine
	
	protected org.activiti.engine.TaskService getActivitiTaskService(){
		processEngine.taskService
	}
	
	protected org.activiti.engine.RuntimeService getActivitiRuntimeService(){
		processEngine.runtimeService
	}
	
	protected org.activiti.engine.RepositoryService getActivitiRepositoryService(){
		processEngine.repositoryService
	}
	

}
