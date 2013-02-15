package ru.argosgrp.docflow.serviceimport java.util.List;

import ru.argosgrp.docflow.domain.Contractor;

interface ContractorService {

	Contractor create(String customerName)
	Contractor get(Long id)
	void delete(Long id)
	List<Contractor> list()}
