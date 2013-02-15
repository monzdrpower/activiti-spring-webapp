package ru.argosgrp.docflow.service;

import org.activiti.engine.RepositoryService
import org.activiti.engine.RuntimeService
import org.activiti.engine.repository.ProcessDefinition
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Service

@Service
public class ProcessService extends ActivitiService {

	@Autowired
	TaskService taskService

	List<Task> startProcess(String processKey, Map variables){

		def processInstance = activitiRuntimeService.startProcessInstanceByKey(processKey, variables)
		taskService.list(processInstance.id, [:])
	}

	List<ProcessInstance> list(){
		def processes = activitiRuntimeService.createProcessInstanceQuery().list()
		processes.collect {
			[processDefinitionId: it.processDefinitionId, activityId: it.activityId]
		}
	}
}
