package ru.argosgrp.docflow.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.servlet.ModelAndView

import ru.argosgrp.docflow.service.ProcessService

@Controller
@RequestMapping("/process")
class ProcessController {

	@Autowired
	ProcessService processService

	@RequestMapping(value = "/start", method=RequestMethod.GET)
	ModelAndView start(
	@RequestParam(required = false, defaultValue = 'argosProcess') String processKey,
	@RequestParam(required = false, defaultValue = '123') Long contractorId){

		def tasks = processService.startProcess(processKey, [contractorId:contractorId])
		def processes = processService.list()

		ModelAndView mav = new ModelAndView()
		mav.addObject("result", "OK")
		mav.addObject("tasks", tasks)
		mav.addObject("processes", processes)
		mav
	}

	@RequestMapping(value = "/list", method=RequestMethod.GET)
	ModelAndView list(){

		def processes = processService.list()

		ModelAndView mav = new ModelAndView()
		mav.addObject("result", "OK")
		mav.addObject("size", processes.size())
		mav.addObject("processes", processes)
		mav
	}
}
