package ru.argosgrp.docflow.service;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import ru.argosgrp.docflow.domain.Contractor
import ru.argosgrp.docflow.utils.FormatUtils


@Service('argosTaskService')
public class TaskService extends ActivitiService {

	@Autowired
	ContractorService contractorService
	
	List<Object> list(String user, Map variables){

		if(!user)
			return []
		
		def query = activitiTaskService.createTaskQuery()
		
		if(user != 'superuser')
		 	query.taskAssignee(user)

		variables.each { v ->
			if(v.value)
				query.processVariableValueEquals(v.key, v.value)
		}

		def tasks = query
				.orderByTaskCreateTime()
				.asc()
				.list()

		tasks.collect { t->
			[
				id:t.id,
				name:t.name,
				processDefinitionId:t.processDefinitionId,
				assignee:t.assignee,
				createTime: FormatUtils.dateTime(t.createTime)
			]
		}
	}

	void complete(String taskId){
		activitiTaskService.complete(taskId)
	}
	
}
