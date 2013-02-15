package ru.argosgrp.docflow.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

import ru.argosgrp.docflow.service.TaskService

@Controller
@RequestMapping("/task")
class TaskController {

	@Autowired
	TaskService taskService

	@RequestMapping(value = "/list", method=RequestMethod.GET)
	ModelAndView list(
		@RequestParam(required = false) String user,
		@RequestParam(required = false) String contractorId){

		def tasks = taskService.list(user, [contractorId:contractorId])

		ModelAndView mav = new ModelAndView()
		mav.addObject("result", "OK")
		mav.addObject("size", tasks.size())
		mav.addObject("tasks", tasks)
		mav
	}

	@RequestMapping(value = "/complete", method=RequestMethod.GET)
	ModelAndView complete(@RequestParam String taskId){

		def tasks = taskService.complete(taskId)

		ModelAndView mav = new ModelAndView()
		mav.addObject("result", "OK")
		mav
	}

}
