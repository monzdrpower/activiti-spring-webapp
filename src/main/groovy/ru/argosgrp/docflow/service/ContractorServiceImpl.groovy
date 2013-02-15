package ru.argosgrp.docflow.service;

import javax.persistence.EntityManager
import javax.persistence.PersistenceContext

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import ru.argosgrp.docflow.domain.Contractor


@Service('contractorService')
public class ContractorServiceImpl implements ContractorService {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	Contractor create(String customerName) {
		Contractor contractor = new Contractor()
		contractor.customerName = customerName
		contractor.approved  = false
		contractor.startDate = Calendar.getInstance()
		
		entityManager.persist(contractor)
		contractor
	}

	@Transactional(readOnly = true)
	Contractor get(Long id) {
		entityManager.find(Contractor.class, id);
	}

	@Transactional
	void delete(Long id) {
		Contractor contractor = entityManager.find(Contractor.class, id);
		entityManager.createQuery("delete from ${Contractor.class.name} c where c.id = $contractor.id").executeUpdate()
	}

	@Transactional(readOnly = true)
	List<Contractor> list() {
		entityManager.createQuery("select c from ${Contractor.class.name} c").resultList;
	}

}
